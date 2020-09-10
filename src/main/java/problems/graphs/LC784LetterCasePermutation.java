package main.java.problems.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 *
 * Input: S = "12345"
 * Output: ["12345"]
 */
public class LC784LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {                      // ◀ BFS
        if (S == null) return new LinkedList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(S);
        for (int i = 0; i < S.length(); i++) {
            if (Character.isDigit(S.charAt(i))) continue;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String cur = queue.poll();
                char[] chs = cur.toCharArray();
                chs[i] = Character.toUpperCase(chs[i]);
                queue.offer(String.valueOf(chs));
                chs[i] = Character.toLowerCase(chs[i]);
                queue.offer(String.valueOf(chs));
            }
        }
        return new LinkedList<>(queue);
    }

    private List<String> res = new ArrayList<>();

    public List<String> letterCasePermutation2(String S) {                      // ◀ DFS
        char[] a = S.toLowerCase().toCharArray();
        helper(a, 0);
        return res;
    }
    void helper(char[] temp, int pos){
        if(pos==temp.length){
            res.add(new String(temp));
            return;
        }
        helper(temp, pos+1);
        if(Character.isLetter(temp[pos])) {
            temp[pos] = Character.toUpperCase(temp[pos]);
            helper(temp, pos+1);
            temp[pos] = Character.toLowerCase(temp[pos]);
        }
    }

    public static void main(String[] args) {
        LC784LetterCasePermutation permutation = new LC784LetterCasePermutation();
        System.out.println(permutation.letterCasePermutation("a1b2"));
        System.out.println(permutation.letterCasePermutation2("a1b2"));
    }
}
