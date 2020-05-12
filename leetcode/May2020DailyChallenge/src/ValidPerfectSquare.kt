fun main() {
    val sol = SolutionVPS()
    println(sol.isPerfectSquare(15))
    println(sol.isPerfectSquare(2147483647))
    println(sol.isPerfectSquare(5776))
    println(sol.isPerfectSquare(5776000))
    println(sol.isPerfectSquare(577600026))
}

class SolutionVPS {
    //    result = num
//        # stop while until < or ==
//        while result * result > num:
//            result = (result + num // result) // 2
//
//        return result * result == num
    fun isPerfectSquare(num: Int): Boolean {
        var res = num.toLong()
        while (res * res > num) {
            res = (res + num / res) / 2
        }

        return res * res == num.toLong()
    }
}