package com.zahariaca.loops

fun main(args: Array<String>) {
    // for loops in Kotlin use ranges, similar to Python
    val range = 1..5 // .. range operator, range is inclusive, includes 1 and 5
    for (i in range) {
        print("$i ")
    }
    println()
    val charRange = 'a'..'z'
    val stringRange = "ABC".."XYZ"

    println(3 in range)
    println('q' in charRange)
    println("CCC" in stringRange)
    println("CCCCCC" in stringRange)
    println("ZZZZZZ" in stringRange)

    val backwardRange = 5.downTo(1) // backward range
    val r = 5..1 // not the same as the above
    println(5 in backwardRange)
    val stepRange = 3..15 // 3,4,5,6..15
    val stepThree = stepRange.step(3) // 3,6,9,12,15
    for (n in stepThree) {
        print("$n ")
    }
    val reversedRange = range.reversed()
    println()
    for (n in reversedRange) {
        print("$n ")
    }
    println()

    val str = "Hello"
    for (c in str) {
        print("$c ")
    }

    println()
    for (num in 0..20 step 4) {
        print("$num ")
    }

    println()
    for (num in 20 downTo 15) {
        print("$num ")
    }

    println()
    for (num in 20 downTo 10 step 5) {
        print("$num ")
    }

    println()
    for (num in 1 until 10) { // excludes 10, 1..9
        print("$num ")
    }

    val s = "goodbye"
    val sRange = 0..s.length

    // ------------- arrays loops --------------
    val seasons = arrayOf("spring", "summer", "fall", "winter")

    println()
    for (season in seasons) {
        print("$season ")
    }

    val notASeason = "whatever" !in seasons
    println(notASeason)

    val notInRange = 32 !in 1..10
    println(notInRange)

    println('e' in str)
    println('t' in str)

    for (index in seasons.indices) {
        println("${seasons[index]} is season number: $index")
    }

    seasons.forEach { print("$it ") }
    seasons.forEachIndexed { index, value -> println("$value is season number: $index") }

    
}