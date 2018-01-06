package main.java.graph;

import java.util.LinkedList;
import java.util.List;

public class AdjListGraph implements IGraph{
    private final int vertexCount;
    private int edgeCount;
    private List<Integer>[] adjacencyListArray;

    public AdjListGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjacencyListArray = (LinkedList<Integer>[])new LinkedList[vertexCount];
        for(int i= 0; i<vertexCount; i++) {
            adjacencyListArray[i] = new LinkedList<>();
        }
    }

    public AdjListGraph(int vertexCount, int[][] edges) {
        this(vertexCount);
        for(int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }
    }

    @Override
    public void addEdge(int i, int j) {
        validateVertex(i); validateVertex(j);
        edgeCount++;
        adjacencyListArray[i].add(j);
        adjacencyListArray[j].add(i);
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public String toString() {
        return "Adjacency List Graph: " + printMe();
    }

    @Override
    public Iterable<Integer> adjacentNeighbors(int i) {
        validateVertex(i);
        return adjacencyListArray[i];
    }
}
