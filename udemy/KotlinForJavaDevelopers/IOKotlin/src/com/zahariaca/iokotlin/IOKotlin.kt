package com.zahariaca.iokotlin

import java.io.File

fun main() {
    val lines = File("textfile").reader().readLines()
    println(lines)
    lines.forEach{println(it)}

    val reader = File("textfile").reader()
    val lines2 = reader.use { it.readText() }
    val bf =  File("textfile").bufferedReader()

    File("textfile").reader().forEachLine { println(it) }


    // equivalent to:
    // new InputStreamReader(new FileInputStreamReader(new File("textfile")), "UTF-8)
}