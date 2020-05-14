package com.zahariaca.declarations

fun main(args: Array<String>) {
    // Declare two immutable String variables called hello1 and hello2. Assign "Hello" to both variables
    val hello1 = "Hello"
    val hello2 = "Hello"

    // Using one line of code, test wether hello1 and hello2 are referentially equal and print result
    println("hello1 is referentially equal to hello2: ${hello1 === hello2}")

    // Determine if they are structurally equal
    println("hello1 is structurally equal to hello2: ${hello1 == hello2}")

    // Declare a mutable variable of type int and assign it the value of 2988
    var number = 2988

    // Declare an immutable variable of type any and assign it "The Any Type is the root of the Kotlin class hierarchy",
    // using smart cast print out the uppercase string
    val anyVariable: Any = "The Any Type is the root of the Kotlin class hierarchy"
    if (anyVariable is String) {
        println("Uppercase value is ${anyVariable.toUpperCase()}")
    }

    // using one line of code, print out the following and make sure it is nicelly indented
    //      1
    //     11
    //    111
    //   1111
    println("""    1
              |   11
              |  111
              | 1111
              |11111
    """.trimMargin())
}