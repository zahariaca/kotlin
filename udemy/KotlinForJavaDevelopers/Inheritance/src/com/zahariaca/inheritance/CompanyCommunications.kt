package com.zahariaca.inheritance

import java.time.Year

fun main(args: Array<String>) {
    println(CompanyCommunications.getTagLine())
    println(CompanyCommunications.getCopyrightLine())
//    val test = CompanyCommunications() -- cannot be instantiated, it is a singleton.
    println(SomeClass.accessPrivateVar()) // calling a companion object with its method, equivalent to class with static method

    val someClass1 = SomeClass.justAssign("this is the string as is")
    val someClass2 = SomeClass.upperOrLowerCase("this isn't the string as is", false)
    println(someClass1.someString)
    println(someClass2.someString)

    var thisIsMuttable = 45

    // Third use case for object keyword - anonymous classes
    // this instance is not a singleton, it is created and then discarded
    // we can implement multiple interfaces, unlike java which can implement just one
    wantsSomeInterface(object : SomeInterface {
        override fun mustImplement(num: Int): String {
            thisIsMuttable++
            return "This is from mustImplement: ${num * 100}"
        }
    })
    println(thisIsMuttable)


    // -------------- Enums ----------------------
    println(Department.ACCOUNTING.getDepartmentInfo())

    // private modifier means it can only be seen in the same file
    topLevel("I'm private")
}

enum class Department(val fullName: String, val numEmployees: Int) {
    HR("Human Resources", 5),
    IT("Information Technology", 10),
    ACCOUNTING("Accounting", 3),
    SALES("Sales", 20);

    fun getDepartmentInfo() = "The $fullName department has $numEmployees employees"
}

// Top Level Functions
private fun topLevel(str: String) {
    println("Top level function: $str")
}

// First use case for object keyword
// object is equivalent to singleton, there should only be 1 instance of this
// object classes cannot have constructors
// can extend classes or implement interfaces
object CompanyCommunications {
    val currentYear = Year.now()

    fun getTagLine() = "Our company rocks!"
    // internal means everything in the same module can use this method, less restrictive than private
    internal fun getCopyrightLine() = "Copyright \u00A9 $currentYear Our Company. All Rights reserved."
}

// ------------ companion objects ------------
class SomeClass private constructor(val someString: String) {
    // Second use case for object keyword: companion objects can function like a Factory
    // constructor is not accessible from outside this class, but instances can be created with the companion object
    // which acts as a Factory
    companion object /*can have a name here, otherwise Companion*/ { // equivalent to a class in java with `public static String accessPrivateVar()` as a static method
        private var privateVar = 6
        fun accessPrivateVar() = println("I'm accessing privateVar: $privateVar");
        fun justAssign(str: String) = SomeClass(str)
        fun upperOrLowerCase(str: String, lowerCase: Boolean): SomeClass {
            if (lowerCase) {
                return SomeClass(str.toLowerCase())
            } else {
                return SomeClass(str.toUpperCase())
            }
        }
    }
}

interface SomeInterface {
    fun mustImplement(num: Int): String
}

fun wantsSomeInterface(si: SomeInterface) {
    println("Printing from wantsSomeInterface ${si.mustImplement(22)}")
}