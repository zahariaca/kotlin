package com.zahariaca.inheritance

// open = makes class or function public and NOT final (default is public and final)
// abstract includes open

fun main(args: Array<String>) {
    val laserPrinter = LaserPrinter("Brother 1234", 15)
    laserPrinter.printModel()

    val somethingElse = SomethingElse("whatever")

}

// class by default is public final, with 'open; it is no longer final
// if it is abstract, it is also open, it can be extended
abstract class Printer(val modelName: String) {
    // without 'open' this is not overrideable, because it also is final
    open fun printModel() = println("The model name of this printer is: $modelName")
    abstract fun bestSellingPrice(): Double
}

open class LaserPrinter(modelName: String, ppm: Int) : Printer(modelName) {
    // final will make the inheritance stop here, children of LaserPrinter will not be able to override this method
    // they need to use the parent method
    final override fun printModel() = println("The model name of this laser printer is: $modelName")
    override fun bestSellingPrice(): Double = 129.99
}

class SpecialLaserPrinter(modelName: String, ppm: Int) : LaserPrinter(modelName, ppm) {

}

// ------------- Secondary constructor inheritance -----------------
open class Something : SubInterface {
    val somePropery: String
    override val number: Int = 25

    constructor(someParameter: String) {
        somePropery = someParameter
        println("I'm in the parent's constructor")
    }

    override fun mySubFunction(num: Int): String {
        TODO("Not yet implemented")
    }

    override fun myFunction(str: String): String {
        TODO("Not yet implemented")
    }
}

class SomethingElse : Something {
    constructor(someOtherParameter: String) : super(someOtherParameter) {
        println("I'm in the child's constructor")
    }
}

// ---------------- Data class inheritance ---------------------
// data classes cannot be extended, or be abstract or innerclass
// we cannot use open
//open data class DataClass(val number: Int) {}

// ------------------ Interfaces ----------------
// interfaces are extendable by default, do not need open
interface MyInterface {
    val number: Int

    //    val number2: Int = 50 -- error, cannot initialize a property in an interface
    val number2: Int
        get() = number * 100

    fun myFunction(str: String): String
}

interface SubInterface : MyInterface {
    fun mySubFunction(num: Int): String

}