fun main() {
    println(findComplement(5))
}

// 130ms
fun findComplement(num: Int): Int {
    val count = Integer.numberOfLeadingZeros(num)
    return num.inv().shl(count).shr(count)
}

// 200ms
fun findComplement2(num: Int): Int {
    var num = num
    var copy = num
    var pos = 0
    while (copy != 0) {
        num = num xor (1 shl pos)
        pos++
        copy = copy shr 1
    }
    return num
}