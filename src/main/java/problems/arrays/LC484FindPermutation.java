package main.java.problems.arrays;

import java.util.Arrays;

public class LC484FindPermutation {

    public int[] findPermutation(String s) {
        int n = s.length(), arr[] = new int[n + 1];
        for (int i = 0; i <= n; i++) arr[i] = i + 1; // sorted
        for (int h = 0; h < n; h++) {
            if (s.charAt(h) == 'D') {
                int l = h;
                while (h < n && s.charAt(h) == 'D') h++;
                reverse(arr, l, h);
            }
        }
        return arr;
    }

    void reverse(int[] arr, int l, int h) {
        while (l < h) {
            arr[l] ^= arr[h];
            arr[h] ^= arr[l];
            arr[l] ^= arr[h];
            l++; h--;
        }
    }

    public static void main(String[] args) {
        LC484FindPermutation findPermutation = new LC484FindPermutation();
        int[] result = findPermutation.findPermutation("DI");
        System.out.println(Arrays.toString(result));     // [2,1,3] or [3,1,2]
        result = findPermutation.findPermutation("D");
        System.out.println(Arrays.toString(result));

    }
}
