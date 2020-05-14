package com.zahariaca.lists

fun main() {
    val strings = listOf("spring", "summer", "fall", "summer", "winter")
    val colorList = listOf("black", "white", "red", "black")

    println(strings.last())
    println(strings.asReversed())

//    if (strings.size > 5) {
//        println(strings[5])
//    }

    println(strings.getOrNull(5))

    val ints = listOf(1,2,3,4,5)
    println(ints.max())

    // zip creates pair elements
    // e.g: [(black, spring), (white, summer), (red, fall)]
    println(colorList.zip(strings))

    val mergedList = listOf(colorList, strings)
    println(mergedList)

    val combinedList = colorList + strings
    println(combinedList)

    // combine without duplicates
    val noDupsList = colorList.union(strings)
    println(noDupsList)

    // remove all duplicates
    val noDupColors = colorList.distinct()
    println(noDupColors)

    val mutableSeasons = strings.toMutableList()
    mutableSeasons.add("some other season")
    println(mutableSeasons)

}