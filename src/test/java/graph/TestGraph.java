package test.java.graph;

import main.java.ds.graph.undirected.AdjListGraph;
import main.java.ds.graph.undirected.AdjMatrixGraph;
import main.java.ds.graph.algorithms.DijkstraShortestPath;
import main.java.ds.graph.algorithms.PrimMST;
import main.java.ds.graph.directed.weighted.DirectedEdge;
import main.java.ds.graph.directed.IDirectedGraph;
import main.java.ds.graph.directed.weighted.EdgeWeightedDirectedGraph;
import main.java.ds.graph.undirected.*;
import main.java.ds.graph.algorithms.KruskalMST;
import main.java.ds.graph.undirected.weighted.EdgeWeightedGraph;
import main.java.ds.graph.undirected.weighted.UndirectedEdge;
import org.junit.Test;
import test.java.DataGenerator;

import java.util.Arrays;
import java.util.List;

public class TestGraph {

    private static final int vertexCount = 4;

    @Test
    public void testAdjMatrixGraph()
    {
        testGraph(new AdjMatrixGraph(vertexCount), DataGenerator.generateEdgesForGraph(vertexCount));
    }

    @Test
    public void testAdjListGraph()
    {
        testGraph(new AdjListGraph(vertexCount), DataGenerator.generateEdgesForGraph(vertexCount));
    }

    @Test
    public void testGraphSearch()
    {
        testGraphSearchOperations(new AdjListGraph(6, new int[][]{{0,5}, {2,4}, {2,3}, {1,2}, {0,1}, {3,4}, {3,5}, {0,2}}));
        testGraphSearchOperations(new AdjMatrixGraph(13, new int[][]{{0,5}, {4,3}, {0,1}, {9,12}, {6,4}, {5,4}, {0,2}, {11,12}, {9,10}, {0,6}, {7,8}, {9,11}, {5,3}}));
    }

    @Test
    public void testMSTAlgorithms()
    {
        List<UndirectedEdge> edgeList = Arrays.asList(
                new UndirectedEdge(0,1,4),
                new UndirectedEdge(0,7,8),
                new UndirectedEdge(1,2,8),
                new UndirectedEdge(1,7,11),
                new UndirectedEdge(2,3,7),
                new UndirectedEdge(2,5,4),
                new UndirectedEdge(2,8,2),
                new UndirectedEdge(3,4,9),
                new UndirectedEdge(3,5,14),
                new UndirectedEdge(4,5,10),
                new UndirectedEdge(5,6,2),
                new UndirectedEdge(6,8,6),
                new UndirectedEdge(6,7,1),
                new UndirectedEdge(7,8,7)
        );
        IUndirectedGraph undirectedGraph = new EdgeWeightedGraph(9);
        for (UndirectedEdge edge : edgeList) {
            undirectedGraph.addEdge(edge);
        }
        KruskalMST kruskalMST = new KruskalMST(undirectedGraph);
        System.out.println("Kruskal's MST: " + kruskalMST.getMST() + " weight: " + kruskalMST.weight());
        PrimMST primMST = new PrimMST(undirectedGraph);
        System.out.println("Prim's MST: " + primMST.getMST() + " weight: " + primMST.weight());
    }

    @Test
    public void testDijkstraAlgorithm() {
        /*
                 0 ----------(4)---------> 1 \                              0->1 (4)                                0         ^1
                 |                            \                             0->2 (1)                                |        /    \
                 |                             (4)                          1->4 (4)                                |       /      \ (4)
                 |                              \                           2->1 (2) not shown   ---->              |      /        \
                 |                               4                          2->3 (4)                               (1)   (2)        >4
                (1)                             /                           3->4 (4)                                |    /
                 |                             /                                                                    |   /
                 |                           (4)                                                                    |  /
                 L                           /                                                                      L /
                 2 -----------(4)-------->  3                                                                       2----------(4)-------->3
         */
        List<DirectedEdge> directedEdgeList = Arrays.asList(
                new DirectedEdge(0,1,4),
                new DirectedEdge(0,2,1),
                new DirectedEdge(1,4,4),
                new DirectedEdge(2,1,2),
                new DirectedEdge(2,3,4),
                new DirectedEdge(3,4,4)
        );
        IDirectedGraph directedGraph = new EdgeWeightedDirectedGraph(5);
        for(DirectedEdge directedEdge : directedEdgeList) {
            directedGraph.addEdge(directedEdge);
        }
        int source = 0;
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(directedGraph, source);
        for(int i=0; i<directedGraph.getVertexCount(); i++) {
            if(dijkstraShortestPath.doesPathExistTo(i)) {
                System.out.println("Path from s(" + source + ") to t(" + i + ") is: " + dijkstraShortestPath.getPathTo(i) + " with length: " + dijkstraShortestPath.getPathLengthTo(i));
            }
            else
            {
                System.out.println("No path from vertex: " + source + " to target vertex: " + i);
            }
        }
    }

    private static void testGraph(IUndirectedGraph graph, int[][] edges)
    {
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        System.out.println(graph);
    }

    private static void testGraphSearchOperations(IUndirectedGraph graph)
    {
        System.out.println(graph);
        System.out.println("backtrack - number of connected vertices to vertex 0 is: " + graph.depthFirstSearch(0));
        System.out.println("BFS - path from 4 to 0 is: ");
        graph.breadthFirstSearch(0, 4);
        System.out.println();
    }
}

