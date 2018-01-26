package main.java.ds.graph.directed.weighted;

import main.java.ds.graph.directed.IDirectedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedDirectedGraph implements IDirectedGraph {
    private final int vertexCount;
    private int edgeCount = 0;
    private List<DirectedEdge>[] adjacencyListArray;
    private int[] indegree;                         // indegree[v] = indegree of vertex v

    public EdgeWeightedDirectedGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjacencyListArray = (LinkedList<DirectedEdge>[])new LinkedList[vertexCount];
        this.indegree = new int[vertexCount];
        for(int i= 0; i<vertexCount; i++) {
            adjacencyListArray[i] = new LinkedList<>();
        }
    }

    public EdgeWeightedDirectedGraph(int vertexCount, List<DirectedEdge> directedEdges) {
        this(vertexCount);
        for(DirectedEdge directedEdge : directedEdges) {
            addEdge(directedEdge);
        }
    }

    @Override
    public void addEdge(DirectedEdge directedEdge) {
        int from = directedEdge.getFrom();
        int to = directedEdge.getTo();
        validateVertex(from); validateVertex(to);
        adjacencyListArray[from].add(directedEdge);
        indegree[to]++;
        edgeCount++;
    }

    @Override
    public void addEdge(int i, int j) {
        addEdge(new DirectedEdge(i,j));
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

    public int outDegree(int i) {
        validateVertex(i);
        return adjacencyListArray[i].size();
    }

    public int inDegree(int i) {
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
    public Iterable<Integer> adjacentVertices(int i) {
        List<Integer> adjacentVertices = new ArrayList<>();
        for(DirectedEdge directedEdge : adjacentEdges(i)) {
            assert (directedEdge.getFrom() == i);
            adjacentVertices.add(directedEdge.getTo());
        }
        return adjacentVertices;
    }

    @Override
    public Iterable<DirectedEdge> edges() {
        LinkedList<DirectedEdge> list = new LinkedList<>();
        for (int v = 0; v < vertexCount; v++) {
            for (DirectedEdge e : adjacentEdges(v)) {
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public Iterable<DirectedEdge> adjacentEdges(int i) {
        validateVertex(i);
        return adjacencyListArray[i];
    }

    @Override
    public String toString() {
        return "Weighted directed graph - " + printMe();
    }
}
