package main.java.ds.graph.directed.basicImpl;

import main.java.ds.graph.directed.IDirectedGraph;

import java.util.LinkedList;
import java.util.List;

public class DirectedGraphAdjList implements IDirectedGraph {
    private final int vertexCount;
    private int edgeCount;
    private List<Integer>[] adjacencyListArray;
    private int[] indegree;        // indegree[v] = indegree of vertex v

    public DirectedGraphAdjList(int vertexCount) {
        this.vertexCount = vertexCount;
        this.indegree = new int[vertexCount];
        this.adjacencyListArray = (LinkedList<Integer>[]) new LinkedList[vertexCount];
        for(int i= 0; i<vertexCount; i++) {
            adjacencyListArray[i] = new LinkedList<>();
        }
    }

    public DirectedGraphAdjList(int vertexCount, int[][] dirEdges) {
        this(vertexCount);
        for(int[] dirEdge : dirEdges) {
            addEdge(dirEdge[0], dirEdge[1]);
        }
    }

    @Override
    public void addEdge(int i, int j) {
        validateVertex(i); validateVertex(j);
        adjacencyListArray[i].add(j);
        edgeCount++;
        indegree[j]++;
    }

    @Override
    public int outdegree(int i) {
        validateVertex(i);
        return adjacencyListArray[i].size();
    }

    @Override
    public int indegree(int i) {
        validateVertex(i);
        return indegree[i];
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
    public Iterable<Integer> adjacentVertices(int i) {
        validateVertex(i);
        return adjacencyListArray[i];
    }
}
