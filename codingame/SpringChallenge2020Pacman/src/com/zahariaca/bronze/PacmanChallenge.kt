import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

// goes from wood2 to bronze
/**
 * Grab the pellets as fast as you can!
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)

    // create game grid at the start of the game
    val grid = createGrid(input)

    // game loop
    // TODO: Find way to avoid conflict with other pacmen
    while (true) {
        val myScore = input.nextInt()
        val opponentScore = input.nextInt()

        var pacmanList = createMyPacmen(input, grid)
        val allPelletNodes = createAllPelletNodes(input, grid)

        pacmanList.forEach {
            it.allPelletNodes = allPelletNodes
            System.err.println("[${it.id}] All valid moves: ${it.validMoves}")
        }

        println(pacmanList.joinToString(separator = "|") {
            grid.getNode(it.getTargetNode().coordinates).pelletValue = 0
            printCommand(it.id, it.getTargetNode().coordinates)
        })
    }
}

private fun createGrid(input: Scanner): Grid {
    val width = input.nextInt() // size of the grid
    val height = input.nextInt() // top left corner is (x=0, y=0)
    System.err.println("Width: $width Height: $height")
    if (input.hasNextLine()) {
        input.nextLine()
    }

    val grid2DArray = mutableListOf<ArrayList<Node>>()
    for (i in 0 until height) {
        val rowString = input.nextLine() // one line of the grid: space " " is floor, pound "#" is wall
        val row = ArrayList<Node>()
        for (index in rowString.indices) {
            row += Node(rowString[index] != '#', Coordinate(index, i))
        }
        grid2DArray += row
    }

    val grid = Grid(width, height, grid2DArray)
    return grid
}

private fun createMyPacmen(
    input: Scanner,
    grid: Grid
): MutableList<Pacman> {
    val visiblePacCount = input.nextInt() // all your pacs and enemy pacs in sight
    val pacmanList = mutableListOf<Pacman>()
    var pacman: Pacman
    for (i in 0 until visiblePacCount) {
        val pacId = input.nextInt() // pac number (unique within a team)
        val mine = input.nextInt() != 0 // true if this pac is yours
        val x = input.nextInt() // position in the grid
        val y = input.nextInt() // position in the grid
        val typeId = input.next() // unused in wood leagues
        val speedTurnsLeft = input.nextInt() // unused in wood leagues
        val abilityCooldown = input.nextInt() // unused in wood leagues
        if (mine) {
            // TODO save instancesof pacman
            val currentPacmanNode = grid.getNode(x, y)
            pacman = Pacman(pacId, currentPacmanNode, grid, typeId, speedTurnsLeft, abilityCooldown)
            pacmanList.add(pacman)
        }
    }
    return pacmanList
}

private fun createAllPelletNodes(input: Scanner, grid: Grid): ArrayList<Node> {
    val visiblePelletCount = input.nextInt() // all pellets in sight
    val allPellets1 = arrayListOf<Node>()
    for (i in 0 until visiblePelletCount) {
        val x = input.nextInt()
        val y = input.nextInt()
        val value = input.nextInt() // amount of points this pellet is worth
        val pelletNode = grid.getNode(x, y)
        pelletNode.pelletValue = value
        val pelletN = Node(true, Coordinate(x, y), value)
        allPellets1.add(pelletN)
    }
    return allPellets1
}

fun printCommand(pacmanId: Int, coordinates: Coordinate<Int, Int>): String {
    return "MOVE $pacmanId ${coordinates.x} ${coordinates.y}"
}

data class Pacman(
    val id: Int,
    val node: Node,
    val grid: Grid,
    val typeId: String,
    val speedTurnsLeft: Int,
    val abilityCooldown: Int
) {
    val validMoves: List<Node>
    var allPelletNodes: List<Node> = arrayListOf()

    init {
        validMoves = calculatePossibilities()
            .filter { grid.getNode(it.coordinates).walkable }.toList()
    }

    fun calculatePossibilities(): List<Node> {
        val neighbours = mutableListOf<Node>()
        for (x in -1..1) {
            for (y in -1..1) {
                if (x == 0 && y == 0) continue

                val checkX = node.coordinates.x + x
                val checkY = node.coordinates.y + y
//                System.err.println("[${id}] for x=$x for y=$y possible x=$checkX possible y= $checkY")
                if (checkX >= 0
                    && checkY >= 0
                    && checkX < grid.gridSizeX
                    && checkY < grid.gridSizeY
                    && grid.getNode(checkX, checkY).walkable
                    && grid.getNode(checkX, checkY).pelletValue > 0
                ) {
                    neighbours.add(grid.getNode(checkX, checkY))
                }
            }
        }

        return neighbours
    }

    fun getTargetNode(): Node {
        if (validMoves.isEmpty()) {
            return  allPelletNodes.minBy { distanceFromPacman(node, it) }
                ?: Node(false, Coordinate(0, 0),-1)
        }
        return validMoves[0]
        /*return validMoves.find { System.err.println("[$id] condition: it.pelletValue == 10"); it.pelletValue == 10 }
            ?: validMoves.find { System.err.println("[$id] condition: it.pelletValue == 1"); it.pelletValue == 1 }
            ?: allPelletNodes.filter { it.pelletValue == 10}.minBy { distanceFromPacman(node, it) }
            ?: allPelletNodes.filter {it.pelletValue == 1 }.minBy { distanceFromPacman(node, it) }
            ?: Node(false, Coordinate(0, 0),-1)*/
    }

    private fun distanceFromPacman(nodeA: Node, nodeB: Node): Int {
        val dstX = abs(nodeA.coordinates.x - nodeB.coordinates.x)
        val dstY = abs(nodeA.coordinates.y - nodeB.coordinates.y)

        return if (dstX > dstY) 14 * dstY + 10 * (dstX - dstY) else 14 * dstX + 10 * (dstY - dstX)
    }
}

class Grid(val gridSizeX: Int, val gridSizeY: Int, val grid: MutableList<ArrayList<Node>>) {
    fun getNode(coordinates: Coordinate<Int, Int>): Node {
        return grid[coordinates.y][coordinates.x]
    }

    fun getNode(x: Int, y: Int): Node {
        return grid[y][x]
    }
}

data class Node(
    val walkable: Boolean,
    val coordinates: Coordinate<Int, Int>,
    var pelletValue: Int = 0
) {}

data class Coordinate<out A, out B>(
    val x: A,
    val y: B
)