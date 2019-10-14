package main.java.problems.graphs;

import main.java.ds.graph.directed.search.DirectedDepthFirstSearch;
import main.java.ds.graph.directed.weighted.DirectedEdge;
import main.java.ds.graph.directed.weighted.EdgeWeightedDirectedGraph;
import main.java.ds.graph.directed.IDirectedGraph;

import java.util.Arrays;
import java.util.List;

/**
 For a directed graph G the transitive closure G’ would have an edge i->j iff there is a directed path from i to j and the resultant matrix is called connectivity matrix.
 (0) <---- (1) <-------(3)
     ----> (2)

 For the above graph connectivity matrix would be:
 T F T F
 T T T F
 F F T F
 T T T T
 One solution is to use Floyd-Warshall algorithm which takes O(V^3)  time. How can we solve it using dfs?

 Hint: backtrack for each vertex to find vertices reachable from source.

 Ref - https://www.geeksforgeeks.org/transitive-closure-of-a-graph-using-dfs/
 */
public class TransitiveClosure {
    private DirectedDepthFirstSearch[] tc;

    private TransitiveClosure(IDirectedGraph idg) {
        this.tc = new DirectedDepthFirstSearch[idg.getVertexCount()];
        for (int i = 0; i < idg.getVertexCount(); i++) {
            tc[i] = new DirectedDepthFirstSearch(idg, i);
        }
    }

    private boolean doesPathExist(int i, int j) {
        validateVertex(i); validateVertex(j);
        return tc[i].doesPathExist(j);
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= tc.length)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (tc.length-1));
    }

    private void printConnectivityMatrix() {
        System.out.println("Connectivity Matrix is:");
        int vertexCount = tc.length;
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if(doesPathExist(i,j))
                    System.out.print(" T ");
                else
                    System.out.print(" F ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<DirectedEdge> directedEdgeList = Arrays.asList(
                new DirectedEdge(0,2),
                new DirectedEdge(1,0),
                new DirectedEdge(3,1)
        );
        IDirectedGraph directedGraph = new EdgeWeightedDirectedGraph(4);
        for(DirectedEdge directedEdge : directedEdgeList) {
            directedGraph.addEdge(directedEdge);
        }
        TransitiveClosure tc = new TransitiveClosure(directedGraph);
        tc.printConnectivityMatrix();
    }
}
