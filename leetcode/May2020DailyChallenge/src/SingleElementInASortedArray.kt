fun main() {
    println(singleNonDuplicate(intArrayOf(1, 1, 2, 3, 3, 4, 4, 8, 8)))
}

fun singleNonDuplicate(nums: IntArray): Int {
    return bs(nums, 0, nums.size - 1)
}

fun bs(nums: IntArray, low: Int, high: Int): Int {
    if (low > high) {
        return -1
    }
    if (low == high) {
        return nums[low]
    }

    val mid = (low + high) / 2

    if (mid % 2 == 0) {
        if (nums[mid] == nums[mid + 1]) {
            return bs(nums, mid + 2, high)
        } else {
            return bs(nums, low, mid)
        }
    } else if (mid % 2 != 0) {
        if (nums[mid] == nums[mid - 1]) {
            return bs(nums, mid + 1, high)
        } else {
            return bs(nums, low, mid - 1)
        }
    }

    return -1
}

fun bsBetter(nums: IntArray): Int {
    var low = 0
    var high = nums.size - 1
    while(low < high){
        var mid = low + (high - low)/2
        if(mid % 2 == 1) mid--

        if(nums[mid] == nums[mid + 1]){
            low = mid + 2
        }else high = mid
    }

    return nums[high]
}