package com.zahariaca.nullreferences

fun main() {
    // kotlin has the concept of nullable types, you need to tell
    // the compiler that a type is nullable,
    // this is done by following the type with a ? -> String?
    val str: String? = null
    // what we can do with this variable is restricted
    val str1: String? = "This isn't null"
    // str1.toUpperCase() this results in a compiler error, we cannot use this method because the variable is nullable
    // java way:
    if (str1 != null) {
        str1.toUpperCase()
    } else {
        null
    }

    // ----------------- Safe access expression ?. ------------------
    // in kotlin we have a safe access expression, equivalent to above java way check:
    str1?.toUpperCase() // with ? we are saying, this could be null so generate a null check behind the scenes and only call the method if it isn't null
    // what happens when the variable actually is null
    println("What happens when we do this: ${str?.toUpperCase()}") // we do not get a NPE, we get the value null

    // java way
    /*if (bankBranch != null) {
        Address address = bankBranch.getAddress();
        if(address != null) {
            Country country = address.getCountry();
            if(country != null) {
                String countryCode = country.getCountryCode();
            }
        }
    }*/

    // kotlin shorthand way:
    // val countryCode: String? = bankBranch?.address?.country?.countryCode

    // ------------------ Elvis operator ?: ------------------
    // we can assign a default value if we don't want to get null from a safe access expression like the above
    // with the Elvis operator: ?:
    val str2 = str ?: "This is the default value"
    val str22 = str?.toUpperCase() ?: "This is the default value"
    println(str2)
    // val countryCode: String? = bankBranch?.address?.country?.countryCode ?: "CountryCodeDefault"

    val something: Any = arrayOf(1,2,3,4)
    val str3 = something as? String // as? = safe cast operator, if it cannot cast successfully (here int to string) it will return null
    println(str3)
    println(str3?.toUpperCase())

    // ----------------------- !! operator -----------------
    // if you are sure that a variable is NOT null, you can tell the compiler that you are sure adn stop using the ? operator
    // you use !!
    // val str4 = str!!.toUpperCase()
    // with !! you will see a NPE exception, this can be done to shortcircuit your code, if you want it to throw an exception
    // println(str4.toBigDecimal())

    // this will throw a NPE, but a KotlinNPE
    // for bellow: the NPE will be thrown at line 57, where we make the assertion with !! and not at line 57 where we call a function on a null
    // val str5 = str!!
    // val str6 = str5.toUpperCase()

    // bad practice, for the bellow you would not know which one is throwing null, if you string them along like that
    // val country = bankBranch!!.address!!.country!!

    // --------------------- let operator --------------------
    // if (str != null) {
    //    printText(str)
    // }
    // let operator, if str1 is not null then it will call the function, otherwise it will not
    // let receives a lambada expression, it, refers to the variable itself, equivalent to it -> printText(it)
    str1?.let { printText(it) } ?: printText("1");
    // str?.let { printText(it) } ?: printText("1"); -> can assign default functionality in case it is null, with ?:

    val str7: String? = null
    val str8 = "This is not null"
    // here we are essentially calling the .equals, but we do not get an error even though str7 is null
    // the == is a safe operator
    println(str7 == str8)

    // ------------- Array of nulls  -------------------
    val nullableInts = arrayOfNulls<Int?>(5)
    for (i in nullableInts) {
        println(i)
    }
}

fun printText(text: String) {
    println(text)
}