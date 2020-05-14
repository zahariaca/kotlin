package com.zahariaca.usesitevariance

fun main() {
    val cars1 = mutableListOf(Car(), Car())
    val cars2: MutableList<Car> = mutableListOf()
    copyCars(cars1, cars2)

    val fords1 = mutableListOf(Ford(), Ford())
    val fords2: MutableList<Ford> = mutableListOf()
    copyCars(fords1, fords2)
    copyCars(fords1, cars2)

    val cars3 = mutableListOf<Car>(Ford(), Ford())
}

open class Car {

}

fun <T: Car> copyCars(source: MutableList<out T>, destination: MutableList<T>) {
    for (car in source) {
        destination.add(car)
    }
}

class Toyota: Car() {}
class Ford: Car() {}