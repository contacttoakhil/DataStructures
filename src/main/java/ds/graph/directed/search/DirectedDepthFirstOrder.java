package main.java.ds.graph.directed.search;

import main.java.ds.graph.directed.IDirectedGraph;
import main.java.ds.queue.ArrayQueue;
import main.java.ds.queue.IQueue;
import main.java.ds.stack.ArrayStack;
import main.java.ds.stack.IStack;

/**
 * This class determines depth-first search ordering of the vertices in a digraph including preorder, postorder, and reverse postorder.
 */
public class DirectedDepthFirstOrder {
    private boolean[] visited;
    private IQueue<Integer> preOrder;
    private IQueue<Integer> postOrder;

    public DirectedDepthFirstOrder(IDirectedGraph directedGraph) {
        int vc = directedGraph.getVertexCount();
        visited = new boolean[vc];
        preOrder = new ArrayQueue<>();
        postOrder = new ArrayQueue<>();
        for (int i = 0; i < vc; i++) {
            if(!visited[i]) {
                dfs(directedGraph, i);
            }
        }
    }

    private void dfs(IDirectedGraph directedGraph, int s) {
        visited[s] = true;
        preOrder.offer(s);
        for (int v: directedGraph.adjacentVertices(s)) {
            if(!visited[v]) {
                dfs(directedGraph, v);
            }
        }
        postOrder.offer(s);
    }

    /**
     * Returns the vertices in postorder.
     * @return the vertices in postorder, as an iterable of vertices
     */
    public Iterable<Integer> post() {
        return postOrder;
    }

    /**
     * Returns the vertices in preorder.
     * @return the vertices in preorder, as an iterable of vertices
     */
    public Iterable<Integer> pre() {
        return preOrder;
    }

    /**
     * Returns the vertices in reverse postorder.
     * @return the vertices in reverse postorder, as an iterable of vertices
     */
    public Iterable<Integer> reversePost() {
        IStack<Integer> reverse = new ArrayStack<>();
        for (int v : postOrder)
            reverse.push(v);
        return reverse;
    }
}
