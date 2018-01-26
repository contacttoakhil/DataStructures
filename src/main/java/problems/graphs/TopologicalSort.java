package main.java.problems.graphs;

import main.java.ds.graph.directed.IDirectedGraph;
import main.java.ds.graph.directed.search.DirectedDepthFirstOrder;
import main.java.ds.graph.directed.weighted.DirectedEdge;
import main.java.ds.graph.directed.weighted.EdgeWeightedDirectedGraph;
import main.java.ds.queue.ArrayQueue;
import main.java.ds.queue.IQueue;

import java.util.*;

public class TopologicalSort {
    private IDirectedGraph dg;

    public TopologicalSort(IDirectedGraph directedGraph) {
        this.dg = directedGraph;
    }

    public void computeUsingDFS() {
        detectCycle();
        print(new DirectedDepthFirstOrder(dg).reversePost());
    }

    public void computeUsingQueue() {
        detectCycle();
        int vc = dg.getVertexCount();
        int[] indegree = new int[vc];
        for (int v = 0; v < vc; v++) {
            indegree[v] = dg.indegree(v);
        }

        IQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < vc; i++)                // initialize queue to have all vertices with indegree = 0
            if (indegree[i] == 0) queue.offer(i);

        IQueue<Integer> order = new ArrayQueue<>();
        while (!queue.isEmpty()) {
            int v = queue.poll();
            order.offer(v);
            for(int w : dg.adjacentVertices(v)) {
                indegree[w]--;
                if(indegree[w] == 0) queue.offer(w);
            }
        }
        print(order);
    }

    private void print(Iterable<Integer> order) {
        StringJoiner joiner = new StringJoiner(",");
        for(Integer e : order) {
            joiner.add(Objects.toString(e));
        }
        System.out.println(joiner);
    }

    private void detectCycle() {
        DetectCycleDirectedGraph detectCycleDirectedGraph = new DetectCycleDirectedGraph(dg);
        if(detectCycleDirectedGraph.hasCycle()) {
            throw new IllegalArgumentException("Graph contains cycle");
        }
    }

    public static void main(String[] args) {
        List<DirectedEdge> edgeListOne = Arrays.asList(
                new DirectedEdge(0,1),
                new DirectedEdge(0,3),
                new DirectedEdge(1,2),
                new DirectedEdge(3,4)
        );
        TopologicalSort topologicalSort = new TopologicalSort(new EdgeWeightedDirectedGraph(5, edgeListOne));
        topologicalSort.computeUsingDFS();
        topologicalSort.computeUsingQueue();
    }
}
