package com.zahariaca.collections

fun main() {
    val strings = listOf("spring", "summer", "fall", "winter")
    println(strings.javaClass)

    val emptyList: List<String> = emptyList()
    println(emptyList.javaClass)

    var strings1 = listOf<String>()

    val notNullList = listOfNotNull("hello", null, "goodbye")
    println(notNullList)

    val arrayList = arrayListOf(1,2,3)
    println(arrayList.javaClass)

    val mutableList = mutableListOf(1,2,3)
    println(mutableList.javaClass)
    println(mutableList[2])
    mutableList[1] = 20
    println(mutableList)

    val array = arrayOf("black", "white", "green")
    val colorsList = listOf(*array) // or array.toList()
    println(colorsList)

    val ints = intArrayOf(1,2,3)
    println(ints.toList())
}