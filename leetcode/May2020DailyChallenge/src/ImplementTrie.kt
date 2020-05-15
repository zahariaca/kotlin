fun main() {
    val trie = Trie()

    trie.insert("apple")
    println(trie.search("apple"))// returns true

    println(trie.search("app"))// returns false

    println(trie.startsWith("app")) // returns true

    trie.insert("app")
    println(trie.search("app"))// returns true

}
// TODO: read second option on: https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
class Trie() {
    /** Initialize your data structure here. */
    var root: TrieNode = TrieNode()


    /** Inserts a word into the trie. */
    fun insert(word: String) {
        var current = root
        for (c in word) {
            current = current.children.computeIfAbsent(c, { c -> TrieNode() })
        }
        current.isWord = true
    }

    /** Returns if the word is in the trie. */
    fun search(word: String): Boolean {
        var current = root
        for (c in word) {
            var node = current.children[c]
            if (node == null) {
                return false
            }
            current = node
        }
        return current.isWord
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    fun startsWith(prefix: String): Boolean {
        return searchPrefix(prefix) != null
    }

    private fun searchPrefix(prefix: String): TrieNode? {
        var children = root.children
        var t: TrieNode? = null
        for (c in prefix) {
            if (children.containsKey(c)) {
                t = children[c]!!
                children = t.children
            } else {
                return null
            }
        }
        return t
    }

}

class TrieNode() {
    val children = HashMap<Char, TrieNode>()
    lateinit var content: String
    var isWord: Boolean = false
}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */