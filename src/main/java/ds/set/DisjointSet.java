package main.java.ds.set;

import java.util.stream.IntStream;

/**
 * A disjoint-set data structure (also called a union–findSubset data structure or merge–findSubset set) is a data structure that keeps track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets. It provides near-constant-time operations
 * to add new sets, to merge existing sets, and to determine whether elements are in the same set. The disjoint-sets play a key role in Kruskal's algorithm for finding the minimum spanning tree of a graph.
 *
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 */
public class DisjointSet {
    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;     // number of components

    public DisjointSet(int count) {
        this.count = count;
        this.parent = IntStream.rangeClosed(0,count).toArray(); // populate parent so that parent[i] = i
        this.rank = new byte[count];
    }

    /**
     *  Finds the name of the set (int) containing the given element.
     *  Performs path compression along the way.
     *
     *  @param p the element sought.
     *  @return the set containing r.
     **/
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }

    /**
     *  Unites two disjoint sets into a single set.  A union-by-rank heuristic is used to choose the new root.
     *
     *  @param p the root of the first set.
     *  @param q the root of the other set.
     **/
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }

    /**
     * Returns true if the the two sites are in the same component.
     * @param p the integer representing one site
     * @param q the integer representing other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component(set); {@code false} otherwise
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }
}
