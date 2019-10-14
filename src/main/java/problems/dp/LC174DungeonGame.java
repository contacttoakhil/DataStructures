package main.java.problems.dp;

/***
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of MOD c N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *
 * https://leetcode.com/problems/dungeon-game/
 */
public class LC174DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length==0) return 0;
        int rows = dungeon.length;
        int cols = dungeon[0].length;
        int[][] health = new int[rows][cols];

        health[rows - 1][cols - 1] = Math.max(1, 1 - dungeon[rows - 1][cols - 1]);  // bottom right cell - needs 1 to survive

        for (int r = rows - 2; r >= 0; r--)   // last row
            health[r][cols - 1] = Math.max(1, health[r + 1][cols - 1] - dungeon[r][cols - 1]);

        for (int c = cols - 2; c >= 0; c--) {      // last col
            health[rows - 1][c] = Math.max(1, health[rows - 1][c + 1] - dungeon[rows - 1][c]);
        }

        for (int r = rows - 2; r >= 0; r--) {
            for (int c = cols - 2; c >= 0; c--) {
                int toRight = Math.max(1, health[r][c + 1] - dungeon[r][c]);
                int toBottom = Math.max(1, health[r + 1][c] - dungeon[r][c]);
                health[r][c] = Math.min(toRight, toBottom);
            }
        }
        return health[0][0];
    }

    public static void main(String[] args) {
        int[][] dungeon = new int[][] {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        LC174DungeonGame dungeonGame = new LC174DungeonGame();
        System.out.println(dungeonGame.calculateMinimumHP(dungeon));
    }
}
