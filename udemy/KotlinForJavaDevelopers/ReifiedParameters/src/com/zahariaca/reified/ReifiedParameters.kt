package com.zahariaca.reified

import java.math.BigDecimal

fun main() {
    val mixedList = listOf<Any>("string", 1, BigDecimal(22.5), "fall", BigDecimal(-123.45))
    val bigDecimalsOnly = getElementsOfType<String>(mixedList)
    println(bigDecimalsOnly)
}


// if you declare the function as inline, due to Kotlins Reification feature,
// the generic type parameter can be checked at runtime
inline fun <reified T> getElementsOfType(list: List<Any>): List<T> {
    var newList: MutableList<T> = mutableListOf();
    for (element in list) {
        if (element is T) {
            newList.add(element)
        }
    }

    return newList
}