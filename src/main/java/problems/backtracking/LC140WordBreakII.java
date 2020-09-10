package main.java.problems.backtracking;

import java.util.*;

public class LC140WordBreakII {

    private Map<String,List<String>> cache = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        return backtrack(s,wordDict);
    }
    public List<String> backtrack(String s, List<String> wordDict){
        if(cache.containsKey(s)) return cache.get(s);
        List<String> result = new ArrayList<>();
        for(String word: wordDict) {
            if(!s.startsWith(word)) continue;
            String next = s.substring(word.length());
            if(next.isEmpty()) {
                result.add(word);
                continue;
            }
            for(String sub: backtrack(next, wordDict))
                result.add(word + " " + sub);
        }
        cache.put(s, result);
        return result;
    }

    public static void main(String[] args) {
        LC140WordBreakII sol = new LC140WordBreakII();
        System.out.println(sol.wordBreak("catsanddog", Arrays.asList("cat", "cats","and","sand","dog"))); // [cat sand dog, cats and dog]
    }
}
