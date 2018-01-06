package main.java.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjMatrixGraph implements IGraph{
    private final int vertexCount;
    private int edgeCount;
    private boolean[][] adjacencyMatrix;

    public AdjMatrixGraph(int vertexCount) {
        if(vertexCount < 0) throw new IllegalArgumentException("Vertex count should be positive number");
        this.vertexCount = vertexCount;
        this.edgeCount = 0;
        this.adjacencyMatrix = new boolean[vertexCount][vertexCount];
    }

    public AdjMatrixGraph(int vertexCount, int[][] edges) {
        this(vertexCount);
        for(int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }
    }

    @Override
    public void addEdge(int i, int j) {
        if(!edgeExists(i,j)) edgeCount++;
        adjacencyMatrix[i][j] = adjacencyMatrix[j][i] = true;
    }

    /**
     * does graph contains an edge between two vertices i and j?
     * @param i the vertex i
     * @param j the vertex j
     * @return returns {@code true} if edge i-j exists; {@code false} otherwise.
     */
    private boolean edgeExists(int i, int j) {
        validateVertex(i);
        validateVertex(j);
        return adjacencyMatrix[i][j];
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public String toString() {
        return "Adjacency Matrix Graph: " + printMe();
    }

    @Override
    public Iterable<Integer> adjacentNeighbors(int i) {
        return new AdjacencyMatrixIterator(i);
    }

    class AdjacencyMatrixIterator implements Iterable<Integer>, Iterator<Integer> {
        private int i;
        private int j = 0;
        AdjacencyMatrixIterator(int i) {
            this.i = i;
        }

        @Override
        public Iterator<Integer> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            while(j < vertexCount) {
                if(adjacencyMatrix[i][j]) return true;
                j++;
            }
            return false;
        }

        @Override
        public Integer next() {
            if(!hasNext()) throw new NoSuchElementException();
            return j++;
        }
    }

}
