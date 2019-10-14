package main.java.problems.graphs.unionFind;

/***
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 *
 * https://leetcode.com/problems/number-of-islands/
 * https://leetcode.com/problems/number-of-islands/discuss/56354/1d-union-find-java-solution-easily-generalized-to-other-problems
 */
public class LC200NumberOfIslands {

    int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)  {
            return 0;
        }
        UnionFind uf = new UnionFind(grid);
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] != '1') continue;
                for (int[] d : distance) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '1') {
                        int id1 = i * cols + j;
                        int id2 = x * cols + y;
                        uf.union(id1, id2);
                    }
                }
            }
        }
        return uf.count;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][] {
                {'1','1','1','1','0'},
                {'1', '1','0','1','0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        LC200NumberOfIslands numberOfIslands = new LC200NumberOfIslands();
        System.out.println(numberOfIslands.numIslands(grid)); // 1
    }
}

class UnionFind {
    int[] parents;
    int m, n;
    int count = 0;
    UnionFind(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        parents = new int[m*n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int id = i * n + j;
                    parents[id] = id;
                    count++;
                }
            }
        }
    }

    private int find(int i) {
        if (parents[i] == i) {
            return i;
        }
        parents[i] = find(parents[i]);
        return parents[i];
    }

    public void union(int i, int j) {
        int a = find(i);
        int b = find(j);
        if (a != b) {
            parents[a] = b;
            count--;
        }
    }
}
