package main.java.problems.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/***
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the children direction.
 *
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 *
 * Output: 12
 *
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right. The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 *
 * https://leetcode.com/articles/the-maze-ii
 *
 * Solution:
 * a) We initialize distance array initialized with very large values in the beginning, distance[i][j] represents the minimum number of steps required to reach the position (i,j) from the start position.
 * b) We use BFS: we explore all the new positions that can be reached starting from the current position first, before moving onto the children positions that can be reached from these new positions.
 * c) We make use of dir array to travel in four directions.
 * d) Whenever we make a traversal in any direction, we keep a track of the number of steps taken while moving in this direction in count variable
 * e) We start from current pos and traverse in a particular direction until we hit the wall and if the  distance from current pos + count is lesser then we update accordingly.
 * f) After this, we add the new position obtained, to the back of a queue, so that the various paths possible from this new position will be explored later on when all the directions possible from the current position have been explored.
 * g) If the destination can't be reached, the corresponding entry will contain Integer.MAX_VALUE.
 */
public class LC505Maze2 {
    /***
     *
     * @param maze given grid
     * @param start starting coordinates start[0] and start[1]
     * @param dest destination coordinates dest[0] and dest[1]
     * @return shortest distance from start to dest
     */
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        return shortestDistance(maze, new Point(start), new Point(dest));
    }

    private int shortestDistance(int[][] maze, Point start, Point dest) {
        int[][] distance = initializeDistance(maze, start);
        Point[] directions = new Point[]{
                new Point(0,1),
                new Point(0, -1),
                new Point(1, 0),
                new Point(-1, 0)
        };
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point p = queue.remove();
            for (Point dir : directions) {
                int x = p.x + dir.x;
                int y = p.x + dir.y;
                int count = 0;
                while(canMoveToNextCell(x, y, maze)) {  // Keep going in same dir as long as we can as ball will roll over
                    x = x + dir.x;
                    y = y + dir.y;
                    count++;
                }
                if(distance[p.x][p.y] + count < distance[x-dir.x][y-dir.y]){
                    distance[x-dir.x][y-dir.y] = distance[p.x][p.y] + count;
                    queue.add(new Point(x-dir.x, y-dir.y));
                }
            }
        }
        return distance[dest.x][dest.y] == Integer.MAX_VALUE ? -1 : distance[dest.x][dest.y];
    }

    private boolean canMoveToNextCell(int x, int y, int[][] maze) {
        return x>=0 && y>=0 && x<maze.length && y<maze[0].length && maze[x][y] == 0;
    }

    private int[][] initializeDistance(int[][] maze, Point start) {
        int[][] result = new int[maze.length][maze[0].length];
        for (int[] row : result)
            Arrays.fill(row, Integer.MAX_VALUE );   // fill initially with max value, will populate with min later on
        result[start.x][start.y] = 0;
        return result;
    }

}
class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point(int[] coordinates) {
        this.x = coordinates[0];
        this.y = coordinates[1];
    }
}

/*
Complexity Analysis
Time complexity : O(m*n*max(m,n))O(m∗n∗max(m,n)). Time complexity : O(m*n*max(m,n)). Complete traversal of maze will be done in the worst case. Here, m and n refers to the number of rows and columns of the maze. Further, for every current node chosen, we can travel upto a maximum depth of max(m,n) in any direction.
Space complexity : O(mn) queue size can grow upto m*n in the worst case.
 */