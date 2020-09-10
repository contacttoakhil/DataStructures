package main.java.problems.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/***
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 *
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 *
 */
public class LC785IsGraphBipartite {

    private static Integer NO_COLOR   = -1;
    private static Integer RED_COLOR  = 0;
    private static Integer BLUE_COLOR = 1;

    public boolean isBipartiteDFS(int[][] graph) {     // 2ms faster than 31 pct
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, NO_COLOR);
        for (int i = 0; i < n; i++) {
            if (colors[i] != NO_COLOR) continue;
            Stack<Integer> stack = new Stack();
            stack.push(i);
            colors[i] = RED_COLOR;
            while (!stack.empty()) {
                Integer node = stack.pop();
                for (int nei: graph[node]) {
                    if (colors[nei] == colors[node]) return false;
                    if (colors[nei] == NO_COLOR) {
                        stack.push(nei);
                        colors[nei] = colors[node] ^ 1;
                    }
                }
            }
        }
        return true;
    }

    public boolean isBipartiteBFS(int[][] graph) {        // 2ms, faster than 31 pct
        int len = graph.length;
        int[] colors = new int[len];
        Arrays.fill(colors, NO_COLOR);
        for (int i = 0; i < len; i++) {
            if (colors[i] != NO_COLOR) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = RED_COLOR;   // Blue: 1; Red: -1;
            while (!queue.isEmpty()) {
                Integer node = queue.poll();
                for (int nei : graph[node]) {
                    if (colors[nei] == colors[node]) return false;
                    if (colors[nei] == NO_COLOR) {
                        queue.offer(nei);
                        colors[nei] = colors[node] ^ 1;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC785IsGraphBipartite graphBipartite = new LC785IsGraphBipartite();

        int[][] graph1 = new int[][] {{1,3}, {0,2}, {1,3}, {0,2}};
        System.out.println(graphBipartite.isBipartiteDFS(graph1));     //true
        System.out.println(graphBipartite.isBipartiteBFS(graph1));     //true

        int[][] graph2 = new int[][] {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
        System.out.println(graphBipartite.isBipartiteDFS(graph2));     //false
        System.out.println(graphBipartite.isBipartiteBFS(graph2));     //false

    }
}

