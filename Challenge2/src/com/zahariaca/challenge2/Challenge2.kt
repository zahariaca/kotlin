package com.zahariaca.challenge2

import com.zahariaca.javacode.DummyClass

fun main() {
    // 1. Declare a non-nullable float variable two ways,
    // and assign it the value of -3874.384
    val float1 = -3874.384f
    val float2: Float = -3874.384f

    // 2. Now change both of those variables declarations into nullable variables
    val float3: Float? = -3874.384f
    val float4: Float? = -3874.384f

    // 3. Now declare an array of type non-nullable Short. Make it size 5,
    // and assign it the values 1,2,3,4,5
    val shorts = arrayOf<Short>(1,2,3,4,5)

    // 4. Now declare an array of nullable Ints and initialize it with the values
    // 5,10,15..200
    val nullableInts = Array<Int?>(40) {i -> (i+1)*5}

    // 5. YOu have to call a Java method with the following signature from Kotlin:
    // public void method1(char[] charArray). Declare an array that you could
    // pass to the method and initialize it with the values a,b,c
    val charArray = charArrayOf('a','b', 'c')
    DummyClass().method1(charArray)

    // 6. given the following code:
    val x: String? = "I AM IN UPPERCASE"
    // Using one line of code, declare another string variable
    // and assing it x.toLowerCase() when x isn't null
    // and the string "I give up!" when x is null
    val y = x?.toLowerCase() ?: "I give up!"

    // 7. Now use the let function to
    // (a) lowercase the string,
    // and then
    // (b) replace "am" with "am not" in the result
    x?.let { println(it.toLowerCase().replace("am", "am not")) }

    // 7. You're really, really confident that the variable myNonNullVariable can't contain null
    // Change the following code to assert that myNonNullVariable isn't null
    // (and shoot yourself in the foot!)
    val myNonNullVariable: Int? = null
    myNonNullVariable!!.toDouble()
}