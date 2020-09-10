package main.java.problems.graphs;

import java.util.*;

/***
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 * https://leetcode.com/problems/word-ladder-ii/
 * https://leetcode.com/problems/word-ladder-ii/discuss/40447/Share-two-similar-Java-solution-that-Accpted-by-OJ.
 */
public class LC126WordLadderII {


    private Set<String> dict;
    private List<List<String>> result = new ArrayList<>();
    private Map<String, ArrayList<String>> nodeNeighbors = new HashMap<>();// Neighbors for every node
    private Map<String, Integer> distance = new HashMap<>();// Distance of every node from the start node

    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        dict = new HashSet<>(wordList); dict.add(start);
        for (String word : dict)
            nodeNeighbors.put(word, new ArrayList<>());
        bfs(start, end);
        dfs(start, end, new ArrayList<>());
        return result;
    }

    // Use BFS to find the shortest distance between start and end, tracing the distance of crossing nodes from start node to end node, and store node's next level neighbors to HashMap;
    private void bfs(String start, String end) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        distance.put(start, 0);
        while (!queue.isEmpty()){
            int size = queue.size();
            boolean foundEnd = false;
            for (int i=0; i<size; i++) {
                String current = queue.poll();
                for(String neighbor : getNeighbors(current)){
                    nodeNeighbors.get(current).add(neighbor);
                    if(distance.containsKey(neighbor)) continue;                // neighbor visited then continue
                    distance.put(neighbor, distance.get(current) + 1);
                    if(end.equals(neighbor)) foundEnd = true;
                    else queue.offer(neighbor);
                }
                if(foundEnd) break;
            }
        }
    }

    private List<String> getNeighbors(String word) {
        char[] wordArray = word.toCharArray();
        List<String> output = new ArrayList<>();
        for (char ch ='a'; ch <= 'z'; ch++) {
            for (int idx = 0; idx < word.length() ; idx++) {
                if(wordArray[idx] == ch) continue;
                var old = wordArray[idx];
                wordArray[idx] = ch;
                if(dict.contains(String.valueOf(wordArray))) output.add(String.valueOf(wordArray));
                wordArray[idx] = old;
            }
        }
        return output;
    }

    // DFS: output all paths with the shortest distance.
    private void dfs(String start, String end, List<String> temp) {
        temp.add(start);
        if(end.equals(start))
            result.add(new ArrayList<>(temp));
        else
        {
            for (String neighbor : getNeighbors(start)) {
                if(distance.get(neighbor) == distance.get(start) + 1)
                    dfs(neighbor, end, temp);
            }
        }
        temp.remove(temp.size()-1);
    }

    /*private Map<String,List<String>> parentsMap = new HashMap<>();    // dot -> dog then entry would be {dog,[dot]}
    private List<List<String>> results;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        results =  new ArrayList<>();
        if (wordList.isEmpty())      return results;
        int minStepsToEndWord = Integer.MAX_VALUE;                                  // steps needed to reach endWord from beginWord
        Map<String,Integer> distanceMap = initDistanceMap(wordList, beginWord);     // distance of each node from beginWord, initially it is MAX_VALUE;
        Queue<String> queue= new ArrayDeque<String>();
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int step = distanceMap.get(word) + 1;               //'step' indicates how many steps are needed to travel to one word.
            if (step > minStepsToEndWord) break;                // Why? This is BFS and the minimum path (step) should be discovered in min-steps-to-end-word.
            for (int i = 0; i < word.length(); i++){
                StringBuilder builder = new StringBuilder(word);
                for (char ch = 'a';  ch <= 'z'; ch++){
                    builder.setCharAt(i, ch);
                    String new_word = builder.toString();
                    if (!distanceMap.containsKey(new_word) || step > distanceMap.get(new_word))      // word not available or word taking in backward direction, ignore them.
                        continue;
                    if (step < distanceMap.get(new_word)){        // unexplored word in forward direction. Can step become equal to ladder.get(new_word) ? Yes and we update parentsMap.
                        queue.add(new_word);
                        distanceMap.put(new_word, step);
                    }
                    parentsMap.computeIfAbsent(new_word, k->new LinkedList<>()).add(word);
                    if (new_word.equals(endWord))
                        minStepsToEndWord = step;
                }
            }
        }
        LinkedList<String> result = new LinkedList<String>(); // using char[]
        backTrace(endWord,beginWord,result);
        return results;
    }

    *//*
       Can step become equal to ladder.get(new_word) ? Yes in that case we update key in parentsMap
       hit-> hot-> dot, lot -> dog, log -> cog
       When processing dog we will traverse:
                    //   dog -> log (d<->l), step = 3 and ladder.get(log) =  MAX (add to queue and update parentsMap)
                    //   dog -> cog (d<->c), step = 4 and ladder.get(cog) = MAX (add to queue and update parentsMap)
                    //  When we come to process log now:
                    //   log -> dog (backwards so continue)
                    //   log -> cog, step = 4 and ladder.get(cog) is also 4 so simply update parentsMap from cog->[dog] to cog-> [dog,log]
                    // -> cog we do make entry for cog in parentsMap. Then when we do for log then step = 4 and also ladder.grt(cog) is also 4.
                    // We dont add log to queue but add it to parentsMap-key cog.
                    // It is a KEY line. If one word already appeared in one ladder,
                    // Do not insert the same word inside the queue twice. Otherwise it gets TLE.


     *//*
    private Map<String, Integer> initDistanceMap(List<String> wordList, String beginWord) {
        Map<String,Integer> distanceMap = new HashMap<>();
        for (String string:wordList)
            distanceMap.put(string, Integer.MAX_VALUE);
        distanceMap.put(beginWord, 0);
        return distanceMap;
    }

    private void backTrace(String word,String start,List<String> list){
        if (word.equals(start)){
            list.add(0,start);
            results.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0,word);
        if (parentsMap.get(word)!=null)
            for (String s: parentsMap.get(word))
                backTrace(s,start,list);
        list.remove(0);
    }*/

    public static void main(String[] args) {
        LC126WordLadderII wordLadderII = new LC126WordLadderII();
        List<List<String>> result1 = wordLadderII.findLadders("hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println(result1);   // [[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
        List<List<String>> result2 = wordLadderII.findLadders("red","tax",Arrays.asList("ted","tex","red","tax","tad","den","rex","pee"));
        System.out.println(result2);  // [red, ted, tad, tax], [red, ted, tex, tax], [red, rex, tex, tax]]
    }
}

// https://fizzbuzzed.com/top-interview-questions-4/