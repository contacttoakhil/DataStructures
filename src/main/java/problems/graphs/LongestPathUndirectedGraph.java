package main.java.problems.graphs;

import main.java.ds.graph.undirected.weighted.EdgeWeightedGraph;
import main.java.ds.graph.undirected.IUndirectedGraph;
import main.java.ds.graph.undirected.weighted.UndirectedEdge;

import java.util.Arrays;
import java.util.List;

/**
 Find the longest path between any pair of vertices.
 Suppose we are having n number of cities connected via cable line such that there is no cycle. We need to find the maximum length of cable between any two cities in this map.
                (0)            (3)
                 |              |
                 | <-3          | <-6
                 |              |
                 |              |
 (2) ----4----- (1) ----2----- (5) -------5------ (4)

 For this example longest cable is 11 for (3)->(4).

 Idea is to findSubset longest cable (lc) needed for each vertex (source) and then findSubset the maximum of the lc.

 Ref - https://www.geeksforgeeks.org/longest-path-between-any-pair-of-vertices/
 */
public class LongestPathUndirectedGraph {
    private boolean[] visited;
    private double MAX_LENGTH = Double.NEGATIVE_INFINITY;

    public LongestPathUndirectedGraph(IUndirectedGraph undirectedGraph) {
        this.visited = new boolean[undirectedGraph.getVertexCount()];
        longestCable(undirectedGraph);
    }

    private void longestCable(IUndirectedGraph undirectedGraph) {
        for (int i = 0; i < undirectedGraph.getVertexCount(); i++) {
            longestCable(undirectedGraph, i, 0);

        }
    }

    private void longestCable(IUndirectedGraph undirectedGraph, int s, double prevLength) {
        double currentCableLength = 0;
        visited[s] = true;
        for(UndirectedEdge edge : undirectedGraph.adjacentEdges(s)) {
            int other = edge.other(s);
            if(!visited[other]) {
                currentCableLength = prevLength + edge.weight(); // Total length of cable from src city to its adjacent will be cable length of src + edge weight
                longestCable(undirectedGraph, other, currentCableLength);
            }
            if(currentCableLength > MAX_LENGTH)     // If total length is greater than MAX_LENGTH then update it.
                MAX_LENGTH = currentCableLength;
        }
    }

    public static void main(String[] args) {
        List<UndirectedEdge> edges = Arrays.asList(
                new UndirectedEdge(0,1, 3),
                new UndirectedEdge(1,2, 4),
                new UndirectedEdge(1,5, 2),
                new UndirectedEdge(5,3, 6),
                new UndirectedEdge(5,4, 5)
        );
        LongestPathUndirectedGraph longestPathUndirectedGraph = new LongestPathUndirectedGraph(new EdgeWeightedGraph(6, edges));
        System.out.println("Max cable length: " + longestPathUndirectedGraph.MAX_LENGTH);
    }
}
