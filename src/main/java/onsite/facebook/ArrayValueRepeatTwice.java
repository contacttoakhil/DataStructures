package main.java.onsite.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Given an integer n, create an array such that each value is repeated twice.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: [1, 1, 2, 2, 3, 3]
 * Example 2:
 *
 * Input: n = 4
 * Output: [1, 1, 2, 2, 3, 3, 4, 4]
 *
 * =======================================================
 * Follow up 1: After creating it, find a permutation such that each number is spaced in such a way, they are at a "their value" distance from the second occurrence of the same number. Return any 1 permutation if it exists. Empty array if no permutation exists.
 *
 * Example 1:
 *
 * Input: n = 3 --> This is the array - [1, 1, 2, 2, 3, 3]
 * Output: [3, 1, 2, 1, 3, 2]
 * Explanation:
 * The second 3 is 3 digits away from the first 3.
 * The second 2 is 2 digits away from the first 2.
 * The second 1 is 1 digit away from the first 1.
 *
 * =======================================================
 * Follow up 2: Return all possible permutations.
 */
public class ArrayValueRepeatTwice {

    List<List<Integer>> result = new ArrayList<>();

    public int[] generate(int n) {
        int[] gens = new int[2*n];
        int idx = -1;
        for (int i = 1; i <= n; i++)
            gens[++idx] = gens[++idx] = i;
        return gens;
    }

    public void permute(int[] gens) {
        int[] temp = new int[gens.length];
        bt(temp, temp.length/2, 1);
    }

    public void bt(int[] temp, int n, int i) {
        if (i == n+1) {
            result.add(Arrays.stream(temp).boxed().collect(Collectors.toList()));
            return;
        }

        for (int k=0; (k+i+1)<temp.length; ++k) {
            if (temp[k] == 0 && temp[k+i+1] == 0) {
                temp[k] = temp[k+i+1] =i;
                bt(temp, n, i+1);
                temp[k] = temp[k+i+1] = 0;
            }
        }
    }

    public static void main(String[] args) {
        ArrayValueRepeatTwice repeatTwice = new ArrayValueRepeatTwice();
        int[] gens = repeatTwice.generate(3);
        System.out.println(Arrays.toString(gens));  //[1, 1, 2, 2, 3, 3]

        repeatTwice.permute(gens);
        System.out.println(repeatTwice.result);   // [[3, 1, 2, 1, 3, 2], [2, 3, 1, 2, 1, 3]]
    }

}
