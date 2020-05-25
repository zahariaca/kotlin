@file:JvmName("StaticCar")

package com.zahariaca.kotlincode

import java.io.IOException

fun topLevel() = println("I'm in the kotlin Car file!")

object SingletonObj {
    @JvmStatic
    fun doSomething() = println("I'm doing something in the SingletonObj")
}

@Throws(IOException::class)
fun doIO() {
    throw IOException()
}

@JvmOverloads
fun defaultArgs(str: String, num: Int = 25) {
    println(str+num)
}

class Car(color: String, @JvmField val model: String, val year: Int) {
    var color: String = color
        set(value) {
            field = "always green"
        }

    companion object {
        const val constant = 266

        @JvmField
        val isAuto = false

        @JvmStatic
        fun carComp() = println("I'm in cars comanion object")
    }

    fun printMe(text: String) {
        println("I don't expect a null: " + text)
    }


}

fun String.print() {
    println(this)
}