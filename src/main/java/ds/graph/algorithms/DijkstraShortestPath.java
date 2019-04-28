package main.java.ds.graph.algorithms;

import main.java.ds.graph.GraphUtils;
import main.java.ds.graph.directed.weighted.DirectedEdge;
import main.java.ds.graph.directed.IDirectedGraph;
import main.java.ds.heap.IndexMinPriorityQueue;
import main.java.ds.stack.ArrayStack;
import main.java.ds.stack.IStack;

import java.util.Objects;
import java.util.StringJoiner;

/***
 * Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph, which may represent, for example, road networks. The BFS algorithm cannot solve the shortest path problem because it cannot guarantee that the vertex at the
 * front of the queue is the one closest to source vertex s. The Dijkstra's algorithm is generalization of BFS and it keeps the shortest distance of every vertex i from the source in pathLength table. The shortest distance of source itself is zero.
 *
 * This algorithm uses the greedy approach and always picks up the next closest vertex to the source by making use of priority queue. If we insert the adjacent vertices of vertex r into priority queue and then pick the minimum element from this then it
 * would always be the one closest to the current vertex(r). It makes use of priority queue to save unvisited vertices by distance from source (s). It does not work with negative weight edges and hence validates the edges before proceeding.
 *
 * Dijkstra's original algorithm does not use a min-priority queue and runs in quadratic time O(|V|^2) where |V| is the number of nodes. The implementation based on a min-priority queue implemented by a Fibonacci heap and running in O(|E|+|V|\log |V|)
 * (where |E| is the number of edges) is due to Fredman & Tarjan 1984. This is asymptotically the fastest known single-source shortest-path algorithm for arbitrary directed graphs with unbounded non-negative weights.
 *
 * The efficiency depends on the number of delete-mins (minimum elements deleted) and updates for priority queue. In case of binary heap the complexity is O(ElogV) as there are E updates and each update takes LogV time. If an array is use then it is
 * O(E + V^2).
 *
 *
 * Reference -  https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 */
public class DijkstraShortestPath {

    private DirectedEdge[] prevEdge;         // prevEdge[i] - previous edge on shortest path from source to vertex i.
    private double[] pathLength;       // pathLength[i] - distance(length) of shortest path from source to vertex i.
    private IndexMinPriorityQueue<Double> priorityQueue;    // priority queue of vertices

    public DijkstraShortestPath(IDirectedGraph directedGraph, int source) {
        this.prevEdge = new DirectedEdge[directedGraph.getVertexCount()];
        this.pathLength = GraphUtils.initializePathLength(directedGraph.getVertexCount(), source);
        this.priorityQueue = new IndexMinPriorityQueue<>(directedGraph.getVertexCount());
        validateVertex(source);
        validateEdges(directedGraph);
        compute(directedGraph, source);
        validate(directedGraph, source);
    }

    /**
     * Does path exist to vertex {@code i} from vertex {@code source}? If yes then {@code true}; otherwise {@code false}
     * @param i - target vertex to check
     * @return true if path exists from {@code source} to {@code i}; otherwise {@code false}
     */
    public boolean doesPathExistTo(int i) {
        validateVertex(i);
        return pathLength[i] != Double.POSITIVE_INFINITY;
    }

    /**
     * Returns the shortest path length from vertex {@code source} to vertex {@code i}
     * @param i - the vertex i
     * @return - the shortest path [source->i] length
     */
    public double getPathLengthTo(int i) {
        validateVertex(i);
        return pathLength[i];
    }

    /**
     * Return shortest path to vertex {@code i} from vertex {@code source}.
     * @param i - target vertex
     * @return - shortest path [source->i]
     */
    public String getPathTo(int i) {
        validateVertex(i);
        if(!doesPathExistTo(i)) return null;
        IStack<DirectedEdge> pathStack = new ArrayStack<>();
        for (DirectedEdge e = prevEdge[i]; e != null; e = prevEdge[e.getFrom()]) {
            pathStack.push(e);
        }
        StringJoiner joiner = new StringJoiner(", ");
        while(!pathStack.isEmpty()) {
            joiner.add(Objects.toString(pathStack.pop()));
        }
        return joiner.toString();
    }

    private void validateVertex(int i) {
        int vertexCount = pathLength.length;
        if(i < 0 || i > vertexCount) throw new IllegalArgumentException("Vertex:" + i + " is not in range: [0," + vertexCount +"]" );
    }

    /**
     * Relax vertices in order of distance from {@code source}
     * @param graph - graph
     * @param source - source vertex
     */
    private void compute(IDirectedGraph graph, int source) {
        priorityQueue.insert(source, pathLength[source]);
        while (!priorityQueue.isEmpty()) {
            int minVertex = priorityQueue.delMin();
            for(DirectedEdge edge : graph.adjacentEdges(minVertex)) {
                relax(edge);
            }
        }
    }

    /**
     * relax edge and update priority queue if changed
     * @param edge to relax
     */
    private void relax(DirectedEdge edge) {
        int from = edge.getFrom();
        int to = edge.getTo();
        if(pathLength[to] > pathLength[from] + edge.getWeight()) {
            pathLength[to] = pathLength[from] + edge.getWeight();
            prevEdge[to] = edge;
            updatePathLength(to, pathLength[to]);
        }
    }

    private void updatePathLength(int index, double newKey) {
        if(priorityQueue.contains(index))
            priorityQueue.decreaseKey(index, newKey);
        else
            priorityQueue.insert(index, newKey);
    }

    private void validate(IDirectedGraph directedGraph, int source) {
        GraphUtils.validateSPT(directedGraph, source, pathLength, prevEdge);
    }

    private void validateEdges(IDirectedGraph directedGraph) {
        GraphUtils.validateEdgesForPositiveWeight(directedGraph.edges());
    }
}
