/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */

fun main() {
    println(majorityElement(intArrayOf(2,2,1,1,1,2,2)))
}

fun majorityElement(nums: IntArray): Int {
    val mutableMap = mutableMapOf<Int,Int>()
    nums.forEach { mutableMap[it] = mutableMap[it]?.plus(1)?:1 }
    return mutableMap.entries
            .filter { it.value > nums.size/2 }
            .maxBy { it.value }!!.key
}
