package main.java.problems.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * Given a set of words (without duplicates), find all word squares you can build from them.
 * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 */
public class LC425WordSquares {

    private Trie trie = null;
    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0)
            return result;
        int len = words[0].length();
        trie = new Trie(words);
        for (String w : words)
            search(len, new ArrayList<>(Collections.singletonList(w)));
        return result;
    }

    private void search(int len, List<String> temp) {
        if (temp.size() == len) {
            result.add(new ArrayList<>(temp));
            return;
        }
        int idx = temp.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : temp)
            prefixBuilder.append(s.charAt(idx));
        List<String> startWith = trie.findByPrefix(prefixBuilder.toString());
        for (String sw : startWith) {
            temp.add(sw);
            search(len, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC425WordSquares wordSquares = new LC425WordSquares();
        System.out.println(wordSquares.wordSquares(new String[]{"ball","area","lead","lady"})); //["ball","area","lead","lady"]
    }
}

class TrieNode {
    List<String> startWith;
    TrieNode[] children;

    TrieNode() {
        startWith = new ArrayList<>();
        children = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;

    Trie(String[] words) {
        root = new TrieNode();
        for (String w : words) {
            TrieNode cur = root;
            for (char ch : w.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null)
                    cur.children[idx] = new TrieNode();
                cur.children[idx].startWith.add(w);
                cur = cur.children[idx];
            }
        }
    }

    List<String> findByPrefix(String prefix) {
        List<String> ans = new ArrayList<>();
        TrieNode cur = root;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (cur.children[idx] == null)
                return ans;

            cur = cur.children[idx];
        }
        ans.addAll(cur.startWith);
        return ans;
    }
}
