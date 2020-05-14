public class FloodFillJava {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }

        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    public void fill(int[][] image, int row, int col, int currentColor, int newColor) {
        if (row < 0 || row >= image.length || col < 0 || col >= image[row].length || image[row][col] != currentColor) {
            return;
        }

        image[row][col] = newColor;
        fill(image, row + 1, col, currentColor, newColor);
        fill(image, row - 1, col, currentColor, newColor);
        fill(image, row, col + 1, currentColor, newColor);
        fill(image, row, col - 1, currentColor, newColor);
    }
}
