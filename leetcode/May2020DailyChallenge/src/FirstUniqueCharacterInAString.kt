fun main() {
    println(firstUniqChar("leetcode"))
    println(firstUniqChar("dddccdbba"))
    println(firstUniqChar("loveleetcode"))
}



fun firstUniqChar(s: String): Int {
    if (s.isEmpty()) return -1
    val arr = IntArray(26)
    s.indices.forEach { arr[s[it] - 'a']++ }
    s.indices.forEach { if (arr[s[it] - 'a'] == 1) return it }
    return -1
}

fun firstUniqChar2(s: String): Int {
    if(s.isEmpty()) return -1
    var arr = IntArray(26)

    for (index in s.indices) {
        arr[s[index]-'a']++
    }

    for (index in s.indices) {
        if (arr[s[index]-'a'] == 1) return index
    }

    return -1
}

fun firstUniqCharInefficient(s: String): Int {
    if (s.isEmpty()) return -1

    val linkedMap = LinkedHashMap<Char, ArrayList<Int>?>()

    for (index in s.indices) {
        val c = s[index]
        if (linkedMap.containsKey(c)) {
            val arrayList = linkedMap[c]
            arrayList?.add(index) ?: arrayListOf(index)
            linkedMap[c] = arrayList
        } else {
            linkedMap[c] = arrayListOf(index)
        }
    }

    for (v in linkedMap.values) {
        return if (v!!.size > 1) continue else v.first()
    }
    return -1
}