package main.java.problems.graphs;

import java.util.*;

public class ShortestPath2DGridWithObstacles {

    private char[][] grid;
    private List<Position> pathPositions = new ArrayList<>();

    public ShortestPath2DGridWithObstacles(char[][] grid) {
        this.grid = grid;
    }

    public int compute(Position start, Position end) {
        boolean[][] visited = initializeVisited(start);
        Queue<Position> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = true;
        while (!queue.isEmpty()) {
            Position p = queue.remove();
            pathPositions.add(p);
            if(p.r == end.r && p.c == end.c)                                      // found destination?
                return p.dist;
            if(p.r > 0 && !visited[p.r -1][p.c]) {                                // move up from current pos: [r,c] -> [r-1,c]?
                queue.add(new Position(p.r -1, p.c, p.dist+1, p));
                visited[p.r -1][p.c] = true;
            }
            if(p.r < grid.length-1 && !visited[p.r +1][p.c]) {                   // moving down
                queue.add(new Position(p.r +1, p.c, p.dist+1, p));
                visited[p.r +1][p.c] = true;
            }
            if(p.c > 0 && !visited[p.r][p.c -1]) {                              // move left
                queue.add(new Position(p.r, p.c -1, p.dist+1, p));
                visited[p.r][p.c -1] = true;
            }
            if(p.c < grid[0].length-1 && !visited[p.r][p.c +1]) {               // moving right
                queue.add(new Position(p.r, p.c +1, p.dist+1, p));
                visited[p.r][p.c +1] = true;
            }
        }
        return -1;
    }

    private boolean[][] initializeVisited(Position start) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                visited[i][j] = grid[i][j] == 'X';       // mark obstacles as visited
            }
        }
        visited[start.r][start.c] = true;
        return visited;
    }

    private void printPathPositions() {
        Position position = pathPositions.get(pathPositions.size()-1);
        if(position != null) {
            Stack<Position> path = new Stack<>();
            do {
                path.push(position);
                position = position.previous;
            } while (position != null);
            Position[] shortestPath = new Position[path.size()];
            int i = 0;
            while (!path.isEmpty()) {
                shortestPath[i++] = path.pop();
            }
            for (Position pos : shortestPath) {
                System.out.print(pos);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                { 'X', '*', 'X', '*' },
                { '*', 'X', '*', '*' },
                { 'X', '*', '*', '*' },
                { '*', '*', '*', '*' }
        };
        ShortestPath2DGridWithObstacles shortestPath2DGridWithObstacles = new ShortestPath2DGridWithObstacles(grid);
        System.out.println(shortestPath2DGridWithObstacles.compute(new Position(0,3), new Position(3,0)));
        shortestPath2DGridWithObstacles.printPathPositions();
    }
}
class Position
{
    public int r;
    public int c;
    public int dist;
    public Position previous;

    @Override
    public String toString() {
        return "[" + "r=" + r + ", c=" + c + ']';
    }

    public Position(int r, int c) {
        this(r, c,0);
    }
    public Position(int r, int c, int dist) {
        this(r, c,dist,null);
    }
    public Position(int r, int c, int dist, Position previous) {
        this.r = r;
        this.c = c;
        this.dist = dist;
        this.previous = previous;
    }

}
