/*
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

// goes from wood2 to bronze
*/
/**
 * Grab the pellets as fast as you can!
 **//*

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val width = input.nextInt() // size of the grid
    val height = input.nextInt() // top left corner is (x=0, y=0)
    System.err.println("Width: $width Height: $height")
    if (input.hasNextLine()) {
        input.nextLine()
    }
    var grid = arrayOf<CharArray>()
    for (i in 0 until height) {
        val row = input.nextLine() // one line of the grid: space " " is floor, pound "#" is wall
        grid += row.toCharArray()
    }
    // game loop
    // TODO: Find way to avoid conflict with other pacmen
    var previousTarget: Pair<Int, Int> = Pair(0, 0);
    while (true) {
        val myScore = input.nextInt()
        val opponentScore = input.nextInt()
        val visiblePacCount = input.nextInt() // all your pacs and enemy pacs in sight
        val pacmanList = getMyPacmen(input, visiblePacCount, width, height, grid)

        val allPellets = getAllPelletsOnBoard(input)

        pacmanList.forEach {
            it.allPellets =allPellets
            it.adjacentPellets = allPellets
*/
/*            System.err.println("All valid moves: ${it.validMoves}")
            System.err.println("Filtered pellets: ${it.adjacentPellets}")*//*

        }
//        System.err.println(pacmanList.map { printCommand(it.id, it.getTargetPellet().coordinates) }.toList().joinToString(postfix = ";"))
        println(pacmanList.joinToString(separator = "|") { printCommand(it.id, it.getTargetPellet().coordinates) })
    }
}

private fun getMyPacmen(input: Scanner, visiblePacCount: Int, width: Int, height: Int, grid: Array<CharArray>): MutableList<Pacman> {
    val pacmanList = mutableListOf<Pacman>()
    var pacman: Pacman
    for (i in 0 until visiblePacCount) {
        val pacId = input.nextInt() // pac number (unique within a team)
        val mine = input.nextInt() != 0 // true if this pac is yours
        val x = input.nextInt() // position in the grid
        val y = input.nextInt() // position in the grid
        if (mine) {
            // TODO save instancesof pacman
            pacman = Pacman(pacId, Pair(x, y), width, height, grid)
            pacmanList.add(pacman)
        }
        val typeId = input.next() // unused in wood leagues
        val speedTurnsLeft = input.nextInt() // unused in wood leagues
        val abilityCooldown = input.nextInt() // unused in wood leagues
    }
    return pacmanList
}

private fun getAllPelletsOnBoard(input: Scanner): ArrayList<Pellet> {
    val visiblePelletCount = input.nextInt() // all pellets in sight
    val allPellets = arrayListOf<Pellet>()
    for (i in 0 until visiblePelletCount) {
        val x = input.nextInt()
        val y = input.nextInt()
        val value = input.nextInt() // amount of points this pellet is worth
        val pellet = Pellet(Pair(x, y), value)
        allPellets.add(pellet)
    }
    return allPellets
}

fun printCommand(pacmanId: Int, coordinates: Pair<Int, Int>): String {
    return "MOVE $pacmanId ${coordinates.first} ${coordinates.second}"
}


data class Pellet(val coordinates: Pair<Int, Int>, val value: Int) {
}

data class Pacman(val id: Int, val coordinates: Pair<Int, Int>, val gridWidth: Int, val gridHeight: Int, val grid: Array<CharArray>) {
    val validMoves: List<Pair<Int, Int>>
    var allPellets: List<Pellet> = arrayListOf()
    var adjacentPellets: List<Pellet> = arrayListOf()
        set(allPellets: List<Pellet>) {
            field = allPellets.filter { validMoves.contains(it.coordinates) }
        }

    init {
        validMoves = calculatePossibilities()
                .filter {
                    val x = it.first
                    val y = it.second
                    x > 0 && y > 0 && x <= gridWidth && y <= gridHeight
                }
                .filter {
                    val x = it.first
                    val y = it.second
                    grid[y][x] != '#'
                }.toList()
    }

    private fun calculatePossibilities(): ArrayList<Pair<Int, Int>> {
        val NE = Pair(coordinates.first + 1, coordinates.second - 1)
        val E = Pair(coordinates.first + 1, coordinates.second)
        val SE = Pair(coordinates.first + 1, coordinates.second + 1)
        val S = Pair(coordinates.first, coordinates.second + 1)
        val SW = Pair(coordinates.first - 1, coordinates.second + 1)
        val W = Pair(coordinates.first - 1, coordinates.second)
        val NW = Pair(coordinates.first - 1, coordinates.second - 1)
        val N = Pair(coordinates.first, coordinates.second - 1)
        return arrayListOf(NE, E, SE, S, SW, W, NW, N)
    }

    fun getTargetPellet(): Pellet {
        return adjacentPellets.find { it.value == 10 } ?: adjacentPellets.find { it.value == 1 }
        ?: allPellets.minBy { distanceFromPacman(coordinates.first, coordinates.second) } ?: Pellet(Pair(0, 0), -1)
    }

    fun distanceFromPacman(pacmanX: Int, pacmanY: Int): Int {
        val xDiff: Double = (coordinates.first - pacmanX).toDouble()
        val yDiff: Double = (coordinates.second - pacmanY).toDouble()

        return sqrt(xDiff.pow(2) + yDiff.pow(2)).toInt()
    }
}*/
