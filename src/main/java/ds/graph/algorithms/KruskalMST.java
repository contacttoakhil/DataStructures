package main.java.ds.graph.algorithms;

import main.java.ds.graph.GraphUtils;
import main.java.ds.graph.undirected.weighted.UndirectedEdge;
import main.java.ds.graph.undirected.IUndirectedGraph;
import main.java.ds.heap.MinPriorityQueue;
import main.java.ds.queue.ArrayQueue;
import main.java.ds.queue.IQueue;
import main.java.ds.set.DisjointSet;

import java.util.Objects;
import java.util.StringJoiner;

public class KruskalMST {
    private IQueue<UndirectedEdge> mst = new ArrayQueue<>(); //mst is minimum spanning tree and will contain those edges
    private double weightOfMST;

    private DisjointSet disjointSet;
    private MinPriorityQueue<UndirectedEdge> pq;

    public KruskalMST(IUndirectedGraph undirectedGraph) {
        this.pq = new MinPriorityQueue<>();
        this.disjointSet = new DisjointSet(undirectedGraph.getVertexCount());
        for (UndirectedEdge e : undirectedGraph.edges()) {
            pq.insert(e);
        }
        compute(undirectedGraph.getVertexCount());
        validate(undirectedGraph);
    }

    private void compute(int vc) {
        boolean mstNotReady = mst.size()<vc-1;
        while(!pq.isEmpty() && mstNotReady) {
            UndirectedEdge edge = pq.deleteMin();
            int either = edge.either();
            int other = edge.other(either);
            if(!disjointSet.connected(either, other)) {
                disjointSet.union(either, other);
                mst.offer(edge);
                weightOfMST = weightOfMST + edge.weight();
            }
        }
    }
    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<UndirectedEdge> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        return weightOfMST;
    }

    public String getMST() {
        StringJoiner joiner = new StringJoiner(", ");
        for(UndirectedEdge edge: edges()) {
            joiner.add(Objects.toString(edge));
        }
        return joiner.toString();
    }

    private void validate(IUndirectedGraph undirectedGraph) {
        GraphUtils.validateMST(undirectedGraph, edges(), weight());
    }
}

