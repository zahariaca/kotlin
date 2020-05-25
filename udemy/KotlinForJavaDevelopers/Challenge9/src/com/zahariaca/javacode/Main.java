package com.zahariaca.javacode;

import com.zahariaca.kotlincode.Challenge;
import com.zahariaca.kotlincode.EmployeeKotlin;
import com.zahariaca.kotlincode.KotlinStuff;

public class Main {
    public static void main(String[] args) {
        KotlinStuff.sayHelloToJava("student");

        EmployeeKotlin employee = new EmployeeKotlin("John", "Smith", 2000);
        employee.startYear = 2009;

        Challenge.doMath(5,4);

        employee.takesDefault("arg1");
    }
}
