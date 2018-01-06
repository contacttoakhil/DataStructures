package main.java.graph;

import main.java.queue.ArrayQueue;
import main.java.queue.IQueue;
import main.java.stack.ArrayStack;
import main.java.stack.IStack;

public interface IGraph {
    void addEdge(int i, int j);
    int getEdgeCount();
    int getVertexCount();
    Iterable<Integer> adjacentNeighbors(int i);

    default void validateVertex(int i) {
        int vertexCount = getVertexCount();
        if(i < 0 || i > vertexCount) throw new IllegalArgumentException("Vertex:" + i + " not in range: [0," + vertexCount +"]" );
    }

    default String printMe() {
        int edgeCount = getEdgeCount();
        int vertexCount = getVertexCount();
        StringBuilder s = new StringBuilder();
        s.append("vertices:").append(vertexCount).append(", edges: ").append(edgeCount).append(System.lineSeparator());
        for (int i = 0; i < vertexCount; i++) {
            s.append(i).append(": ");
            for (int j : adjacentNeighbors(i)) {
                s.append(j).append(" ");
            }
            s.append(System.lineSeparator());
        }
        return s.toString();
    }

    /**
     * Runs depth first search on the undirected graph. It takes O(E+V) time.
     * @param source the source vertex
     * @return the number of vertices connected to {@code source}
     */
    default int depthFirstSearch(int source) {
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(this, source);
        return depthFirstSearch.getCount();
    }

    /**
     * Runs breadth first search on the undirected graph and then prints ths shortest path from {@code source} to {@code target}
     * @param source the source vertex
     * @param target the target vertex
     */
    default void breadthFirstSearch(int source, int target) {
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(this, source);
        breadthFirstSearch.printPath(target);
    }

    class BreadthFirstSearch {
        private boolean[] visited;      // visited[i] is true if there is a path from source to vertex i; false otherwise.
        private int[] prevEdge;         // prevEdge[i] - previous edge on shortest path fro source to vertex i.
        private int[] pathLength;       // pathLength[i] - number of edges in shortest path from source to vertex i.

        /**
         * Computes the shortest path between the source vertex {@code s} and every other vertex in the graph {@code graph}.
         * @param graph the graph
         * @param source the source vertex
         * @throws IllegalArgumentException if vertex is invalid.
         */
        BreadthFirstSearch(IGraph graph, int source) {
            this.visited = new boolean[graph.getVertexCount()];
            this.prevEdge = new int[graph.getVertexCount()];
            this.pathLength = new int[graph.getVertexCount()];
            graph.validateVertex(source);
            bfs(graph, source);
        }

        private void bfs(IGraph graph, int source) {
            IQueue<Integer> queue = new ArrayQueue<>();
            for(int i=0; i<graph.getVertexCount(); i++) {
                pathLength[i] = Integer.MAX_VALUE;
            }
            pathLength[source] = 0;
            visited[source] = true;
            queue.offer(source);
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for(int n : graph.adjacentNeighbors(v)) {
                    graph.validateVertex(n);
                    if(!visited[n]) {
                        prevEdge[n] = v; // n is adjacent to v so can be reached via v.
                        pathLength[n] = pathLength[v] + 1;
                        visited[n] = true;
                        queue.offer(n);
                    }
                }
            }
        }

        /**
         * Print the shortest path from source to this vertex {@code v}
         * @param v - the target vertex
         */
        void printPath(int v) {
            IStack<Integer> path = new ArrayStack<>();
            int i;
            for (i = v; pathLength[i] != 0; i = prevEdge[i])
                path.push(i);
            path.push(i);
            path.print("->");
        }
    }

    class DepthFirstSearch {
        private boolean[] visited; // visited[i] is true if there is a path from source to vertex i; false otherwise.
        private int count;

        /**
         * Performs depth first search on the {@code graph} and computes the number of vertices connected to {@code source}
         * @param graph the graph to use for depth first search
         * @param source the source vertex
         */
        DepthFirstSearch(IGraph graph, int source) {
            this.visited = new boolean[graph.getVertexCount()];
            graph.validateVertex(source);
            dfs(graph, source);
        }


        private void dfs(IGraph graph, int source) {
            visited[source] = true; count++;
            for(int n : graph.adjacentNeighbors(source)) {
                graph.validateVertex(n);
                if(!visited[n])
                    dfs(graph, n);
            }
        }

        /**
         * Returns the number of vertices connected to {@code source}
         * @return the number of connected vertices to {@code source}
         */
        int getCount() {
            return count;
        }
    }
}
