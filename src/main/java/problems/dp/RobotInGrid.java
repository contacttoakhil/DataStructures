package main.java.problems.dp;

public class RobotInGrid {
    int[][] countPaths;
    int m,n;
    
    public RobotInGrid(int m, int n) {
        this.m = m;
        this.n = n;
        this.countPaths = new int[m][n];
    }

    private int compute() {
        for(int i=0 ;i<m; i++)      //Count of paths to reach any cell in first column is 1
            countPaths[i][0] = 1;
        for(int j=0; j<n; j++)      // count of paths to reach any cell in first row is 1
            countPaths[0][j] = 1;
        for (int i= 1; i<m; i++)
            for (int j=1; j<n; j++)
                countPaths[i][j] = countPaths[i-1][j] + countPaths[i][j-1]; // if diagonal movements are allowed we can add count[i-1][j-1]
        return countPaths[m-1][n-1];
    }

    public static void main(String[] args) {
        RobotInGrid robotInGrid = new RobotInGrid(3,3);
        System.out.println("Total paths: " + robotInGrid.compute());
    }
}
