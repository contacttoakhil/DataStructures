package main.java.problems.graphs;

import java.util.*;
import java.util.stream.Collectors;

/***
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 *
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.
 *
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 *
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.
 *
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 *
 * Note:
 *
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid.
 * You may assume start and end string is not the same.
 *
 *
 * Example 1:
 *
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 *
 * return: 1
 *
 *
 * Example 2:
 *
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 *
 * return: 2
 *
 *
 * Example 3:
 *
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 *
 * return: 3
 */
public class LC433MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        if(start.equals(end)) return 0;
        Set<String> bankSet = Arrays.stream(bank).collect(Collectors.toSet());
        char[] charSet = new char[]{'A', 'C', 'G', 'T'};
        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if(curr.equals(end)) return level;
                char[] currArray = curr.toCharArray();
                for(int j = 0; j < currArray.length; j++) {
                    char old = currArray[j];
                    for(char c: charSet) {
                        currArray[j] = c;
                        String next = new String(currArray);
                        if(!visited.contains(next) && bankSet.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currArray[j] = old;
                }
            }
            level++;
        }
        return -1;
    }

    public static void main(String[] args) {
        LC433MinimumGeneticMutation minimumGeneticMutation = new LC433MinimumGeneticMutation();
        System.out.println(minimumGeneticMutation.minMutation("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"}));
    }
}
