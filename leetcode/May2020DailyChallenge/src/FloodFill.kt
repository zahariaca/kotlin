fun main() {
    val image = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1))
    floodFill(image, 1, 1, 2)
}

fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
    if (image[sr][sc] == newColor) {
        return image
    }

    fill(image, sr, sc, image[sr][sc], newColor)
    return image
}

fun fill(image: Array<IntArray>, row: Int, col: Int, currentColor: Int, newColor: Int) {
    if (row < 0 || row >= image.size || col < 0 || col >= image[0].size || image[row][col] != currentColor) {
        return;
    }
    image[row][col] = newColor
    fill(image, row + 1, col, currentColor, newColor)
    fill(image, row - 1, col, currentColor, newColor)
    fill(image, row, col + 1, currentColor, newColor)
    fill(image, row, col - 1, currentColor, newColor)
}