fun main() {
    // print 5, 10, 15, 20 ,25 ... 50
    for (num in 5..50 step 5) {
        print("$num ")
    }
    println()

    // print number from -500 to 0 inclusive
    for (num in -100..0) {
        print("$num ")
    }
    println()

    // fibonacci 0,1,1,2,3,5,
    // print first 15 numbers in fibonacci
    var total: Int
    var secondToLast = 0
    var last = 1

    print("$secondToLast $last ")
    for (i in 1..13) {
        total = secondToLast + last
        print("$total ")
        secondToLast = last
        last = total
    }
    println()

    //
    iLoop@ for (i in 1..5) {
        println(i)
        if (i == 2) break
        for (j in 11..20) {
            println(j)
            for (k in 100 downTo 90) {
                println(k)
                if (k == 98) continue@iLoop
            }
        }
    }

    // declare int var called num, and give it an int value
    // declare a var called dnum (doulbe, and assign it
    // if num > 100 assign dnum -234.567
    // if num < 100 assign dnum 4444.555
    // if num == 100, assing dnum 0.0
    // print dnum
    val num = 122
    var dnum: Double
    dnum = when {
        num > 100 -> -234.567
        num < 100 -> 4444.555
        else -> 0.0
    }
    println(dnum)
}