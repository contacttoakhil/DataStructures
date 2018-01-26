package main.java.ds.graph.undirected.search;

import main.java.ds.graph.GraphUtils;
import main.java.ds.graph.undirected.IUndirectedGraph;
import main.java.ds.queue.ArrayQueue;
import main.java.ds.queue.IQueue;

/***
 * Breadth First Search works similar to level order traversal of the tree and makes use of queue. When adjacency list is used time complexity is O(V+E) and when adjacency matrix is used we get quadratic time complexity of O(V^2).
 *
 * Applications - finding connected components, finding all nodes in a connected component, finding shortest path between two nodes, testing graph for bi-partiteness etc.
 */
public class BreadthFirstSearch {
    private boolean[] visited;      // visited[i] is true if there is a path from source to vertex i; false otherwise.
    private int[] prevEdge;         // prevEdge[i] - previous edge on shortest path from source to vertex i.
    private int[] pathLength;       // pathLength[i] - number of edges in shortest path from source to vertex i.
    private IQueue<Integer> queue;
    /**
     * Computes the shortest path between the source vertex {@code s} and every other vertex in the graph {@code graph}.
     * @param graph the undirected graph
     * @param source the source vertex
     * @throws IllegalArgumentException if vertex is invalid.
     */
    public BreadthFirstSearch(IUndirectedGraph graph, int source) {
        graph.validateVertex(source);
        this.visited = new boolean[graph.getVertexCount()];
        this.prevEdge = new int[graph.getVertexCount()];
        this.pathLength = initializePathLength(graph.getVertexCount(), source);
        queue = new ArrayQueue<>();
        bfs(graph, source);
    }

    private void bfs(IUndirectedGraph graph, int source) {
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
        return GraphUtils.initializePathLengthForIntArray(vertexCount, source);
    }

    /**
     * Print the shortest path from source to this vertex {@code v}
     * @param v - the target vertex
     */
    public void printPath(int v) {
        GraphUtils.printPath(v, pathLength, prevEdge);
    }
}
