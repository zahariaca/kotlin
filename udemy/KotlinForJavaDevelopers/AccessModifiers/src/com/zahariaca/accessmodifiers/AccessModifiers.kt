package com.zahariaca.accessmodifiers

// top level constants
val MY_CONSTANT = 100

// In Kotlin, the default access modifier is PUBLIC, not package private (DEFAULT) in Java
// In kotlin you can have as many public classes in the same file
// private means that everything in the same file.kt can access it, this means we can have private classes
// that can be used by others in the same file.kt
fun main(args: Array<String>) {
    println(MY_CONSTANT)

    val emp = Employee("John")
    println(emp.firstName)

    val emp2 = Employee("Joe")
    println(emp2.firstName)
    println(emp2.fullTime)

    val emp3 = Employee("Jane", false)
    println(emp3.firstName)
    emp3.fullTime = false
    println(emp3.fullTime)


    val demo = Demo()
    println(demo.dummy)


    val car = Car("blue", "Toyota", 2015)
    val car2 = Car("blue", "Toyota", 2015)
    println("Non data class: $emp")
    println("Data class:     $car")

    println("Non data class: ${emp == Employee("John")}")
    println("Data class:     ${car == car2}")

    val car3 = car.copy()
    println(car3)
    val car4 = car.copy(year = 2016, color = "green")
    println(car4)
}

// all classes are public and final by default in Kotlin
class Employee(val firstName: String, fullTime: Boolean = true) { // this is a primary constructor
    // this is a secondary constructor. using val/var in secondary, does not declare properties for us like the primary constructor
    // this can be done in the primary constructor
//    var fullTime: Boolean = true
    /* constructor(firstName:String, fullTime: Boolean): this(firstName) {
         this.fullTime = fullTime
     }*/

    // ---------------- custom get/set ------------------
    var fullTime = fullTime
        get() {
            println("Running the custom get")
            return field
        }
        set(value) {
            println("Running the custom set")
            field = value
        }
}

class Demo {
    val dummy: String

    constructor() {
        this.dummy = "Hello"
    }
}

// ---------------- Data classes -------------------
// data classes come with a toString, equals, hashCode by default
data class Car(val color: String, val model: String, val year: Int) {}