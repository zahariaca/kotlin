package com.zahariaca.collectionsstuff

fun main() {
    val setInts = setOf(10, 15, 19, 5, 3, -22)

    val immutableMap = mapOf<Int, Car>(
        1 to Car("green", "Toyota", 2015),
        2 to Car("red", "Ford", 2016),
        3 to Car("silver", "Honda", 2013)
    )

    println(setInts.filter { it % 2 != 0 })

    println(immutableMap.filter { it.value.year == 2016 })

    val mutableMap = mutableMapOf<Int, Car>(
        1 to Car("green", "Toyota", 2015),
        2 to Car("red", "Ford", 2016),
        3 to Car("silver", "Honda", 2013)
    )
    println(mutableMap.filter { it.value.color == "silver" })

    val ints = arrayOf(1, 2, 3, 4, 5)
    val add10List: MutableList<Int> = mutableListOf()

    for (i in ints) {
        add10List.add(i + 10)
    }
    println(add10List)

    val add10List2 = ints.map { it + 10 }
    println(add10List2.javaClass)
    println(add10List2)

    val immutableMap2 = mapOf<Int, Car>(
        1 to Car("green", "Toyota", 2015),
        2 to Car("red", "Ford", 2016),
        3 to Car("silver", "Honda", 2013),
        17 to Car("red", "BMW", 2015),
        8 to Car("green", "Ford", 1999)
    )

    println(immutableMap2.map { it.value.year })

    println(immutableMap2.filter { it.value.model == "Ford" }
        .map { it.value.color })

    println(immutableMap2.all { it.value.year > 2014 })
    println(immutableMap2.any { it.value.year > 2014 })
    println(immutableMap2.count { it.value.year > 2014 })
    println(immutableMap2.toSortedMap())

    val cars = immutableMap2.values
    println(cars.find { it.year > 2014 })
    println(cars.groupBy { it.color })
    println(cars.sortedBy { it.year })


}

data class Car(val color: String, val model: String, val year: Int) {

}