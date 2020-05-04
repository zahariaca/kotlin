fun main() {
    println(numJewelsInStones("aa", "ab"))
}

fun numJewelsInStones(J: String, S: String): Int {
    val jRange = J.toCharArray()
    var count = 0;

    S.forEach { it -> if (it in jRange) count++}
    return count
}
