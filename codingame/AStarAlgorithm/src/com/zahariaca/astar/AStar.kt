package com.zahariaca.astar

import kotlin.math.abs

fun main() {
    val inputStrings = listOf(
        " #  ##",
        " ##   ",
        "      "
    )
    val width = inputStrings[0].length
    val height = inputStrings.size

    val grid = mutableListOf<ArrayList<Node>>()
    for (i in 0 until height) {
        val string = inputStrings[i]
        val row = ArrayList<Node>()
        for (index in string.indices) {
            row += Node(string[index] != '#', Coordinate(index, i))
        }
        grid += row
    }

    val gridObj = Grid(width, height, grid)
    val pathFinding = PathFinding(gridObj)

    println("Path: ${pathFinding.findPath(Coordinate(0, 0), Coordinate(5, 2))}")
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


class Node(
    val walkable: Boolean,
    val coordinates: Coordinate<Int, Int>
) {
    var gCost: Int = 0
    var hCost: Int = 0
    val fCost: Int
        get() = gCost + hCost
    lateinit var parent: Node

    override fun toString(): String {
        return "Node(walkable=$walkable, coordinates=$coordinates, gCost=$gCost, hCost=$hCost, fCost=$fCost)"
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