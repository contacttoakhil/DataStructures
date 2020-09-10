package main.java.problems.binarySearch;

import java.util.Arrays;

/***
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 *
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * Example 2:
 *
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * Example 3:
 *
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 * Hint: Exact likt LC410: Split Array Largest Sum with only change of from:
 * total = num;
 * to
 * total = 0;
 *
 * (don't forget we want K + 1 pieces so we also have to add 1 to K)
 * (also change the max to min)
 */
public class LC1231DivideChoclates {

    public int maximizeSweetness(int[] sweetness, int K) {
        int lo = Arrays.stream(sweetness).min().getAsInt();
        int hi = Arrays.stream(sweetness).sum();
        if (K+1 == 1) return hi;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (valid(mid, sweetness, K+1))   hi = mid - 1;
            else                            lo = mid + 1;
        }
        return lo;
    }
    public boolean valid(long target, int[] sweetness, int K) {
        int count = 1, total = 0;
        for(int num : sweetness) {
            total += num;
            if (total > target) {
                total = 0;
                count++;
                if (count > K)  return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC1231DivideChoclates divideChoclates = new LC1231DivideChoclates();
        int result = divideChoclates.maximizeSweetness(new int[] {1,2,3,4,5,6,7,8,9}, 5);
        System.out.println(result);
    }
}
