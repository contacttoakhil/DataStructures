package main.java.problems.arrays;

import java.util.*;

public class FindPairOfElementsToGivenSum {
    public static int numberOfPairs(int[] array, long sum) {
        List<Integer> list = new ArrayList<>();
        for(int i:array) {
            list.add(i);
        }
        Set<Integer> set = new HashSet<>(list);
        Set<String> uniquePairs = new HashSet<String>();
        for (int i : array) {
            int x = (int) (sum - i);
            if (set.contains(x)) {
                int[] y = new int[] { x, i };
                Arrays.sort(y);
                uniquePairs.add(Arrays.toString(y));
            }
        }
        return uniquePairs.size();
    }
    public static void main(String[] args) {
        int r =  numberOfPairs(new int[]{1,3,46,1,3,9}, 47);
        System.out.println(r);
    }
}
