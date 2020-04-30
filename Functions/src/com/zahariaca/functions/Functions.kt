package com.zahariaca.functions

// default return type for a function is Unit
fun main(args: Array<String>) {
    println(labelMultiply(3, 4, "The result is:"))
    println(labelMultiply2(3, 4))

    val emp = Employee("Jogn")
    println(emp.upperCaseFirstName())

    val car1 = Car("blue", "Toyota", 2015)
    val car2 = Car("red", "Ford", 2016)
    val car3 = Car("grey", "Ford", 2017)
    printColors(car1, car2, car3, str = "Color: ")

    val manyCars = arrayOf(car1, car2, car3)
//    printColors(manyCars) cannot pass car[] to vararg function, like we would in Java
    printColors(*manyCars, str = "Some String") // * is the spread operator

    val moreCars = arrayOf(car2, car3)
    val car4 = car2.copy(color = "green")
    val lotOfCars = arrayOf(*manyCars, *moreCars, car4)

    println()
    for (c in lotOfCars) {
        println(c)
    }

    // ------------------- Functions Extensions --------------
    // java way of using a utils method
    println(Utils().upperFirstAndLast("this is all in lowercase"))

    // kotlin way, it is just an illusion, we are not actually adding this method to the String class
    val s = "this is all in lowercase"
    println(s.upperFirstAndLast())

    // ---------------- inline functions -------------------
}

fun String.upperFirstAndLast(): String {
    val upperFirst = substring(0, 1).toUpperCase() + substring(1)
    val upperLast = upperFirst.substring(
        upperFirst.length - 1,
        upperFirst.length
    ).toUpperCase()
    return upperFirst.substring(0, upperFirst.length - 1) + upperLast
}

// valid because println() doesn't return anything
//fun main(args: Array<String>) =
//    println(labelMultiply(3, 4, "The result is:"))

fun whatever() = 3 * 4

// a function with {} is a function with a block body
fun labelMultiply(operand1: Int, operand2: Int, label: String): String {
    return ("$label ${operand1 * operand2}")
}

// simplified version of the above
// this is a function with an expression body
fun labelMultiply2(
    operand1: Int, operand2: Int,
    label: String = "The answer is:"
) =
    "$label ${operand1 * operand2}"

class Employee(val firstName: String) {
    fun upperCaseFirstName() = firstName.toUpperCase()
}

fun printColors(vararg cars: Car, str: String) {
    for (car in cars) {
        println(car)
    }
}

data class Car(val color: String, val model: String, val year: Int) {}