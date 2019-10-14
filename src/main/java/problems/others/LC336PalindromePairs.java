package main.java.problems.others;

import java.util.*;

public class LC336PalindromePairs {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        TrieNodeCustom root = new TrieNodeCustom();
        for (int i = 0; i < words.length; i++) addWord(root, words[i], i);
        for (int i = 0; i < words.length; i++) search(words, i, root, res);
        return res;
    }

    private void addWord(TrieNodeCustom root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';
            if (root.next[j] == null) root.next[j] = new TrieNodeCustom();
            if (isPalindrome(word, 0, i)) root.list.add(index);
            root = root.next[j];
        }
        root.list.add(index);
        root.index = index;
    }

    private void search(String[] words, int wordIdx, TrieNodeCustom root, List<List<Integer>> res) {
        String word = words[wordIdx];
        for (int c = 0; c < word.length(); c++) {   // ltIdx is index for each letter in word.
            // root.index > - 1 means word exists in input words array.
            // root.index != i means it is not same word
            if (root.index > -1 && root.index != wordIdx && isPalindrome(word, c, word.length() - 1))
                res.add(Arrays.asList(wordIdx, root.index));
            root = root.next[word.charAt(c) - 'a'];
            if (root == null) return;
        }
        for (int j : root.list) {
            if (wordIdx == j) continue;
            res.add(Arrays.asList(wordIdx, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }
        return true;
    }

    public static void main(String[] args){
        LC336PalindromePairs sol = new LC336PalindromePairs();
        System.out.println(sol.palindromePairs(new String[] {"ba", "a", "aaa"}));
        System.out.println(sol.palindromePairs(new String[] {"bat","tab","cat"}));
        //System.out.println(sol.palindromePairs(new String[] {"abcd","dcb", "dcba","lls","s","sssll"}));

    }
}

class TrieNodeCustom {
    TrieNodeCustom[] next;
    int index;
    List<Integer> list;

    TrieNodeCustom() {
        next = new TrieNodeCustom[26];
        index = -1;
        list = new ArrayList<>();
    }
}
//https://leetcode.com/problems/palindrome-pairs/discuss/79195/on-k2-java-solution-with-trie-structure
