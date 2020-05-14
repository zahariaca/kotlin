package com.zahariaca.bfs

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


}

class Node(
    val walkable: Boolean,
    val coordinates: Coordinate<Int, Int>
) {}

class Grid(private val gridSizeX: Int, private val gridSizeY: Int, val grid: MutableList<ArrayList<Node>>) {

}

data class Coordinate<out A, out B>(
    val x: A,
    val y: B
)