package main.java.ds.advanced.trie;

public class Trie {

    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert (String word) {
        int index;
        for(char c : word.toCharArray()) {
            index = c - 'a';
            if(root.children[index] == null)
                root.children[index] = new TrieNode();
            root.children[index].count++;
            root = root.children[index];
        }
        root.terminating++;
    }

    public void delete(String word) {
        int index;
        for(char c : word.toCharArray()) {
            index = c - 'a';
            if(root.children[index] != null)
                root = root.children[index];
            else
                throw new RuntimeException();
        }
        if(root.terminating != 0)
            root.terminating--;
        else
            throw new RuntimeException();
    }

    public int search(String word) {
        int index;
        for(char c : word.toCharArray()) {
            index = c - 'a';
            if(root.children[index] != null)
                throw new RuntimeException("Word not found");
            root = root.children[index];
        }
        return root.count;
    }
}

// Read : https://www.hackerearth.com/practice/notes/trie-suffix-tree-suffix-array/