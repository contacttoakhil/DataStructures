package main.java.ds.graph.directed;

import main.java.ds.graph.directed.search.DirectedBreadthFirstSearch;
import main.java.ds.graph.directed.search.DirectedDepthFirstSearch;
import main.java.ds.graph.directed.weighted.DirectedEdge;

public interface IDirectedGraph {

    /**
     * Returns the count of edges in this directed graph.
     * @return the count of directed edges
     */
    int getEdgeCount();

    /**
     * Returns the count of vertices in this directed graph
     * @return the count of vertices
     */
    int getVertexCount();

    /**
     * Returns all the vertices adjacent to vertex {@code i} as an iterable.
     *
     * @param  i the vertex
     * @return the vertices incident from vertex {@code i} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < vertexCount}
     */
    Iterable<Integer> adjacentVertices(int i);

    /**
     * Adds a directed edge from {@code i}to {@code j} in the graph.
     * @param i from endpoint of edge
     * @param j to endpoint of edge
     */
    void addEdge(int i, int j);

    /**
     * Returns the number of directed edges incident from vertex {@code i}.
     * This is known as the <em>outdegree</em> of vertex {@code i}.
     *
     * @param  i the vertex
     * @return the outdegree of vertex {@code i}
     * @throws IllegalArgumentException unless {@code i} is valid
     */
    int outdegree(int i);

    /**
     * Returns the number of directed edges incident to vertex {@code i}.
     * This is known as the <em>indegree</em> of vertex {@code i}.
     *
     * @param  i the vertex
     * @return the indegree of vertex {@code i}
     * @throws IllegalArgumentException unless {@code i} is valid.
     */
    public int indegree(int i);

    /**
     * Adds the directed edge {@code directedEdge} to this directed graph.
     *
     * @param  directedEdge the edge
     * @throws IllegalArgumentException if endpoints of directed edge are not valid
     */
    default void addEdge(DirectedEdge directedEdge) {
        throw new UnsupportedOperationException("addEdge");
    }

    /**
     * Returns the directed edges incident from vertex {@code i} as an iterable.
     *
     * @param  i the vertex
     * @return the directed edges incident from vertex {@code i} as an Iterable
     * @throws IllegalArgumentException if {@code i} is not valid.
     */
    default Iterable<DirectedEdge> adjacentEdges(int i) {
        throw new UnsupportedOperationException("adjacentEdges");
    }

    /**
     * Returns all the edges in this directed graph
     * @return all the edges in this directed graph as an iterable of edges
     */
    default Iterable<DirectedEdge> edges(){
        throw new UnsupportedOperationException("edges");
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
        StringBuilder s = new StringBuilder();
        s.append("vertices:").append(vertexCount).append(", edges: ").append(edgeCount).append(System.lineSeparator());
        for (int i = 0; i < vertexCount; i++) {
            s.append(i).append(": ");
            for (DirectedEdge e : adjacentEdges(i)) {
                s.append(e).append(" ");
            }
            s.append(System.lineSeparator());
        }
        return s.toString();
    }

    /**
     * Runs depth first search on the directed graph. It takes O(E+V) time.
     * @param source the source vertex
     * @return the number of vertices connected to {@code source}
     */
    default int depthFirstSearch(int source) {
        DirectedDepthFirstSearch depthFirstSearch = new DirectedDepthFirstSearch(this, source);
        return depthFirstSearch.getCount();
    }

    /**
     * Runs breadth first search on the directed graph and then prints ths shortest path from {@code source} to {@code target}
     * @param source the source vertex
     * @param target the target vertex
     */
    default void breadthFirstSearch(int source, int target) {
        DirectedBreadthFirstSearch breadthFirstSearch = new DirectedBreadthFirstSearch(this, source);
        breadthFirstSearch.printPath(target);
    }
}
