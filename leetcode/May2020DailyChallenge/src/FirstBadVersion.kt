fun main() {
    println(firstBadVersion(4))
}

fun firstBadVersion(n: Int) : Int {
    return binarySearch(n)
}

fun binarySearch(n: Int): Int {
    var low = 0
    var high = n
    var target = 0

    while (low <= high) {
        target = low + (high-low) / 2

        if (!isBadVersion(target)) {
            low = target + 1
        } else {
            high = target - 1
        }
    }
    if (!isBadVersion(target)) {
        return target + 1
    }
    return target
}

fun isBadVersion(n: Int): Boolean {
    return n >= 12
}