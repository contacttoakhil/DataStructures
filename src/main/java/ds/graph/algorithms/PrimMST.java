package main.java.ds.graph.algorithms;

import main.java.ds.graph.undirected.weighted.UndirectedEdge;
import main.java.ds.graph.undirected.IUndirectedGraph;
import main.java.ds.heap.IndexMinPriorityQueue;
import main.java.ds.queue.ArrayQueue;
import main.java.ds.queue.IQueue;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * In computer science, Prim's algorithm is a greedy algorithm that finds a minimum spanning tree for a weighted undirected graph. This means it finds a subset of the edges that forms a tree that includes every vertex, where the total weight of all the edges
 * in the tree is minimized. The algorithm operates by building this tree one vertex at a time, from an arbitrary starting vertex, at each step adding the cheapest possible connection from the tree to another vertex.
 *
 * The algorithm computes mst by the following process:
 * a) At each stage pick a new vertex (i) to add to tree.
 * b) And add to the tree an edge(i,j) such that cost of [i,j] is minimum among all edges where i is in tree but j is not.
 *
 * We can see that it is essentially identical to Dijkstra's algorithm for shortest paths. So we keep for each vertex prevEdge (in mst), pathLength (weight of shortest edge) and visited (marked or not).
 *
 * https://en.wikipedia.org/wiki/Prim%27s_algorithm
 */
public class PrimMST {
    private UndirectedEdge[] prevEdge;         // prevEdge[i] - shortest edge from tree vertex to non-tree vertex.
    private double[] pathLength;       // pathLength[i] - weight of shortest such edge.
    private IndexMinPriorityQueue<Double> priorityQueue;    // priority queue of vertices
    private boolean[] visited;     // visited[i] = true if i on tree, false otherwise

    public PrimMST(IUndirectedGraph undirectedGraph) {
        int vertexCount = undirectedGraph.getVertexCount();
        this.prevEdge = new UndirectedEdge[vertexCount];
        this.visited = new boolean[vertexCount];
        this.pathLength = GraphUtils.initializePathLength(vertexCount);
        priorityQueue = new IndexMinPriorityQueue<>(vertexCount);
        compute(undirectedGraph);
        validate(undirectedGraph);
    }

    private void compute(IUndirectedGraph undirectedGraph) {
        for (int i = 0; i < undirectedGraph.getVertexCount(); i++)      // run from each vertex to find
            if (!visited[i]) prim(undirectedGraph, i);      // minimum spanning forest
    }

    private void prim(IUndirectedGraph undirectedGraph, int source) {
        pathLength[source] = 0.0;
        priorityQueue.insert(source, pathLength[source]);
        while (!priorityQueue.isEmpty()) {
            int min = priorityQueue.delMin();
            scan(undirectedGraph, min);
        }
    }

    private void scan(IUndirectedGraph undirectedGraph, int source) {
        visited[source] = true;
        for(UndirectedEdge edge: undirectedGraph.adjacentEdges(source)) {
            int other = edge.other(source);
            if(visited[other]) continue;        // obsolete edge source-other
            if(edge.weight() < pathLength[other]) {
                pathLength[other] = edge.weight();
                prevEdge[other] = edge;
                if(priorityQueue.contains(other))   priorityQueue.decreaseKey(other, pathLength[other]);
                else    priorityQueue.insert(other, pathLength[other]);
            }
        }
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as an iterable of edges
     */
    public Iterable<UndirectedEdge> edges() {
        IQueue<UndirectedEdge> mst = new ArrayQueue<>();
        for (UndirectedEdge e : prevEdge) {
            if (e != null) {
                mst.offer(e);
            }
        }
        return mst;
    }

    /**
     * Returns string representation of the mst.
     * @return string representation of the mst
     */
    public String getMST() {
        StringJoiner joiner = new StringJoiner(", ");
        for(UndirectedEdge edge: edges()) {
            joiner.add(Objects.toString(edge));
        }
        return joiner.toString();
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        double weight = 0.0;
        for (UndirectedEdge e : edges())
            weight += e.weight();
        return weight;
    }

    private void validate(IUndirectedGraph undirectedGraph) {
        GraphUtils.validateMST(undirectedGraph, edges(), weight());
    }
}
