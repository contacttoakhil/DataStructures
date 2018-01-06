package test.java.graph;

import main.java.graph.AdjListGraph;
import main.java.graph.AdjMatrixGraph;
import main.java.graph.IGraph;
import org.junit.Test;
import test.java.DataGenerator;

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
    public void testBasicOperations()
    {
        testGraphSearchOperations(new AdjListGraph(6, new int[][]{{0,5}, {2,4}, {2,3}, {1,2}, {0,1}, {3,4}, {3,5}, {0,2}}));
        testGraphSearchOperations(new AdjMatrixGraph(13, new int[][]{{0,5}, {4,3}, {0,1}, {9,12}, {6,4}, {5,4}, {0,2}, {11,12}, {9,10}, {0,6}, {7,8}, {9,11}, {5,3}}));
    }

    private static void testGraph(IGraph graph, int[][] edges)
    {
        for(int i=0; i<edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1]);
        }
        System.out.println(graph);
    }

    private static <E extends Comparable<E>> void testGraphSearchOperations(IGraph graph)
    {
        System.out.println(graph);
        System.out.println("DFS - number of connected vertices to vertex 0 is: " + graph.depthFirstSearch(0));
        System.out.println("BFS - path from 4 to 0 is: ");
        graph.breadthFirstSearch(0, 4);
        System.out.println();
    }
}
