package com.zahariaca.binaryio

import java.io.*

fun main() {
    val di = DataInputStream(FileInputStream("testfile.bin"))
    var si: String

    try {
        while(true) {
            si = di.readUTF()
            println(si)
        }
    }catch (e: EOFException) {

    }
}