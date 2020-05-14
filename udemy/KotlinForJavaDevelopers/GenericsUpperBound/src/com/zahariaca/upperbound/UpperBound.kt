package com.zahariaca.upperbound

import java.lang.Appendable
import java.lang.StringBuilder

fun main() {
    val ints= listOf(1,2,3,4,5)
    val shorts: List<Short?> = listOf(10,20,30,40)
    val floats: List<Float?> = listOf(200.3f, -523.55f)
    val strings = listOf("1", "2", "3")

    convertToInt(ints)
    convertToInt(shorts)
    convertToInt(floats)
//    convertToInt(strings)
    append(StringBuilder("String 1"), StringBuilder("String 2"))
    println()

//    printCollection(shorts)
//    printCollection(floats)
//    println()

    // in kotlin generics are also a compile time feature but we can do:
    if (strings is List<String>) {
        println("This list contains strings!")
    }
    // this can be done because the compiler can figure this out at compile time
    // like in java this cannot be done at runtime due to type erasure

    val listAny: Any = listOf("str1", "str2")
//    if (listAny is List<String>) -> compiler error, compiler cannot figure out instanceof

    // similar to java `strings instanceOf List`
    if (strings is List<*>) {
        println("Yes this is a list!")
    }

    if (listAny is List<*>) {
        println("List still contains strings")
        var strList = listAny as List<String>
        println(strList[1].replace("str", "string"))

    }
}

// T: Any means we will take anything NonNullable
fun <T: Any> printCollection(collection: List<T>) {
    for (item in collection) {
        println(item)
    }
}



// will accept items that inherit CharSequence AND Appendable
fun <T> append(item1: T, item2: T) where T: CharSequence, T: Appendable {
    println("Result is ${item1.append(item2)}")
}

fun <T: Number?> convertToInt(collection: List<T>) {
    for (num in collection) {
        println("${num?.toInt()}")
    }
}