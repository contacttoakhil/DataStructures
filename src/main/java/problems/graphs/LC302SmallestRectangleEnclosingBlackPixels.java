package main.java.problems.graphs;

/***
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 * For example, given the following image:
 * [
 *   "0010",
 *   "0110",
 *   "0100"
 * ]
 * and x = 0, y = 2,
 * Return 6.
 */
public class LC302SmallestRectangleEnclosingBlackPixels {

    // Using DFS
    private int top, bottom, left, right;
    public int minArea(char[][] image, int x, int y) {
        if(image.length == 0 || image[0].length == 0) return 0;
        top = bottom = x;
        left = right = y;
        dfs(image, x, y);
        return (right - left) * (bottom - top);
    }
    private void dfs(char[][] image, int x, int y){
        if(x < 0 || y < 0 ||
                x >= image.length || y >= image[0].length || image[x][y] == '0')
            return;
        image[x][y] = '0'; // mark visited black pixel as white
        top = Math.min(top, x);
        bottom = Math.max(bottom, x + 1);
        left = Math.min(left, y);
        right = Math.max(right, y + 1);
        dfs(image, x + 1, y);
        dfs(image, x - 1, y);
        dfs(image, x, y - 1);
        dfs(image, x, y + 1);
    }

    //Using Binary Search
    private char[][] image;
    public int minAreaUsingBS(char[][] iImage, int x, int y) {
        image = iImage;
        int m = image.length, n = image[0].length;
        int top = search(0, x, 0, n, true, true);
        int bottom = search(x + 1, m, 0, n, false, true);
        int left = search(0, y, top, bottom, true, false);
        int right = search(y + 1, n, top, bottom, false, false);
        return (right - left) * (bottom - top);
    }
    private boolean isWhite(int mid, int k, boolean isRow) {
        return ((isRow) ? image[mid][k] : image[k][mid]) == '0';
    }
    private int search(int i, int j, int low, int high, boolean opt, boolean isRow) {
        while (i != j) {
            int k = low, mid = (i + j) / 2;
            while (k < high && isWhite(mid, k, isRow)) ++k;
            if (k < high == opt)
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }

    public static void main(String[] args) {
        LC302SmallestRectangleEnclosingBlackPixels smallestRectangleEnclosingBlackPixels = new LC302SmallestRectangleEnclosingBlackPixels();
        char[][] image1 = new char[][] {
                {'0','0','1','0'},
                {'0','1','1','0'},
                {'0','1','0','0'},
        };
        System.out.println(smallestRectangleEnclosingBlackPixels.minArea(image1, 0, 2));
        char[][] image2 = new char[][] {
                {'0','0','1','0'},
                {'0','1','1','0'},
                {'0','1','0','0'},
        };
        System.out.println(smallestRectangleEnclosingBlackPixels.minAreaUsingBS(image2, 0, 2));
    }

}
