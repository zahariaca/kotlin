package com.zahariaca.sets

fun main() {
    val setInts = setOf(10,15,19,5,3,-22)
    println(setInts.javaClass)
    println(setInts.plus(20)) // this creates a new set, as the setInts is immutable
    println(setInts.plus(10))
    println(setInts.minus(19))
    println(setInts.minus(100))
    println(setInts.average())
    println(setInts.drop(3)) // drop first 3 elements from set

    val mutableSetInts = mutableSetOf(1,2,3,4,5)
    mutableSetInts.plus(10) // even though the set is mutable now, the .plus function does no change the actual set, it returns a new set
    println(mutableSetInts)
}