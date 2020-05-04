package com.zahariaca.ifexpression

// there is no ternary operator in kotlin, because the
// if is a expression an can act like a ternary operator
fun main(args: Array<String>) {
    var num: Int
    val someCondition = true
    if (someCondition) {
        num = 50
    } else {
        num = 592
    }

//    num = someCondition ? 50 : 592

    num = if (someCondition) 50 else 592

    // if used as an expression, then it must have else branch
    val num2 = if (someCondition) {
        println("Something")
        50
    } else {
        println("Something else")
        592
    }
    println(num2)

    // can be without else if not used as an expression
    if (someCondition) {
        println("Blabla")
    }

    println("This is the result of the if expression: ${if (someCondition) {
        println("Something")
        50
    } else {
        println("Something else")
        592
    }}")
}