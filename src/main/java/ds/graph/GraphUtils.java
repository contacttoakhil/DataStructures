package main.java.ds.graph;

import main.java.ds.graph.directed.IDirectedGraph;
import main.java.ds.graph.directed.weighted.DirectedEdge;
import main.java.ds.graph.undirected.IUndirectedGraph;
import main.java.ds.graph.undirected.weighted.UndirectedEdge;
import main.java.ds.set.DisjointSet;
import main.java.ds.stack.ArrayStack;
import main.java.ds.stack.IStack;

import java.util.Arrays;

public class GraphUtils {
    /**
     * Validates the mst for graph {@code undirectedGraph}
     * @param undirectedGraph graph whose mst needs to validated
     * @param mstEdges edges in mst of graph {@code undirectedGraph}
     * @param mstWeight weight of mst for graph {@code undirectedGraph}
     */
    public static void validateMST(IUndirectedGraph undirectedGraph, Iterable<UndirectedEdge> mstEdges, double mstWeight) {
        validateWeight(mstEdges, mstWeight);
        DisjointSet ds = new DisjointSet(undirectedGraph.getVertexCount());
        validateNoCycle(ds, mstEdges);
        validateSpanningForest(undirectedGraph, ds);
        validateMinimumSpanningTree(undirectedGraph, mstEdges);
    }

    /**
     * Validate SPT graph for optimal conditions.
     * @param graph to validate
     * @param source to validate
     * @param pathLength
     * @param prevEdge
     */
    public static void validateSPT(IDirectedGraph graph, int source, double[] pathLength, DirectedEdge[] prevEdge) {
        validateEdgesForPositiveWeight(graph.edges());
        validateSourceVertex(source, pathLength, prevEdge);
        validatePathLengthAndPrevEdge(graph, source, pathLength, prevEdge);
        validateEdgeWeights(graph, pathLength);
        validateEdgeWeightForTightness(graph, pathLength, prevEdge);
    }

    public static double[] initializePathLength(int vertexCount) {
        return initializePathLength(vertexCount, 0);
    }

    /**
     * Initialize path-length for every vertex in graph to infinity and initialize path length of {@code source} to zero.
     * @param vertexCount number of vertices in the graph.
     * @param source source of graph.
     */
    public static double[] initializePathLength(int vertexCount, int source) {
        double[] array = new double[vertexCount];
        Arrays.fill(array, Double.POSITIVE_INFINITY);
        array[source] = 0.0;
        return array;
    }

    /**
     * Initialize path-length for every vertex in graph to infinity and initialize path length of {@code source} to zero.
     * @param vertexCount number of vertices in the graph.
     * @param source source of graph.
     */
    public static int[] initializePathLengthForIntArray(int vertexCount, int source) {
        int[] array = new int[vertexCount];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[source] = 0;
        return array;
    }

    /**
     * Print the shortest path from source to this vertex {@code v}
     * @param v - the target vertex
     */
    public static void printPath(int v, int[] pathLength, int[] prevEdge) {
        IStack<Integer> path = new ArrayStack<>();
        int i;
        for (i = v; pathLength[i] != 0; i = prevEdge[i])
            path.push(i);
        path.push(i);
        path.print("->");
    }

    /*********************  private methods for SPT *************************************************************/
    /**
     * Ensure that all edges in the graph has positive weight (no cycle or negative weight edges).
     * @param edges to validate
     */
    public static void validateEdgesForPositiveWeight(Iterable<DirectedEdge> edges){
        for (DirectedEdge e : edges) {
            if (e.getWeight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }
    }

    private static void validateEdgeWeightForTightness(IDirectedGraph graph, double[] pathLength, DirectedEdge[] prevEdge) {
        for (int t = 0; t < graph.getVertexCount(); t++) {
            DirectedEdge edge = prevEdge[t];
            if(edge != null) {
                int f = edge.getFrom();
                if(t != edge.getTo()) {
                    throw new IllegalStateException("For edge: " + edge + " to is" + edge.getTo() + " and " + t + " are not matching.");
                }
                if ( (pathLength[f] + edge.getWeight()) != pathLength[t] ) {
                    throw new IllegalStateException("edge " + edge + " on shortest path is not tight.");
                }
            }
        }
    }

    private static void validateEdgeWeights(IDirectedGraph graph, double[] pathLength) {
        // for every edge i->j , pathLength[j] <= patLength[i] + e.weight
        for (int i = 0; i < graph.getVertexCount(); i++) {
            for (DirectedEdge edge : graph.adjacentEdges(i)) {
                int j = edge.getTo();
                if ( (pathLength[i] + edge.getWeight()) < pathLength[j] ) {
                    throw new IllegalStateException("edge " + edge + " is not relaxed");
                }
            }
        }
    }

    private static void validatePathLengthAndPrevEdge(IDirectedGraph graph, int source, double[] pathLength, DirectedEdge[] prevEdge) {
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (i == source) {
                continue;
            }
            if (prevEdge[i] == null && pathLength[i] != Double.POSITIVE_INFINITY) {
                throw new IllegalStateException("prev edge and path length for vertex:" + i + " are inconsistent.");
            }
        }
    }

    private static void validateSourceVertex(int source, double[] pathLength, DirectedEdge[] prevEdge) {
        if( (pathLength[source] != 0.0) || (prevEdge[source] != null) ) {
            throw new IllegalStateException("path length and prev edge for source are inconsistent.");
        }
    }

    /*********************  private methods for MST *************************************************************/

    //  check that it is a minimal spanning forest (cut optimality conditions)
    private static void validateMinimumSpanningTree(IUndirectedGraph undirectedGraph, Iterable<UndirectedEdge> mstEdges) {
        for (UndirectedEdge e : mstEdges) {
            // all edges in MST except e
            DisjointSet ds = new DisjointSet(undirectedGraph.getVertexCount());
            for (UndirectedEdge f : mstEdges) {
                int x = f.either(), y = f.other(x);
                if (f != e) ds.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (UndirectedEdge f : undirectedGraph.edges()) {
                int x = f.either(), y = f.other(x);
                if (!ds.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        throw new IllegalStateException("Edge " + f + " violates cut optimality conditions");
                    }
                }
            }
        }
    }

    // check that it is a spanning forest
    private static void validateSpanningForest(IUndirectedGraph undirectedGraph, DisjointSet ds) {
        for (UndirectedEdge e : undirectedGraph.edges()) {
            int either = e.either(), other = e.other(either);
            if (!ds.connected(either, other)) {
                throw new IllegalStateException("Not a spanning forest");
            }
        }
    }

    // check that no cycle
    private static void validateNoCycle(DisjointSet ds, Iterable<UndirectedEdge> mstEdges) {
        for (UndirectedEdge e : mstEdges) {
            int either = e.either(), other = e.other(either);
            if (ds.connected(either, other)) {
                throw new IllegalStateException("Not supposed to but contains cycle");
            }
            ds.union(either, other);
        }
    }

    // check and verify weight
    private static void validateWeight(Iterable<UndirectedEdge> mstEdges, double mstWeight) {
        double total = 0.0;
        for (UndirectedEdge e : mstEdges) {
            total += e.weight();
        }
        double FLOATING_POINT_EPSILON = 1E-12;
        if (Math.abs(total - mstWeight) > FLOATING_POINT_EPSILON) {
            throw new IllegalStateException("Weight of edges:" + total + " does not equal weight()" + mstWeight);
        }
    }
}
