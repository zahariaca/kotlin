fun main() {
    canConstruct("a", "b")
    canConstruct("aa", "ab")
    canConstruct("aa", "aab")
}

// 240ms
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val ransomNoteMap = hashMapOf<Char, Int>()
    val magazineMap = hashMapOf<Char, Int>()
    magazine.forEach { c -> magazineMap[c] = magazineMap[c]?.plus(1)?:1 }
    ransomNote.forEach { c -> ransomNoteMap[c] = ransomNoteMap[c]?.plus(1)?:1 }

    for ((k,v) in ransomNoteMap) {
        if (v <= magazineMap[k]?:0) {
            continue
        } else {
            return false
        }
    }

    return true;
}

// 400+ ms
fun canConstruct2(ransomNote: String, magazine: String): Boolean {
    var mutableMagazine = magazine
    for (c in ransomNote) {
        val s: String = c.toString()
        if (mutableMagazine.contains(s)) {
            mutableMagazine = mutableMagazine.replaceFirst(s, "")
        } else {
            return false;
        }
    }
    return true;
}



