import java.lang.StringBuilder

fun main() {
    println(removeKdigits("1432219", 3))
}

fun removeKdigits(num: String, k: Int): String {
    if (k == 0) return num
    if (num.length == k) return "0"

    val sb = StringBuilder(num)

    for (i in 0 until k) {
        var j = 0
        while(j< sb.length-1 && sb[j] <= sb[j+1]) {
            j++
        }
        sb.delete(j, j+1)
    }
    while( sb.length >1 && sb[0] == '0') {
        sb.delete(0,1)
    }

    if (sb.isEmpty()) return "0"

    return sb.toString()
}
