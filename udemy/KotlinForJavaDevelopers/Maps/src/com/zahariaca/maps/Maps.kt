package com.zahariaca.maps

fun main() {

    val immutableMap = mapOf<Int, Car>(
        1 to Car("green", "Toyota", 2015),
        2 to Car("red", "Ford", 2016),
        3 to Car("silver", "Honda", 2013)
    )

    println(immutableMap.javaClass)
    println(immutableMap)


    val mutableMap = hashMapOf<String, Car>(
        "John's Car" to Car("red", "Range Rover", 2010),
        "Jane's car" to Car("blue", "Hyundai", 2012)
    )
    println(mutableMap.javaClass)
    println(mutableMap)
    mutableMap.put("Mary's Car", Car("red", "Corvette", 1965))

    for (entry in mutableMap) {
        println(entry.key)
        println(entry.value)
    }

    // using destructuring declaration
    for ((key, value) in mutableMap) {
        println(key)
        println(value)
    }
    val pair = Pair(10, "ten")
/*    val firstValue = pair.first
    val secondValue = pair.second*/
    val (firstValue, secondValue) = pair
    println(firstValue)
    println(secondValue)

    val car = Car("blue", "Corvette", 1959)
    val (color, model, year) = car
    println("Color= $color, model=$model, year=$year")
}

data class Car(val color: String, val model: String, val year: Int) {
    // component1()... used in destructing, and are included in data classes
//    operator fun component1() = color
//    operator fun component2() = model
//    operator fun component3() = year
}