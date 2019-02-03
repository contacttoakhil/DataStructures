package main.java.ds.advanced.suffixArray;

import java.util.Arrays;

public class SuffixArray {
    private Suffix[] suffixes;

    public SuffixArray(String input) {
        int il = input.length();
        this.suffixes = new Suffix[il];
        for (int i = 0; i < il; i++) {
            this.suffixes[i] = new Suffix(input, i);
        }
        Arrays.sort(suffixes);
    }

    public int length() {
        return suffixes.length;
    }

    /***
     * Returns the index of the ith smallest suffix into original array
     * @param idx
     * @return
     */
    public int index(int idx) {
        validateIndex(idx, 0, suffixes.length-1);
        return suffixes[idx].getIndex();
    }

    /**
     * Returns the longest common prefix of ith and (i-1)th smallest suffixes
     * @param i
     * @return the longest common prefix of ith and (i-1)th smallest suffixes
     */
    public int lcp(int i) {
        validateIndex(i,1,suffixes.length-1);
        return lcp(suffixes[i], suffixes[i-1]);
    }

    /***
     * Fetch the ith smallest suffix as string.
     * @param i
     * @return the ith smallest suffix as string
     */
    public String fetch(int i) {
        validateIndex(i, 0, suffixes.length-1);
        return suffixes[i].toString();
    }

    /***
     * Rank of the provided input suffix i.e. number of suffixes strictly less than the given input.
     * @param query
     * @return
     */
    public int rank(String query) {
        int lo = 0, hi = suffixes.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compare(query, suffixes[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    private int compare(String query, Suffix suffix) {
        int n = Math.min(query.length(), suffix.length());
        for (int i = 0; i < n; i++) {
            if (query.charAt(i) < suffix.charAt(i)) return -1;
            if (query.charAt(i) > suffix.charAt(i)) return +1;
        }
        return query.length() - suffix.length();
    }

    // longest common prefix of a and b
    private int lcp(Suffix a, Suffix b) {
        int ml = Math.min(a.length(), b.length());
        for (int i = 0; i < ml; i++) {
            if (a.charAt(i) != b.charAt(i)) return i;
        }
        return ml;
    }

    private void validateIndex(int idx, int min, int max) {
        if (idx < min || idx > max) throw new IllegalArgumentException();
    }
}

//https://algs4.cs.princeton.edu/63suffix/