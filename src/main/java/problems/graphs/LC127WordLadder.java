package main.java.problems.graphs;

import java.util.*;

/***
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 * https://leetcode.com/problems/word-ladder/
 *
 * Solution
 * Bi-directional search: The idea behind bidirectional search is to run two simultaneous searches—one forward from the initial state and the other backward from the goal—hoping that the two searches meet in the middle.
 * The motivation is that b^(d/2) + b^(d/2) is much less than b^d. b is branch factor, d is depth. -- section 3.4.6 in Artificial Intelligence - A modern approach by Stuart Russel and Peter Norvig
 *
 * Points about this solution:
 * a) It's much faster than one-end search indeed as explained in wiki.
 * b) BFS isn't equivalent to Queue. Sometimes Set is more accurate representation for nodes of level. (also handy since we need to check if we meet from two ends)
 * c) It's safe to share a visited set for both ends since if they meet same string it terminates early. (vis is useful since we cannot remove word from dict due to bidirectional search)
 * d) It seems like if(set.add()) is a little slower than if(!contains()) then add() but it's more concise.
 *
 */
public class LC127WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList), start = new HashSet<>(), end = new HashSet<>();
        if (!dict.contains(endWord)) return 0;
        start.add(beginWord); end.add(endWord);
        Set<String> visited = new HashSet<>();
        for (int len = 2; !start.isEmpty(); len++) {
            Set<String> next = new HashSet<>();
            for (String w : start) {
                for (int j = 0; j < w.length(); j++) {
                    char[] ch = w.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == w.charAt(j)) continue; // beginWord and endWord not the same, so bypass itself
                        ch[j] = c;
                        String nb = String.valueOf(ch);
                        if (end.contains(nb)) return len; // meet from two ends
                        if (dict.contains(nb) && visited.add(nb)) next.add(nb); // not meet yet, vis is safe to use
                    }
                }
            }
            start = (next.size() < end.size()) ? next : end; // switch to small one to traverse from other end
            end = (start == next) ? end : next;
        }
        return 0;
    }

    public static void main(String[] args) {
        LC127WordLadder wordLadder = new LC127WordLadder();
        System.out.println(wordLadder.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
    }
}

/*
class Pair<K,V> {
    private K key;
    private V value;
    public Pair(K key, V value) {
        this.key = key; this.value = this.value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}*/
