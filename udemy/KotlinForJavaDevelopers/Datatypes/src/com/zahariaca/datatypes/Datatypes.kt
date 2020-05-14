package com.zahariaca.datatypes

import com.zahariaca.javacode.DummyClass
import java.math.BigDecimal

fun main(args: Array<String>) {
    val myInt = 10
    var myLong = 22L

    println("Default datatype is Int: ${myInt is Int}")

    // java automatically widens the integer because it fits into a ling
    // int myInt = 10;
    // int myLong = myInt;

    // in Kotlin this does not work
    //myLong = myInt;
    // this does:
    myLong = myInt.toLong()

    val myByte: Byte = 111
    val myShort: Short = myByte.toShort()

    val anotherInt = 5

    // ----------- floating point numbers ----------
    var myDouble = 66.984 // double value
    println(myDouble is Double)

    var myFloat = 838.8429f
    println("This is a float ${myFloat is Float}")

    val char = 'b'
    // java: char myChar = 67;
    // val myChar = 65 // this doesn;t work, compiler assumes this is a Int
    // val myChar: Char = 65 // this doesn't work either
    // you need to be specific like this, no automatic conversion
    val myCharInt = 65
    println("Specific conversion from int to char: ${myCharInt.toChar()}")

    val myBoolean = true

    // --------------- call java method from kotlin with primitive and object boolean/Boolean ----------------
    val vacationTime = false;
    val onVacation = DummyClass().isVacationTime(vacationTime)
    println(onVacation)

    val anything: Any

    // Any, Unit, Nothing datatypes - TODO: read more

    // ------------------- Array ---------------
    val names = arrayOf("John", "Jane", "Joe", "Jill")

    val longs1 = arrayOf(1L, 2L, 3L)
    val longs2 = arrayOf<Long>(1, 2, 3)
    val longs3 = arrayOf(1, 2, 3, 4)

    println(longs1 is Array<Long>)
    println(longs2 is Array<Long>)
    println(longs3 is Array<Int>)

    println(longs1[2])
    println(longs1.get(2))

    val evenNumbers = Array(16) { i -> i * 2 } // i is index, we will have 0*2 = 0, 1*2 = 2, 2*2= 4 etc
    for (number in evenNumbers) {
        print("$number ")
    }
    println()

    val lotsOfNumbers = Array(100000) { i -> i + 1 } // numbers from 1 to 100.000
    val allZeroes = Array(100) { 0 } // all zeroes

    var someArray: Array<Int>
    someArray = arrayOf(1,2,3,4)
    for (number in someArray) {
        print("$number ")
    }
    println()

    someArray = Array(6) { i -> (i+1) * 10}
    for (number in someArray) {
        print("$number ")
    }
    println()

    val mixedArray = arrayOf<Any>("hello", 22, BigDecimal(10.5), 'a') // basically creating an Array<Any>
    for(element in mixedArray) {
        print("$element  ")
    }
    println()
    println(mixedArray is Array<Any>)

    val myIntArray = intArrayOf(3,4,7,8,12,23,55) // intArrayOf -> int[] while arrayOf -> Array<Int>
    DummyClass().printNumbers(myIntArray)

    // var someOtherArray = Array<Int>(5) this does not work, you cannot specify the size of the array here because the constructor expects both size and values
    // var array: Array<Int> // this is ok
    var someOtherArray = IntArray(5) // this does work, with special primitive arrays, will result in int[] filled with 0
    for(number in someOtherArray) {
        print("$number ")
    }
    println()

    DummyClass().printNumbers(someArray.toIntArray()) // converts general Array<Int> to int[]
    val convertedInt = someOtherArray.toTypedArray() // converts int[] to Array<Int>
}