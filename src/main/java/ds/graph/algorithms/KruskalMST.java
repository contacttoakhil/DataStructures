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

    public KruskalMST(IUndirectedGraph undirectedGraph) {
        MinPriorityQueue<UndirectedEdge> pq = new MinPriorityQueue<>();
        for (UndirectedEdge e : undirectedGraph.edges()) {
            pq.insert(e);
        }

        DisjointSet disjointSet = new DisjointSet(undirectedGraph.getVertexCount());
        while(!pq.isEmpty() && mst.size()<undirectedGraph.getVertexCount()-1) {
            UndirectedEdge edge = pq.deleteMin();
            int either = edge.either();
            int other = edge.other(either);
            if(!disjointSet.connected(either, other)) {
                disjointSet.union(either, other);
                mst.offer(edge);
                weightOfMST = weightOfMST + edge.weight();
            }
        }
        validate(undirectedGraph);
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

