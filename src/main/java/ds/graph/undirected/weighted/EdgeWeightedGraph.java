package main.java.ds.graph.undirected.weighted;

import main.java.ds.graph.undirected.IUndirectedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedGraph implements IUndirectedGraph {
    private final int vertexCount;
    private int edgeCount = 0;
    private List<UndirectedEdge>[] adjacencyListArray;

    public EdgeWeightedGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjacencyListArray = (LinkedList<UndirectedEdge>[])new LinkedList[vertexCount];
        for(int i= 0; i<vertexCount; i++) {
            adjacencyListArray[i] = new LinkedList<>();
        }
    }

    public EdgeWeightedGraph(int vertexCount, List<UndirectedEdge> edges) {
        this(vertexCount);
        for(UndirectedEdge edge : edges) {
            addEdge(edge);
        }
    }

    @Override
    public void addEdge(UndirectedEdge edge) {
        int either = edge.either();
        int or = edge.other(either);
        validateVertex(either); validateVertex(or);
        adjacencyListArray[either].add(edge);
        adjacencyListArray[or].add(edge);
        edgeCount++;
    }

    @Override
    public void addEdge(int i, int j) {
        addEdge(new UndirectedEdge(i,j,0));
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<UndirectedEdge> edges() {
        List<UndirectedEdge> list = new LinkedList<>();
        for (int i = 0; i < vertexCount; i++) {
            int selfLoops = 0;
            for (UndirectedEdge e : adjacentEdges(i)) {
                if (e.other(i) > i) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(i) == i) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    /**
     * Returns the edges incident on vertex {@code i}.
     * @param i the vertex
     * @return the edges incident on vertex {@code i} as an Iterable
     */
    @Override
    public Iterable<UndirectedEdge> adjacentEdges(int i) {
        validateVertex(i);
        return adjacencyListArray[i];
    }

    @Override
    public Iterable<Integer> adjacentVertices(int i) {
        List<Integer> adjacentVertices = new ArrayList<>();
        for(UndirectedEdge edge : adjacentEdges(i)) {
            assert (edge.other(i) != i);
            adjacentVertices.add(edge.other(i));
        }
        return adjacentVertices;
    }

}

