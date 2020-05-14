package com.zahariaca.anothermodulepackage

import com.zahariaca.inheritance.CompanyCommunications as Comm
import com.zahariaca.inheritance.Department
import com.zahariaca.inheritance.topLevel as tp

fun main(args: Array<String>) {
    tp("Hello from another module")
    println(Comm.getCopyrightLine())
    println(Department.HR.getDepartmentInfo())
}