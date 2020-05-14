package com.zahariaca.lambdaexpressions

fun main() {
    run { println("I'm in a lambda") }
    val employees = listOf(
        Employee("John", "Smith", 2012),
        Employee("Jane", "Wilson", 2015),
        Employee("Mary", "Johnson", 2010),
        Employee("Mike", "Jones", 2002)
    )
    println(employees.minBy { it.startYear })
    println(employees.minBy(Employee::startYear))

    var num = 10
    run {
        num += 15
        println(num)
    }

    run {
        topLevel()
    }
    run(::topLevel)
    println(countTo100())
    println(countTo100Better())

    println(findByLastName(employees, "Wilson"))
    println(findByLastName(employees, "Smitty"))

    "Some string".apply somestringLabel@{
        "Another String".apply {
            println(toLowerCase()) // refers to "Another String" because it is in it's block
            println(this@somestringLabel.toUpperCase())
        }
    }
}

fun findByLastName(employees: List<Employee>, lastName: String) {
    employees.forEach returnBlock@ {
        if (it.lastName == lastName) {
            println("Yes there's an employee with the last name: $lastName")
//            return this would return from the function, not jsut the lambda
            // if we want to return just from the lambda we use labels
            return@returnBlock
        }
    }

    println("No there is no employee with the last name: $lastName")
}

fun countTo100(): String {

    val numbers = StringBuilder()
    for (i in 1..99) {
        numbers.append(i)
        numbers.append(", ")
    }
    numbers.append(100)
    return numbers.toString()
}

fun countTo100Better(): String {
    val numbers = StringBuilder()
    return with(numbers) {
        for (i in 1..99) {
            append(i)
            append(", ")
        }
        append(100)
        toString()
    }
}

fun countTo100Better2() =
    with(StringBuilder()) {
        for (i in 1..99) {
            append(i)
            append(", ")
        }
        append(100)
        toString()
    }

fun countTo100BApply() =
    StringBuilder().apply {
        for (i in 1..99) {
            append(i)
            append(", ")
        }
        append(100)
        toString()
    }


fun topLevel() = println("I'm in a function")

fun useParameter(employees: List<Employee>, num: Int) {
    employees.forEach {
        println(it.firstName)
        println(num)
    }
}

data class Employee(val firstName: String, val lastName: String, val startYear: Int) {

}