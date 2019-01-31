package main.java.problems.arrays;

import java.util.Collections;
import java.util.PriorityQueue;

/*
Given two sorted arrays of integers, A and B, find the set of n largest numbers where each number is the sum of an integer from A and an integer from B.
 */
public class NLargestSumValues {
    private static void compute(int[] a, int[] b, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(5, Collections.reverseOrder());
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                pq.add(a[i] + b[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(pq.poll());
        }
    }
    public static void main(String[] args) {
        compute(new int[] {1,3,4,8,10}, new int[] {20,22,30,40}, 3);
    }
}
