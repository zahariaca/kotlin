package com.zahariaca.generics

import java.math.BigDecimal

fun main() {
    val list = mutableListOf("Hello")
    list.add("another string")
    printCollection(list)

    val bdList = mutableListOf(
        BigDecimal(-33.45),
        BigDecimal(3503.99),
        BigDecimal(0.234)
    )
    printCollection(bdList)
    bdList.printCollectionExtension()
}

fun <T> printCollection(collection: List<T>) {
    for (item in collection) {
        println(item)
    }
}

fun <T> List<T>.printCollectionExtension() {
    for (item in this) {
        println(item)
    }
}