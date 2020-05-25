package com.zahariaca.calljava

import com.zahariaca.javacode.Car

fun main() {
    val car = Car("blue", "Ford", 2015)
    car.color = "green"
    println(car)

//    car.color = null  RuntimeException
    car.variableMethod(5, "Hello", "Goodbye")

    val strings = arrayOf("hello", "goodbye")
    car.variableMethod(10, *strings)

    car.wantsIntArray(arrayOf(1, 2, 3).toIntArray())

//    (car.anObject as java.lang.Object).notify()
    println("x= ${Car.x}")
    println(Car.xString())

    car.demoMethod({ print("I'm in kotlin and a thread!") })
}