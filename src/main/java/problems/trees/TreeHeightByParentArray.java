package main.java.problems.trees;

import java.util.Arrays;

public class TreeHeightByParentArray {
    static int findHeight(int parent[], int n) {
        int depth[] = new int[n];
        Arrays.fill(depth , 0);
        for (int i = 0; i < n; i++) {
            fillDepth(parent, i, depth);
        }
        return Arrays.stream(depth).max().getAsInt(); // return max value in depth array
    }
    static void fillDepth(int parent[], int i, int depth[]) {
        if (depth[i] != 0) {
            return;
        }
        if (parent[i] == -1) {
            depth[i] = 1;
            return;
        }
        if (depth[parent[i]] == 0) {    // evaluate depth of parent
            fillDepth(parent, parent[i], depth);
        }
        depth[i] = depth[parent[i]] + 1;    //depth[i] = depth[parent[i]] + 1;
    }
    public static void main(String args[]) {
        int parent[] = {1, 5, 5, 2, 2, -1, 3};
        System.out.println("Height is  " + findHeight(parent, parent.length));
    }
}
