package main.java.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/***
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 *
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 * https://leetcode.com/problems/russian-doll-envelopes/
 */
public class LC354RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0  || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        sortEnvelopes(envelopes);
        int dp[] = new int[envelopes.length];   // By default it would be filled with zero (initial element).
        int len = 0;                            // element from idx zero to len would be sorted in array dp.
        for(int[] envelope : envelopes){
            int index = getInsertionPoint(dp, len, envelope[1]);
            dp[index] = envelope[1];
            if(index == len)
                len++;
        }
        return len;
    }

    private int getInsertionPoint(int[] dp, int len, int num) {
        int idx = Arrays.binarySearch(dp, 0, len, num);
        // If element does not exists then binarySearch would return -i-1 or -(i+1) as insertion point, e.g.
        // e.g. if dp = [0,8,0,0,0], len = 2, num=4 then ideally 4 should be inserted at second place => [0,4,8,....] so method would return -2.
        if(idx < 0)
            idx = - (idx + 1); // correct idx to make it positive.
        return idx;
    }

    private void sortEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>(){     // width ascending and height descending.
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });
    }

    public static void main(String[] args) {
        int[][] envelopes = new int[][] {
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3}
        };
        LC354RussianDollEnvelopes russianDollEnvelopes = new LC354RussianDollEnvelopes();
        System.out.println(russianDollEnvelopes.maxEnvelopes(envelopes));
    }
}
