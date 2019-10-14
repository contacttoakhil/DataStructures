package main.java.problems.backtracking;

import java.util.*;

public class LC140WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty())  return Collections.emptyList();
        Set<String> wordSet = new HashSet<>();
        int maxLen = 0;
        for (String word : wordDict) {
            wordSet.add(word);
            maxLen = Math.max(maxLen, word.length());
        }
        List<String> res = dfs(s, wordSet, new HashMap<>(), 0, maxLen);
        return res;
    }

    private List<String> dfs(String s, Set<String> wordSet, Map<Integer, List<String>> map, int startIdx, int maxLen) {
        if (map.containsKey(startIdx))
            return map.get(startIdx);
        List<String> result = new ArrayList<>();
        for (int i = startIdx; i < s.length() && i < startIdx + maxLen; i++) {
            String pref = s.substring(startIdx, i + 1);
            if (wordSet.contains(pref)) {
                if (i == s.length() - 1) {
                    result.add(pref);
                    return result;
                }
                List<String> tmp = dfs(s, wordSet, map, i + 1, maxLen);
                for (String suff : tmp) {
                    result.add(pref + " " + suff);
                }
            }
        }
        map.put(startIdx, result);
        return result;
    }

    public static void main(String[] args) {
        LC140WordBreakII sol = new LC140WordBreakII();
        String s = "catsanddog";
        System.out.println(sol.wordBreak("catsanddog", Arrays.asList("cat", "cats","and","sand","dog")));
    }
}
