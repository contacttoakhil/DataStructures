package main.java.ds.graph.directed.search;

import main.java.ds.graph.directed.IDirectedGraph;

public class DirectedDepthFirstSearch {
    private boolean[] visited; // visited[i] is true if there is a path from source to vertex i; false otherwise.
    private int count;

    /**
     * Performs depth first search on the {@code graph} and computes the number of vertices connected to {@code source}
     * @param graph the graph to use for depth first search
     * @param source the source vertex
     */
    public DirectedDepthFirstSearch(IDirectedGraph graph, int source) {
        this.visited = new boolean[graph.getVertexCount()];
        graph.validateVertex(source);
        dfs(graph, source);
    }

    private void dfs(IDirectedGraph graph, int source) {
        visited[source] = true; count++;
        for(int n : graph.adjacentVertices(source)) {
            graph.validateVertex(n);
            if(!visited[n])
                dfs(graph, n);
        }
    }

    /**
     * Checks if a path exist from {@code source} to this vertex {@code j}
     * @param j the target vertex for path check
     * @return {@code true} if path exists from {@code source} to this vertex {@code j}; {@code false} otherwise.
     */
    public boolean doesPathExist(int j) {
        return visited[j];
    }

    /**
     * Returns the number of vertices connected to {@code source}
     * @return the number of connected vertices to {@code source}
     */
    public int getCount() {
        return count;
    }
}
