package main.java.ds.graph.undirected;

import main.java.ds.graph.undirected.search.BreadthFirstSearch;
import main.java.ds.graph.undirected.search.DepthFirstSearch;
import main.java.ds.graph.undirected.weighted.UndirectedEdge;

public interface IUndirectedGraph {

    /**
     * Returns the count of edges in this undirected graph.
     * @return the count of undirected edges
     */
    int getEdgeCount();

    /**
     * Returns the count of vertices in this undirected graph
     * @return the count of vertices
     */
    int getVertexCount();

    /**
     * Returns all the vertices adjacent to vertex {@code i} as an iterable.
     *
     * @param  i the vertex
     * @return the vertices incident from vertex {@code i} as an Iterable
     * @throws IllegalArgumentException if {@code i} is invalid i.e. not in range of  0 to vertexCount.
     */
    Iterable<Integer> adjacentVertices(int i);

    /**
     * Adds the undirected edge {@code directedEdge} to this undirected graph.
     *
     * @param  undirectedEdge the edge
     * @throws IllegalArgumentException if endpoints of edge are not valid.
     */
    default void addEdge(UndirectedEdge undirectedEdge) {
        throw new UnsupportedOperationException("addEdge");
    }

    /**
     * Returns all the undirected edges incident from vertex {@code i} as an iterable.
     *
     * @param  i the vertex
     * @return the directed edges incident from vertex {@code i} as an Iterable
     * @throws IllegalArgumentException if {@code i} is not valid.
     */
    default Iterable<UndirectedEdge> adjacentEdges(int i) {
        throw new UnsupportedOperationException("adjacentEdges");
    }

    /**
     * Returns all the edges in this graph.
     * @return the edges in this graph as an iterable of edges
     */
    default Iterable<UndirectedEdge> edges() {
        throw new UnsupportedOperationException("edges");
    }

    /**
     * Adds an undirected edge to the graph.
     * @param i endpoint of edge
     * @param j endpoint of edge
     */
    default void addEdge(int i, int j) {
        throw new UnsupportedOperationException("addEdge");
    }

    /**
     * Validates the vertex
     * @param i vertex to validate
     */
    default void validateVertex(int i) {
        int vertexCount = getVertexCount();
        if(i < 0 || i > vertexCount) throw new IllegalArgumentException("Vertex:" + i + " not in range: [0," + vertexCount +"]" );
    }

    /**
     * Returns the string representation of the graph
     * @return string representation of the graph
     */
    default String printMe() {
        int edgeCount = getEdgeCount();
        int vertexCount = getVertexCount();
        StringBuilder s = new StringBuilder("Undirected Graph: ");
        s.append("vertices:").append(vertexCount).append(", edges: ").append(edgeCount).append(System.lineSeparator());
        for (int i = 0; i < vertexCount; i++) {
            s.append(i).append(": ");
            for (int j : adjacentVertices(i)) {
                s.append(j).append(" ");
            }
            s.append(System.lineSeparator());
        }
        return s.toString();
    }

    /**
     * Runs depth first search on the undirected graph. It takes O(E+V) time.
     * @param source the source vertex
     * @return the number of vertices connected to {@code source}
     */
    default int depthFirstSearch(int source) {
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(this, source);
        return depthFirstSearch.getCount();
    }

    /**
     * Runs breadth first search on the undirected graph and then prints ths shortest path from {@code source} to {@code target}
     * @param source the source vertex
     * @param target the target vertex
     */
    default void breadthFirstSearch(int source, int target) {
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(this, source);
        breadthFirstSearch.printPath(target);
    }
}
