package com.zahariaca.walkfiletree

import java.io.File

fun main() {
    File(".").walkTopDown().forEach { println(it) }
    println()
    File(".").walkTopDown().maxDepth(1).forEach { println(it) }
    println()
    File(".").walkTopDown()
        .filter { it.name.endsWith(".kt") }
        .forEach { println(it) }
    println()
}