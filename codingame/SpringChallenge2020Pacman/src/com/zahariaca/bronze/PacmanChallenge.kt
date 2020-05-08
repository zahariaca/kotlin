import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Grab the pellets as fast as you can!
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val width = input.nextInt() // size of the grid
    val height = input.nextInt() // top left corner is (x=0, y=0)
    System.err.println("Width: $width Height: $height")
    if (input.hasNextLine()) {
        input.nextLine()
    }

    val grid = createGrid(height, input, width)

    // game loop
    // TODO: Find way to avoid conflict with other pacmen
//    var previousTarget: Coordinate<Int, Int> = Coordinate(0, 0);
    while (true) {
        val myScore = input.nextInt()
        val opponentScore = input.nextInt()
        val visiblePacCount = input.nextInt() // all your pacs and enemy pacs in sight
        val pacmanList = getMyPacmen(input, visiblePacCount, grid)

        val visiblePelletCount = input.nextInt() // all pellets in sight
        val allPellets1 = arrayListOf<Pellet>()
        for (i in 0 until visiblePelletCount) {
            val x = input.nextInt()
            val y = input.nextInt()
            val value = input.nextInt() // amount of points this pellet is worth
            val pellet = Pellet(Coordinate(x, y), value)
            allPellets1.add(pellet)
        }
        val allPellets = allPellets1

        pacmanList.forEach {
            it.allPellets = allPellets
            it.adjacentPellets = allPellets
/*            System.err.println("All valid moves: ${it.validMoves}")
            System.err.println("Filtered pellets: ${it.adjacentPellets}")*/
        }
//        System.err.println(pacmanList.map { printCommand(it.id, it.getTargetPellet().coordinates) }.toList().joinToString(postfix = ";"))
        println(pacmanList.joinToString(separator = "|") { printCommand(it.id, it.getTargetPellet().coordinates) })
    }
}

private fun createGrid(height: Int, input: Scanner, width: Int): Grid {
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

private fun getMyPacmen(
    input: Scanner,
    visiblePacCount: Int,
    grid: Grid
): MutableList<Pacman> {
    val pacmanList = mutableListOf<Pacman>()
    var pacman: Pacman
    for (i in 0 until visiblePacCount) {
        val pacId = input.nextInt() // pac number (unique within a team)
        val mine = input.nextInt() != 0 // true if this pac is yours
        val x = input.nextInt() // position in the grid
        val y = input.nextInt() // position in the grid
        if (mine) {
            // TODO save instancesof pacman
            val currentPacmanNode = grid.grid[y][x]
            pacman = Pacman(pacId, currentPacmanNode, grid)
            pacmanList.add(pacman)
        }
        val typeId = input.next() // unused in wood leagues
        val speedTurnsLeft = input.nextInt() // unused in wood leagues
        val abilityCooldown = input.nextInt() // unused in wood leagues
    }
    return pacmanList
}

fun printCommand(pacmanId: Int, coordinates: Coordinate<Int, Int>): String {
    return "MOVE $pacmanId ${coordinates.x} ${coordinates.y}"
}


data class Pellet(val coordinates: Coordinate<Int, Int>, val value: Int) {
}

data class Pacman(
    val id: Int,
    val node: Node,
    val grid: Grid
) {
    val validMoves: List<Node>
    var allPellets: List<Pellet> = arrayListOf()
    var adjacentPellets: List<Pellet> = arrayListOf()
        set(allPellets: List<Pellet>) {
            field = allPellets.filter { validMoves.contains(grid.grid[it.coordinates.y][it.coordinates.x]) }
        }

    init {
        validMoves = calculatePossibilities()
            .filter { grid.grid[it.coordinates.y][it.coordinates.x].walkable }.toList()
    }

    fun calculatePossibilities(): List<Node> {
        val neighbours = mutableListOf<Node>()
        for (x in -1..1) {
            for (y in -1..1) {
                if (x == 0 && y == 0) continue

                val checkX = node.coordinates.x + x
                val checkY = node.coordinates.y + y

                if (checkX >= 0 && checkY >= 0 && checkX < grid.gridSizeX && checkY <  grid.gridSizeY) {
                    neighbours.add(grid.grid[checkY][checkX])
                }
            }
        }

        return neighbours
    }

    fun getTargetPellet(): Pellet {
        return adjacentPellets.find { it.value == 10 } ?: adjacentPellets.find { it.value == 1 }
        ?: allPellets.minBy { distanceFromPacman(node.coordinates.x, node.coordinates.y) } ?: Pellet(
            Coordinate(0, 0),
            -1
        )
    }

    fun distanceFromPacman(pacmanX: Int, pacmanY: Int): Int {
        val xDiff: Double = (node.coordinates.x - pacmanX).toDouble()
        val yDiff: Double = (node.coordinates.y - pacmanY).toDouble()

        return sqrt(xDiff.pow(2) + yDiff.pow(2)).toInt()
    }
}

class Grid(val gridSizeX: Int, val gridSizeY: Int, val grid: MutableList<ArrayList<Node>>) {}

class Node(
    val walkable: Boolean,
    val coordinates: Coordinate<Int, Int>
) {}

data class Coordinate<out A, out B>(
    val x: A,
    val y: B
)