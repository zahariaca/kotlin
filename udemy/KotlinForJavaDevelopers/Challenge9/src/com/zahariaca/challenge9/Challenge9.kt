package com.zahariaca.challenge9

import com.zahariaca.javacode.Employee

fun main() {

    val employee = Employee("Jane", "Smith", 2000)
    employee.lastName = "Jones"
    employee.salaryLast3Years = arrayOf(5000.24F, 4255.33F, 5666.56F).toFloatArray()
    println(employee)
}