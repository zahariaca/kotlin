package com.zahariaca.covariance

fun main() {
    val shortList: List<Short> = listOf(1, 2, 3, 4, 5)
    convertToInt(shortList)
    println()
}

fun convertToInt(collection: List<Number>) {
    for (num in collection) {
        print("${num.toInt()} ")
    }
}

fun waterGarden(garden: Garden<Flower>) {

}

fun tendGarden(roseGarden: Garden<Rose>) {
    waterGarden(roseGarden) // this would not be possible if T in Garden was not declared as out, because subtype is not accepted
}

open class Flower {

}

class Rose : Flower() {

}

// out keyword make T: Flower Covariant
// if not covariant a Garden<Flower> can not be replaced by a subtype like Garden<Rose>
// Covariant classes are restricted in what we can do with them, similar to Immutable Classes (which are also covariant)
class Garden<out T : Flower>(val something: T) {
    val flowers: List<T> = listOf()

    fun pickFlower(i: Int): T = flowers[i]
//    fun plantFlower(flower: T) {} -> Compiler exception: Type parameter T is declared as 'out' but occurs in 'in' position in type T
    // in the above we try to use T in the "IN" position, which is no longer allowed, since we declared T as "out" (covariant)
    // this means we can use T as return type, but we cannot accept T in our functions
}