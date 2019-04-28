package main.java.problems.dp;

/***
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
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
public class Lc174DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length==0) return 0;
        int rows = dungeon.length;
        int cols = dungeon[0].length;
        // To survive min health is 1 at last cell i.e. dungeon[rows-1][cols-1]
        for(int i = rows-1; i >= 0; i--) {
            for(int j = cols-1; j >= 0; j--) {
                if(i == rows-1 && j == cols-1)  // bottom right last cell - needs 1 to survive
                    dungeon[i][j] = Math.max(1, 1 - dungeon[i][j]);
                else if(i == rows-1)            // last row
                    dungeon[i][j] = Math.max(1, dungeon[i][j+1] - dungeon[i][j]);
                else if(j == cols-1)            // last col
                    dungeon[i][j] = Math.max(1, dungeon[i+1][j]-dungeon[i][j]);
                else
                    dungeon[i][j] = Math.max(1, Math.min(dungeon[i+1][j], dungeon[i][j+1])-dungeon[i][j]);
            }
        }
        return dungeon[0][0];
    }
}
