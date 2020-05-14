import java.util.*
import kotlin.math.abs

/**
 * Grab the pellets as fast as you can!
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val grid = createGrid(input)

    // game loop
    var rounds = 0
    val pacmanMap = mutableMapOf<Int, Pacman>()
    val enemyList = mutableListOf<Pacman>()
    while (true) {
        val myScore = input.nextInt()
        val opponentScore = input.nextInt()
        val visiblePacCount = input.nextInt() // all your pacs and enemy pacs in sight
        for (i in 0 until visiblePacCount) {
            val pacId = input.nextInt() // pac number (unique within a team)
            val mine = input.nextInt() != 0 // true if this pac is yours
            val x = input.nextInt() // position in the grid
            val y = input.nextInt() // position in the grid
            val typeId = input.next() // unused in wood leagues
            val speedTurnsLeft = input.nextInt() // unused in wood leagues
            val abilityCooldown = input.nextInt() // unused in wood leagues
            if (mine) {
                if (rounds == 0) {
                    val pacman = Pacman(pacId, Node(true, Coordinate(x, y)), typeId, speedTurnsLeft, abilityCooldown, true)
                    pacman.positionLastTurn = grid.getNode(Coordinate(x,y))
                    pacman.target = grid.getNode(Coordinate(x,y))
                    pacmanMap[pacId] = pacman

                } else {
                    val previousPac = pacmanMap[pacId]
                    previousPac!!.node = Node(true, Coordinate(x, y))
                    previousPac.typeId = typeId
                    previousPac.speedTurnsLeft = speedTurnsLeft
                    previousPac.abilityCooldown = abilityCooldown
                    previousPac.isAlive = true
                }
            } else {
                enemyList += Pacman(pacId, Node(true, Coordinate(x, y)), typeId, speedTurnsLeft, abilityCooldown, true)
            }
        }
        val visiblePelletCount = input.nextInt() // all pellets in sight
        val pellets = mutableListOf<Pellet>()
        for (i in 0 until visiblePelletCount) {
            val x = input.nextInt()
            val y = input.nextInt()
            val value = input.nextInt() // amount of points this pellet is worth
            pellets += Pellet(Node(true, Coordinate(x, y)), value)
        }
        val livePacmen = pacmanMap.filter { entry -> entry.value.isAlive }.values

        livePacmen.forEach { pacman ->
            pacman.neighbors = grid.getNeighbour(pacman.node)
            pacman.neighbors.forEach { neighbor ->
                neighbor.hasEnemy = false
                if (enemyList.map { it.node }.contains(neighbor)) {
                    System.err.println("Enemy close! : $neighbor")
                    neighbor.hasEnemy = true;
                }
            }
        }

        val normalPellets = pellets.filter { it.value == 1 }.toMutableList()
        val superPellets = pellets.filter { it.value == 10 }.toMutableList()
        System.err.println("All super pellets: $superPellets")
        var closestSP: Node? = null
        val commandString = mutableListOf<String>()

        if (rounds == 0 || rounds % 10 == 0) {
            livePacmen.forEach { pacman ->
                commandString += "SPEED ${pacman.id}"
            }
        } else {
            livePacmen.forEach { pacman ->
                System.err.println("Pacman: ${pacman.id} has reached his target. Recalculating target")
                if (superPellets.isNotEmpty()) {
                    val shortestPaths = getShortestPaths(superPellets, grid, pacman)
                    closestSP = shortestPaths.minBy { it.size }!!.last()
                    superPellets.removeIf { it.node == closestSP }
                    pacman.target = closestSP as Node
                } else if (normalPellets.isNotEmpty()) {
                    val shortestPath = getShortestPaths(normalPellets, grid, pacman)
                    closestSP = shortestPath.minBy { it.size }?.last()
                    normalPellets.removeIf { it.node == closestSP }
                    pacman.target = closestSP as Node
                } else if (pellets.isNotEmpty() && pacman.node == pacman.target) {
                    val randomPellet = pellets.last()
                    pellets.remove(randomPellet)
                    pacman.target = randomPellet.node
                } else if (pacman.node == pacman.target || pacman.node == pacman.positionLastTurn){
                    val randomGridNode = grid.grid.random().random()
                    pacman.target = randomGridNode
                }
            }

            livePacmen.forEach{pacman ->
                commandString += "MOVE ${pacman.id} ${pacman.target.coordinates.x} ${pacman.target.coordinates.y}"
                pacman.positionLastTurn = pacman.node
                pacman.isAlive = false
                pacmanMap[pacman.id] = pacman
            }
        }



        println(commandString.joinToString(separator = "|")) // MOVE <pacId> <x> <y>
        rounds++
    }
}

private fun getShortestPaths(
    pellets: List<Pellet>,
    grid: Grid,
    pacman: Pacman
): List<List<Node>> {
    return pellets.map {
        PathFinding(grid.copy()).findPath(
            pacman.node.coordinates,
            it.node.coordinates
        )
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

class Node(
    val walkable: Boolean,
    val coordinates: Coordinate<Int, Int>,
    var hasEnemy: Boolean = true
) {
    var gCost: Int = 0
    var hCost: Int = 0
    val fCost: Int
        get() = gCost + hCost
    lateinit var parent: Node

    override fun toString(): String {
        return "Node(walkable=$walkable, coordinates=$coordinates, gCost=$gCost, hCost=$hCost, fCost=$fCost)"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        return if (other is Node) coordinates == other.coordinates else false
    }

    override fun hashCode(): Int {
        return coordinates.hashCode()
    }
}

data class Grid(private val gridSizeX: Int, private val gridSizeY: Int, val grid: MutableList<ArrayList<Node>>) {

    fun getNode(coordinates: Coordinate<Int, Int>): Node = grid[coordinates.y][coordinates.x]
    fun getNeighbour(center: Node): List<Node> {
        val neighbours = mutableListOf<Node>()
        for (x in -1..1) {
            for (y in -1..1) {
                if (x == 0 && y == 0) continue

                val checkX = center.coordinates.x + x
                val checkY = center.coordinates.y + y

                if (checkX >= 0 && checkY >= 0 && checkX < gridSizeX && checkY < gridSizeY) {
                    neighbours.add(grid[checkY][checkX])
                }
            }
        }

        return neighbours
    }
}

data class Coordinate<out A, out B>(
    val x: A,
    val y: B
)

data class Pacman(
    val id: Int,
    var node: Node,
    var typeId: String,
    var speedTurnsLeft: Int,
    var abilityCooldown: Int,
    var isAlive: Boolean = false
) {
    lateinit var neighbors: List<Node>
    lateinit var positionLastTurn: Node
    lateinit var target: Node

    /*fun getTargetNodeAndPresumeDead(): Node {
        isAlive = false
        *//*if (validMoves.isEmpty()) {
            return allPelletNodes.minBy { calculateDistance(node, it) }
                ?: Node(false, Coordinate(0, 0), -1)
        }
        return validMoves[0]*//*
        var result = node.neighbours.find { it.pelletValue == 10 }
            ?: node.neighbours.find { it.pelletValue == 1 }
            ?: allPelletNodes.filter { it.pelletValue == 10 }.minBy { calculateDistance(node, it) }
            ?: allPelletNodes.filter { it.pelletValue == 1 }.minBy { calculateDistance(node, it) }
            ?: Node(false, Coordinate(0, 0), -1)
        System.err.println("Pacman [$id] has chosen: $result")
        result.pelletValue = 0
        return result

    }*/
}

data class Pellet(val node: Node, val value: Int) {

}

class PathFinding(private val grid: Grid) {
    fun findPath(startPos: Coordinate<Int, Int>, targetPos: Coordinate<Int, Int>): List<Node> {
        val startNode = grid.getNode(startPos)
        val targetNode = grid.getNode(targetPos)

        val openList = mutableListOf<Node>()
        val closedSet = hashSetOf<Node>()

        openList.add(startNode)

        while (openList.isNotEmpty()) {
            var currentNode = openList[0]
            for (i in 1 until openList.size) {
                if (openList[i].fCost < currentNode.fCost || openList[i].fCost == currentNode.fCost && openList[i].hCost < currentNode.hCost) {
                    currentNode = openList[i]
                }
            }

            openList.remove(currentNode)
            closedSet.add(currentNode)

            if (currentNode == targetNode) {
                return retracePath(startNode, targetNode)
            }

            for (neighbour in grid.getNeighbour(currentNode)) {
                if (!neighbour.walkable || closedSet.contains(neighbour)) {
                    continue
                }

                val newMovementCostToNeighbour = currentNode.gCost + calculateDistance(currentNode, neighbour)
                if (newMovementCostToNeighbour < neighbour.gCost || !openList.contains(neighbour)) {
                    neighbour.gCost = newMovementCostToNeighbour
                    neighbour.hCost = calculateDistance(neighbour, targetNode)
                    neighbour.parent = currentNode

                    if (!openList.contains(neighbour)) openList.add(neighbour)
                }
            }
        }

        return emptyList()
    }

    private fun retracePath(starNode: Node, targetNode: Node): MutableList<Node> {
        val path = mutableListOf<Node>()
        var currentNode = targetNode
        while (currentNode != starNode) {
            path.add(currentNode)
            currentNode = currentNode.parent
        }
        path.reverse()
        return path

    }

    private fun calculateDistance(nodeA: Node, nodeB: Node): Int {
        val dstX = abs(nodeA.coordinates.x - nodeB.coordinates.x)
        val dstY = abs(nodeA.coordinates.y - nodeB.coordinates.y)

        return if (dstX > dstY) 14 * dstY + 10 * (dstX - dstY) else 14 * dstX + 10 * (dstY - dstX)
    }
}