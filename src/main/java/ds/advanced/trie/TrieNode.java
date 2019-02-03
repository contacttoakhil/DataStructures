package main.java.ds.advanced.trie;

public class TrieNode {

    public int count = 0;
    public int terminating;
    public TrieNode[] children;

    public TrieNode() {
        children = new TrieNode[26];
    }
}
