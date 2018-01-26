package main.java.problems.graphs;

import main.java.ds.graph.directed.IDirectedGraph;
import main.java.ds.graph.directed.weighted.DirectedEdge;
import main.java.ds.graph.directed.weighted.EdgeWeightedDirectedGraph;

import java.util.Arrays;
import java.util.List;

/***
 *   Depth First Traversal can be used to detect cycle in a Graph. DFS for a connected graph produces a tree. There is a cycle in a graph only if there is a back edge present in the graph. A back edge is an edge that is from a node to itself (selfloop) or
 *   one of its ancestor in the tree produced by DFS.
 */
public class DetectCycleDirectedGraph {
    private boolean hasCycle;
    private Color[] colors;

    public DetectCycleDirectedGraph(IDirectedGraph directedGraph) {
        int vc = directedGraph.getVertexCount();
        initializeColors(vc);
        for(int i=0; i<vc; i++) {
            if(colors[i] == Color.WHITE) {
               if(dfsCycleCheck(directedGraph, i)) {
                   hasCycle = true;
               }
            }
        }
    }

    private boolean dfsCycleCheck(IDirectedGraph directedGraph, int source) {
        colors[source] = Color.GRAY;
        for(int v : directedGraph.adjacentVertices(source)) {
            if(colors[v] == Color.GRAY)
                return true;
            // If v is not processed and there is a back edge in subtree rooted with v
            if (colors[v] == Color.WHITE && dfsCycleCheck(directedGraph, v))
                return true;
        }
        colors[source] = Color.BLACK;
        return false;
    }

    private void initializeColors(int vertexCount) {
        colors = new Color[vertexCount];
        Arrays.fill(colors, Color.WHITE);
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        List<DirectedEdge> edgeListOne = Arrays.asList(
                new DirectedEdge(0,1),
                new DirectedEdge(0,3),
                new DirectedEdge(1,2),
                new DirectedEdge(2,0),
                new DirectedEdge(3,4)
        );
        DetectCycleDirectedGraph detectCycleUndirectedGraph = new DetectCycleDirectedGraph(new EdgeWeightedDirectedGraph(5, edgeListOne));
        System.out.println("Cycle? " + detectCycleUndirectedGraph.hasCycle); // true

        List<DirectedEdge> edgeListTwo = Arrays.asList(
                new DirectedEdge(0,1),
                new DirectedEdge(0,3),
                new DirectedEdge(1,2),
                new DirectedEdge(3,4)
        );
        detectCycleUndirectedGraph = new DetectCycleDirectedGraph(new EdgeWeightedDirectedGraph(5, edgeListTwo));
        System.out.println("Cycle? " + detectCycleUndirectedGraph.hasCycle); // false
    }
}
enum Color {WHITE, GRAY, BLACK};