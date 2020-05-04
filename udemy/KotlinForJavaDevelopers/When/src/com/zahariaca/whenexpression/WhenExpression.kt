package com.zahariaca.whenexpression

import java.math.BigDecimal

enum class Season {
    SPRING, SUMMER, FALL, WINTER
}

fun main() {
    val num = 100
    // when is equivalent to switch in java but with more functionality
    when (num) {
        in 100..199 -> println("in range 100..199")
        200, 400 -> println("200")
        300 -> println("300")
        else -> println("Doesn't match anything")
    }

    val y = 10
    when (num) {
        y + 80 -> println("90")
        y + 90 -> println("100")
        300 -> println("300")
        else -> println("Doesn't match anything")
    }

    val obj: Any = "I'm a string"
    val obj2: Any = BigDecimal(25.2)
    val obj3: Any = 45

    val something: Any = obj2
    // verbose for kotlin
    if (something is String) {
        println(something.toUpperCase())
    } else if (something is BigDecimal) {
        println(something.remainder(BigDecimal(10.5)))
    } else if (something is Int) {
        println("${something - 25}")
    }

    // smart casting still works here
    var z = when (something) {
        is String ->  {
            println(something.toUpperCase())
            1
        }
        is BigDecimal -> {
            println(something.remainder(BigDecimal(10.5)))
            2
        }
        is Int -> {
            println("${something - 25}")
            3
        }
        else -> {
            println("It's something")
            -1
        }
    }
    println(z)

    val timeOFYear = Season.WINTER
    // when we have exhaustive branches (like here with a Enum) we do not need else
    // if we where not using all of our enum values, we would need else
    val str = when (timeOFYear) {
        Season.SPRING -> "Flowers are blooming"
        Season.SUMMER -> "It's hot!"
        Season.FALL -> "It's getting cooler"
        Season.WINTER -> "I need a coat"
    }
    println(str)

    val num2 = -50

    if(num < num2) {
        println("num is less than num2")
    } else if(num > num2) {
        println("num is greater than num2")
    } else {
        println("num is equal to num2")
    }

    when {
       num < num2 -> println("num is less than num2")
       num > num2 -> println("num is greater than num2")
       else -> println("num is equal to num2")
    }
}