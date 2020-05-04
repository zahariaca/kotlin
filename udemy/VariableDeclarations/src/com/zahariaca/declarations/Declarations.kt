package com.zahariaca.declarations

//Type alias declariation is done above all other code, including classes
typealias EmployeeSet = Set<Employee>

fun main(args: Array<String>) {
    val number = 25 // number will be an int
    val numberShort: Short = 25 // number will be a short
    val numberWithInference: Int
    // number = 20 -> Error val is immutable
    var numberMutable = 25
    // val should be used as often as possible, immutability is expected
    val employee1 = Employee("Lynn Jones", 500)
    employee1.name = "Lynn Smith"
    // employee1 = Employee("Tim Watson", 100) -> error, cannot be assigned

    val employee2: Employee
    val number2 = 100

    if (number < number2) {
        employee2 = Employee("Jane SMith", 400)
    } else {
        employee2 = Employee("Mike watson", 650)
    }

    // numberMutable = "ajdasf" -> cannot cahnge varibale type on the fly, numberMutable was instantiated as int, can not become string

    val employees: EmployeeSet

    val names = arrayListOf("test")
    println(names[0]) // equivalent to names.get(1) but more concise, can be done with maps too

    // ---------------- quick differences ------------------
    // ternary operator doesn't exist in kotlin
    // condition ? true : false

    // this for loop also doesn't exist
    // for (int i = 0, i < 20, i++){}

    // static functions and static variables are dealt with differently in kotlin, concept is there but keyword isn't
    // 'new' doesn't exist

    // ---------------- equality in kotlin ------------------
    val employeeOne = Employee("Sma Smith", 1)
    val employee3 = Employee("John Smith", 2)
    val employee4 = Employee("John Smith", 2)
    println(employeeOne == employee3) // false
    println(employee3 == employee4) // true // == is equivalent to .equals, does not do reference equality like in Java
    println(employeeOne.equals(employee3)) // false
    println(employee3.equals(employee4)) // true
    // to check for referential equality we have ===, same as == in java
    println(employeeOne === employee3) // false
    println(employee3 === employee4) // false
    val employeeCopy = employee3
    println(employeeCopy === employee3) // true

    println(employeeCopy != employee3) // false
    println(employeeCopy !== employee3) // false
    println(employeeOne != employee3) // true
    println(employeeOne !== employee3) // true

    // ---------------- bit operators ------------------
    val x = 0x00101101
    val y = 0x11011011

    x or y // x | y - operator '|' doesn't exist in kotlin, use 'or'
    x and y // x & y - operator '|' doesn't exist, use 'and'
    x xor y

    val something: Any = employee4
    if (something is Employee) { // equivalent to instanceof
        // casting to employee, here casting is not needed because of smart casting, after we did is, and it was true
        // kotlin automatically casts it
        // val newEmployee = something as Employee
        println(something.name)
    }


    // ----------------  strings and string templates ------------------
    println(employee1)

    val change = 4.22
    println("to show the value of change, we use \$change, $$change, $ ")

    val numerator = 10.99
    val denominator = 20.00
    println("the value of $numerator divided by $denominator is ${numerator / denominator}")

    println("The employee's id is ${employee1.id}")


    val filePath = """c:\somedir\somedir2""" // triple quoted strings, automatically escape characters, equivalent to "c:\\somedir\\somedir2"
    val eggName = "Humpty"
    val nurseryRhyme = """$eggName Dumpty sat on a wall
        |bla bla bla
        |bla bla bla
        |bla""".trimMargin()
    println(nurseryRhyme)
}

class Employee(var name: String, val id: Int) {
    override fun equals(other: Any?): Boolean {
        if (other is Employee) {
            return name == other.name && id == other.id
        }
        return false
    }

    override fun toString(): String {
        return "Employee(name='$name', id=$id)"
    }


}