package main.java.ds.graph.undirected.search;

import main.java.ds.graph.undirected.IUndirectedGraph;

/***
 * Depth First Search works similar to pre-order traversal and makes use of stack. When adjacency list is used to represent the graph, then we start at a vertex and process the adjacent nodes only if they are not visited and hence the time complexity
 * is O(V+E). But when adjacency matrix is used we cannot find this efficiently and get quadratic time complexity of O(V^2).
 *
 * Applications for DFS - Topological sorting, finding connected components, finding articulation points (cut vertices) of graph, finding strongly connected component, solving mazes etc.
 *
 */
public class DepthFirstSearch {
    private boolean[] visited; // visited[i] is true if there is a path from source to vertex i; false otherwise.
    private int count;

    /**
     * Performs depth first search on the {@code graph} and computes the number of vertices connected to {@code source}
     * @param graph the graph to use for depth first search
     * @param source the source vertex
     */
    public DepthFirstSearch(IUndirectedGraph graph, int source) {
        this.visited = new boolean[graph.getVertexCount()];
        graph.validateVertex(source);
        dfs(graph, source);
    }


    private void dfs(IUndirectedGraph graph, int source) {
        visited[source] = true; count++;
        for(int n : graph.adjacentVertices(source)) {
            graph.validateVertex(n);
            if(!visited[n])
                dfs(graph, n);
        }
    }

    /**
     * Returns the number of vertices connected to {@code source}
     * @return the number of connected vertices to {@code source}
     */
    public int getCount() {
        return count;
    }
}
