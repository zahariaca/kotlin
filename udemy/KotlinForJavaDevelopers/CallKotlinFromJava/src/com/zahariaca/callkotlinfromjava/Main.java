package com.zahariaca.callkotlinfromjava;

import com.zahariaca.kotlincode.Car;
import com.zahariaca.kotlincode.SingletonObj;
import com.zahariaca.kotlincode.StaticCar;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        StaticCar.topLevel();
        StaticCar.print("print this java String");

        Car car = new Car("blue", "BMW", 2011);
        System.out.println(car.model);
        car.setColor("purple");
        System.out.println(car.getColor());

        Car.Companion.carComp();
        Car.carComp();

        SingletonObj.INSTANCE.doSomething();
        SingletonObj.doSomething();

        System.out.println("isAuto= " + Car.isAuto);
        System.out.println("const= " + Car.constant);

//        car.printMe(null);

        try {
            StaticCar.doIO();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StaticCar.defaultArgs("The number is: ");
    }
}
