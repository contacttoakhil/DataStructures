package main.java.problems.graphs;

import java.util.*;

/***
 * On an 8x8 chessboard, there can be multiple Black Queens and one White King.
 *
 * Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King,
 * return the coordinates of all the queens (in any order) that can attack the King.
 *
 * Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * Output: [[0,1],[1,0],[3,3]]
 * Explanation:
 * The queen at [0,1] can attack the king cause they're in the same row.
 * The queen at [1,0] can attack the king cause they're in the same column.
 * The queen at [3,3] can attack the king cause they're in the same diagnal.
 * The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
 * The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
 * The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.
 */
public class LC1222QueensThatCanAttackTheKing {
    public static String COLON = ":";
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        Set<String> qset = new HashSet<>();
        for(int[] qpos:queens)
            qset.add(qpos[0] + COLON + qpos[1]);
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        for(int[] dir : directions) {
            for(int k = 1; k <= 8; k++){
                int xpos = king[0] + dir[0] * k;
                int ypos = king[1] + dir[1] * k;
                if (qset.contains(xpos + COLON + ypos)){
                    result.add(Arrays.asList(xpos,ypos));
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LC1222QueensThatCanAttackTheKing queensThatCanAttackTheKing = new LC1222QueensThatCanAttackTheKing();
        List<List<Integer>> result = queensThatCanAttackTheKing.queensAttacktheKing(new int[][]{{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}}, new int[]{0,0});
        System.out.println(result); // [[0, 1], [1, 0], [3, 3]]
    }
}
