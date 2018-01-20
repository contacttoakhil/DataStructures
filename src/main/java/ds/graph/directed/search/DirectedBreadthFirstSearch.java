package main.java.ds.graph.directed.search;

import main.java.ds.graph.directed.IDirectedGraph;
import main.java.ds.queue.ArrayQueue;
import main.java.ds.queue.IQueue;
import main.java.ds.stack.ArrayStack;
import main.java.ds.stack.IStack;

import java.util.Arrays;

/**
 * This is similar to BreadthFirstSearch class with the only exception that it works for Directed Graph. We can think of BFS as Dijkstra's algorithm where each edge has a weight of 1. As each edge has weight of 1 we can think of priority queue as a queue as
 * each edge will have same priority. Hence we make use of queue. Also BFS is like level order traversal of tree.
 */
public class DirectedBreadthFirstSearch {
    private boolean[] visited;      // visited[i] is true if there is a path from source to vertex i; false otherwise.
    private int[] prevEdge;         // prevEdge[i] - previous edge on shortest path from source to vertex i.
    private int[] pathLength;       // pathLength[i] - number of edges in shortest path from source to vertex i.
    private IQueue<Integer> queue;
    /**
     * Computes the shortest path between the source vertex {@code s} and every other vertex in the graph {@code graph}.
     * @param graph the directed graph
     * @param source the source vertex
     * @throws IllegalArgumentException if vertex is invalid.
     */
    public DirectedBreadthFirstSearch(IDirectedGraph graph, int source) {
        graph.validateVertex(source);
        this.visited = new boolean[graph.getVertexCount()];
        this.prevEdge = new int[graph.getVertexCount()];
        this.pathLength = initializePathLength(graph.getVertexCount(), source);
        queue = new ArrayQueue<>();
        bfs(graph, source);
    }

    private void bfs(IDirectedGraph graph, int source) {
        visited[source] = true;
        queue.offer(source);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for(int n : graph.adjacentVertices(v)) {
                graph.validateVertex(n);
                if(!visited[n]) {
                    prevEdge[n] = v; // n is adjacent to v so can be reached via v.
                    pathLength[n] = pathLength[v] + 1;
                    visited[n] = true;
                    queue.offer(n);
                }
            }
        }
    }

    /**
     * Initialize path-length for every vertex in graph to infinity and initialize path length of {@code source} to zero.
     * @param vertexCount number of vertices in the graph.
     * @param source source of graph.
     */
    private int[] initializePathLength(int vertexCount, int source) {
        int[] array = new int[vertexCount];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[source] = 0;
        return array;
    }

    /**
     * Print the shortest path from source to this vertex {@code v}
     * @param v - the target vertex
     */
    public void printPath(int v) {
        IStack<Integer> path = new ArrayStack<>();
        int i;
        for (i = v; pathLength[i] != 0; i = prevEdge[i])
            path.push(i);
        path.push(i);
        path.print("->");
    }
}
