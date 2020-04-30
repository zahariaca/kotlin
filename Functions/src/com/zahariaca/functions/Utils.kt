package com.zahariaca.functions

class Utils {
    fun upperFirstAndLast(str: String): String {
        val upperFirst = str.substring(0, 1).toUpperCase() + str.substring(1)
        val upperLast = upperFirst.substring(
            upperFirst.length - 1,
            upperFirst.length
        ).toUpperCase()
        return upperFirst.substring(0, upperFirst.length - 1) + upperLast
    }
}