package com.zahariaca.sequences

fun main() {
    val immutableMap = mapOf<Int, Car>(
        1 to Car("green", "Toyota", 2015),
        2 to Car("red", "Ford", 2016),
        3 to Car("silver", "Honda", 2013),
        17 to Car("red", "BMW", 2015),
        8 to Car("green", "Ford", 1999)
    )

    println(immutableMap.filter { it.value.model == "Ford" } // filter creates an intermediate collections
        .map { it.value.color }) // map also creates one

    // sequences are similar with Java streams
    // asSequence is basically .stream(), this will not create intermediate collections and needs terminal operation like in java
    println(immutableMap.asSequence().filter { it.value.model == "Ford" }
        .map { it.value.color })

    println(listOf("Jow", "Mary", "Jane").asSequence()
        .filter { println("filtering: $it"); it[0] == 'J' }
         
        .find { it.endsWith('W') })


}

data class Car(val color: String, val model: String, val year: Int) {

}