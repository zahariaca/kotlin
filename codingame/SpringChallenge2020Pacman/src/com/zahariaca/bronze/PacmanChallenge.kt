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
    var firstRound = true;
    var pacmanList = mutableMapOf<Int, Pacman>()
    var rounds = 0
    while (true) {
        val myScore = input.nextInt()
        val opponentScore = input.nextInt()

        // TODO: Pacmen cannot be created before grid and nodes are properly populated...
        pacmanList = createMyPacmen(input, grid, firstRound, pacmanList)

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
        val livePacmen = pacmanList.values.filter {
            it.isAlive
        }

        livePacmen.forEach {
            it.allPelletNodes = allPellets1
            it.node.neighbours = calculatePossibilities(it.node, grid)
            for (n in it.node.neighbours) {
                n.neighbours = calculatePossibilities(n, grid)
            }
            System.err.println("[${it.id}] All valid moves: ${it.node.neighbours}\n")
            for (n in it.node.neighbours) {
                System.err.println("    all neighbourMoves: ${n.neighbours}")
            }
        }

        println(livePacmen.joinToString(separator = "|") {
            printCommand(it.id, it.getTargetNodeAndPresumeDead().coordinates, rounds)
        })
        rounds += 1
        grid.resetPelletValue()
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
    grid: Grid,
    firstRound: Boolean,
    pacmanList: MutableMap<Int, Pacman>
): MutableMap<Int, Pacman> {
    val visiblePacCount = input.nextInt() // all your pacs and enemy pacs in sight
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
            val currentPacmanNode = grid.getNode(x, y)
            if (firstRound) {
                pacman = Pacman(pacId, currentPacmanNode, typeId, speedTurnsLeft, abilityCooldown, true)
                pacmanList[pacId] = pacman
            } else {
                val oldPacman = pacmanList[pacId]
                oldPacman!!.node = currentPacmanNode
                oldPacman.typeId = typeId
                oldPacman.speedTurnsLeft = speedTurnsLeft
                oldPacman.abilityCooldown = abilityCooldown
                oldPacman.isAlive = true
            }
        }
    }
    return pacmanList
}

fun printCommand(pacmanId: Int, coordinates: Coordinate<Int, Int>, rounds: Int): String {
    if (rounds == 0 || rounds % 10 == 0) {
        return "SPEED $pacmanId"
    }
    return "MOVE $pacmanId ${coordinates.x} ${coordinates.y}"
}

fun calculatePossibilities(center: Node, grid: Grid): List<Node> {
    val neighbours = mutableListOf<Node>()
    for (x in -1..1) {
        for (y in -1..1) {
            /*if (x == 0 && y == 0
                || x==-1 && y ==-1
                || x==1 && y==-1
                || x==1 && y==1
                || x==-1 && y==1) {
                continue
            }*/
            if (x == 0 && y == 0) continue

            val checkX = center.coordinates.x + x
            val checkY = center.coordinates.y + y
            // TODO: externalize gridSize*
            if (checkX >= 0
                && checkY >= 0
                && checkX < grid.gridSizeX
                && checkY < grid.gridSizeY
                && grid.getNode(checkX, checkY).walkable
                && grid.getNode(checkX, checkY).pelletValue > 0
            ) {
                val neighbourNode = grid.getNode(checkX, checkY)
                neighbourNode.gCost = center.gCost + calculateDistance(center, neighbourNode)
                neighbours.add(neighbourNode)
            }
        }
    }

    return neighbours
}

fun calculateDistance(nodeA: Node, nodeB: Node): Int {
    val dstX = abs(nodeA.coordinates.x - nodeB.coordinates.x)
    val dstY = abs(nodeA.coordinates.y - nodeB.coordinates.y)

    return if (dstX > dstY) 14 * dstY + 10 * (dstX - dstY) else 14 * dstX + 10 * (dstY - dstX)
}

data class Pacman(
    val id: Int,
    var node: Node,
    var typeId: String,
    var speedTurnsLeft: Int,
    var abilityCooldown: Int,
    var isAlive: Boolean = false
) {
    lateinit var allPelletNodes: List<Node>

    fun getTargetNodeAndPresumeDead(): Node {
        isAlive = false
        /*if (validMoves.isEmpty()) {
            return allPelletNodes.minBy { calculateDistance(node, it) }
                ?: Node(false, Coordinate(0, 0), -1)
        }
        return validMoves[0]*/
        var result = node.neighbours.find { it.pelletValue == 10 }
            ?: node.neighbours.find { it.pelletValue == 1 }
            ?: allPelletNodes.filter { it.pelletValue == 10 }.minBy { calculateDistance(node, it) }
            ?: allPelletNodes.filter { it.pelletValue == 1 }.minBy { calculateDistance(node, it) }
            ?: Node(false, Coordinate(0, 0), -1)
        System.err.println("Pacman [$id] has chosen: $result")
        result.pelletValue = 0
        return result

    }
}

class Grid(val gridSizeX: Int, val gridSizeY: Int, val grid: MutableList<ArrayList<Node>>) {
    fun getNode(coordinates: Coordinate<Int, Int>): Node {
        return grid[coordinates.y][coordinates.x]
    }

    fun getNode(x: Int, y: Int): Node {
        return grid[y][x]
    }

    fun resetPelletValue() {
        for (l in grid) {
            for (n in l) {
                n.pelletValue = 0
            }
        }
    }
}

data class Node(
    val walkable: Boolean,
    val coordinates: Coordinate<Int, Int>,
    var pelletValue: Int = 0,
    var gCost: Int = 0
) {
    lateinit var neighbours: List<Node>
}

data class Coordinate<out A, out B>(
    val x: A,
    val y: B
)