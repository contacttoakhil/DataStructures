package main.java.problems.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/***
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k. Define a pair (u,v) which consists of one element from the first array and one element
 * from the second array. Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3 Output: [[1,2],[1,4],[1,6]] .
 * The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6], [7,2],[7,4],[11, 2],[7,6],[11,4],[11,6].
 *
 * Hint: Similar to LC23 : Merge K sorted Lists
 *
 */

public class LC373FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        int m = nums1.length, n = nums2.length;
        List<List<Integer>> res = new ArrayList<>();
        if(nums1.length == 0 || nums2.length == 0 || k <= 0) return res;
        for(int i=0; i <m; i++) pq.offer(new Tuple(i, 0, nums1[i]+nums2[0]));
        for(int j = 0; j < Math.min(k, m *n); j++) {
            Tuple t = pq.poll();
            res.add(Arrays.asList(nums1[t.x], nums2[t.y]));
            if(t.y == n - 1) continue;   //t.y is index in num2
            pq.offer(new Tuple (t.x, t.y + 1, nums1[t.x] + nums2[t.y+1]));
        }
        return res;
    }

    public static void main(String[] args) {
        LC373FindKPairsWithSmallestSums smallestSums = new LC373FindKPairsWithSmallestSums();
        //System.out.println(smallestSums.kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6},3));   // [[1, 2], [1, 4], [1, 6]]
        System.out.println(smallestSums.kSmallestPairs(new int[]{1,2,4,5,6}, new int[]{3,5,7,9},20));
    }
}
class Tuple implements Comparable<Tuple> {
    int x, y, val;   // constructor for these three
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    @Override
    public int compareTo (Tuple that) { return this.val - that.val; }
}

