package com.zahariaca.challenge7

fun main() {
    val joe = Person("Joe", "Jones", 45)
    val (fName, lName, age) = joe
    println("fName=$fName, lName=$lName, age=$age")

    val joe2 = Person2("Joe2", "Jones2", 45)
    val (fName2, lName2, age2) = joe2
    println("fName=$fName2, lName=$lName2, age=$age2")

    val jane = Person("Jane", "Smith", 12)
    val mary = Person("Mary", "Wilson", 70)
    val john = Person("Jean", "Smithson", 66)

    val immutableMap = mapOf<String, Person>(
        joe.firstName to joe,
        jane.firstName to jane,
        mary.firstName to mary,
        john.firstName to john
    )

    println(immutableMap.values.count { it.lastName.startsWith("s", true) })

    val pairs =immutableMap.map { Pair(it.value.firstName, it.value.lastName) }
    println(pairs)

    val firstName = immutableMap.map { it.value.firstName }
    val lastName = immutableMap.map { it.value.lastName }
    val pairs2 = firstName.zip(lastName)
    println(pairs2)

    // also() works with receivers
    // Joe is 45 years old..
    // Jane is 12 years old..
    immutableMap.also {
        it.map { map -> println("${map.value.firstName} is ${map.value.age} years old") }
    }

    val list1 = listOf(1,4,9,15,33)
    val list2 = listOf(4,55,-83, 15,22,101)

    val list3 = list1.filter { list2.contains(it) }
    println(list3)

    val regularPaper = Box<Regular> ()
    var paper = Box<Paper>()
    paper = regularPaper

}

data class Person(val firstName: String, val lastName: String, val age: Int) {

}

class Person2(private val firstName: String, private val lastName: String, private val age: Int) {
    operator fun component1() = firstName
    operator fun component2() = lastName
    operator fun component3() = age
}

// covariance (accept T and all of its subclasses, `in` contravariance (accept T and all of its superclasses)
class Box<out T> {}
open class Paper {}
class Regular: Paper() {}
class Premium: Paper() {}