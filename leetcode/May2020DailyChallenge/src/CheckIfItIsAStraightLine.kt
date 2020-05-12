fun main() {
    // coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
    var coordinates: Array<IntArray> = arrayOf();
    coordinates += intArrayOf(1,2)
    coordinates += intArrayOf(2,3)
    coordinates += intArrayOf(3,4)
    coordinates += intArrayOf(4,5)
    coordinates += intArrayOf(5,6)
    coordinates += intArrayOf(6,7)
    println(checkStraightLine(coordinates))

    // [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
    var coordinates2: Array<IntArray> = arrayOf()
    coordinates2 += intArrayOf(1,1)
    coordinates2 += intArrayOf(2,2)
    coordinates2 += intArrayOf(3,4)
    coordinates2 += intArrayOf(4,5)
    coordinates2 += intArrayOf(5,6)
    coordinates2 += intArrayOf(7,7)
    println(checkStraightLine(coordinates2))

}

fun checkStraightLine(coordinates: Array<IntArray>): Boolean {
    val firstPoint = coordinates[0]
    val secondPoint = coordinates[1]
    println(firstPoint.toList())
    val slopeFirstTwo = calculateSlope(firstPoint, secondPoint)
    for (index in 2 until coordinates.size){
        val nextSlope = calculateSlope(firstPoint, coordinates[index])
        if (slopeFirstTwo != nextSlope) {
            return false;
        }
    }
    return true
}
fun calculateSlope(firstPoint: IntArray, secondPoint: IntArray): Double = (secondPoint[1].toDouble() - firstPoint[1].toDouble()) / (secondPoint[0].toDouble() - firstPoint[0].toDouble())