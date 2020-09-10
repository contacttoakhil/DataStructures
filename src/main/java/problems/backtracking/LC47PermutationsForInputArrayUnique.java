package main.java.problems.backtracking;

import java.util.*;

public class LC47PermutationsForInputArrayUnique {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] input) {
        //Arrays.sort(input);
        compute(result, new ArrayList<>(), input, new boolean[input.length]);
        return result;
    }
    private void compute(List<List<Integer>> result, List<Integer> temp, int[] input, boolean[] visited) {
        if(temp.size() == input.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if(visited[i] || i > 0 && input[i] == input[i-1] && !visited[i - 1]) continue;
            visited[i] = true;
            temp.add(input[i]);
            compute(result, temp, input, visited);
            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    List<String> resultList = new ArrayList<>();
    public List<String> permuteUniqueString(char[] input) {
        Map<Character,Integer> countMap = getMap(input);
        char[] str = new char[countMap.size()];
        int[] count = new int[countMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            str[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }
        permuteUtil(str, count, new char[input.length], 0);
        return resultList;
    }

    private void permuteUtil(char[] str, int[] count, char[] temp, int level) {
        if (level == temp.length) {
            resultList.add(new String(temp));
            return;
        }
        for(int i = 0; i < str.length; i++) {
            if(count[i] == 0) {
                continue;
            }
            temp[level] = str[i];
            count[i]--;
            permuteUtil(str, count, temp, level + 1);
            count[i]++;
        }
    }

    private Map<Character, Integer> getMap(char[] input) {
        Map<Character, Integer> countMap = new TreeMap<>();
        for (char ch : input) {
            countMap.compute(ch, (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
        }
        return countMap;
    }

    public static void main(String[] args) {
        LC47PermutationsForInputArrayUnique pm = new LC47PermutationsForInputArrayUnique();
        System.out.println(pm.permuteUnique(new int[] {1,1,2}));//[[1, 1, 2], [1, 2, 1], [2, 1, 1]]
        System.out.println(pm.permuteUniqueString("AABC".toCharArray())); //[AABC, AACB, ABAC, ABCA, ACAB, ACBA, BAAC, BACA, BCAA, CAAB, CABA, CBAA]

    }
}
