fun main() {
    /*Input: N = 2, trust = [[1,2]]
      Output: 2*/
    println(findJudge(2, arrayOf(intArrayOf(1, 2))))
    /*Input: N = 3, trust = [[1,3],[2,3]]
      Output: 3*/
    println(findJudge(3, arrayOf(intArrayOf(1, 3), intArrayOf(2, 3))))
    /*Input: N = 3, trust = [[1,3],[2,3],[3,1]]
      Output: -1*/
    println(findJudge(3, arrayOf(intArrayOf(1, 3), intArrayOf(2, 3), intArrayOf(3, 1))))
    /*Input: N = 3, trust = [[1,2],[2,3]]
      Output: -1*/
    println(findJudge(3, arrayOf(intArrayOf(1, 2), intArrayOf(2, 3))))
    /*Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
      Output: 3*/
    println(findJudge(4, arrayOf(intArrayOf(1, 3), intArrayOf(2,3), intArrayOf(2,4), intArrayOf(4,3))))
}

fun findJudge(N: Int, trust: Array<IntArray>): Int {
    val arr = IntArray(N) { 0 }
    for (pair in trust) {
        arr[pair[0] - 1] = -1
        arr[pair[1]-1] += 1
    }
    for(index in arr.indices) {
        if (arr[index] == N-1) {
            return index+1
        }
    }
    return -1
}
