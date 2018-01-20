package main.java.problems.graphs;

import main.java.ds.graph.undirected.weighted.EdgeWeightedGraph;
import main.java.ds.graph.undirected.IUndirectedGraph;
import main.java.ds.graph.undirected.weighted.UndirectedEdge;

import java.util.Arrays;
import java.util.List;

public class DetectCycleUndirectedGraph {
    private boolean[] visited;
    private boolean hasCycle;

    public DetectCycleUndirectedGraph(IUndirectedGraph undirectedGraph, int source) {
        visited = new boolean[undirectedGraph.getVertexCount()];
        dfsCycleCheck(undirectedGraph, source, source);
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    private void dfsCycleCheck(IUndirectedGraph undirectedGraph, int source, int parent) {
        visited[source] = true;
        for(int i : undirectedGraph.adjacentVertices(source)) {
            if(!visited[i]){
                visited[i] = true;
                dfsCycleCheck(undirectedGraph, i, source);
            }
            else if(i != parent)  // If an adjacent is visited and not parent of current vertex, then there is a cycle.
            {
               hasCycle = true;
               return;
            }
        }
    }

    public static void main(String[] args) {
        List<UndirectedEdge> edgeListOne = Arrays.asList(
                new UndirectedEdge(0,1),
                new UndirectedEdge(0,2),
                new UndirectedEdge(0,3),
                new UndirectedEdge(1,2),
                new UndirectedEdge(3,4)
        );
        DetectCycleUndirectedGraph detectCycleUndirectedGraph = new DetectCycleUndirectedGraph(new EdgeWeightedGraph(5, edgeListOne), 0);
        System.out.println("Cycle? " + detectCycleUndirectedGraph.hasCycle);

        List<UndirectedEdge> edgeListTwo = Arrays.asList(
                new UndirectedEdge(0,1),
                new UndirectedEdge(0,3),
                new UndirectedEdge(1,2),
                new UndirectedEdge(3,4)
        );
        detectCycleUndirectedGraph = new DetectCycleUndirectedGraph(new EdgeWeightedGraph(5, edgeListTwo), 0);
        System.out.println("Cycle? " + detectCycleUndirectedGraph.hasCycle);
    }
}
