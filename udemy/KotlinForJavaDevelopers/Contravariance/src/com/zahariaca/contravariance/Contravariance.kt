package com.zahariaca.contravariance

fun main() {
    val roseTender = object : FlowerCare<Rose> {
        override fun prune(flower: Rose) = println("I'm pruning a rose!")
    }
    val daffodilTender = object : FlowerCare<Daffodil> {
        override fun prune(flower: Daffodil) = println("I'm pruning a daffodil!")
    }
    val flowerTender = object : FlowerCare<Flower> {
        override fun prune(flower: Flower) = println("I'm tending a ${flower.name}!")
    }

    val roseGarden = Garden<Rose>(listOf(Rose(), Rose()), flowerTender)
    roseGarden.tendFlower(0)

    val daffodilGarden = Garden<Daffodil>(listOf(Daffodil(), Daffodil(), Daffodil()), flowerTender)
    daffodilGarden.tendFlower(2)

}

// Contravariance is the opposite of covariance
// when we use covariance we say: we want to accept a Flower and all of its subclasses
// when we use contravariance we say: we want to accept a Rose and all of its superclasses
class Garden<T : Flower>(val flowers: List<T>, val flowerCare: FlowerCare<T>) {
    fun pickFlower(i: Int) = flowers[i]
    fun tendFlower(i: Int) = flowerCare.prune(flowers[i])
}

open class Flower(val name: String) {

}

class Rose : Flower("Rose") {

}

class Daffodil : Flower("Daffodil") {

}

// in -> contravariance
interface FlowerCare<in T> {
    fun prune(flower: T)
    // using Contravariance comes with a price, we can no longer use the T as a return parameter
//    fun pick(): T
}