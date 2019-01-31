package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LC77Combinations {
    public List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || n < k)
            return result;
        List<Integer> item = new ArrayList<Integer>();
        compute(result, item, 1, n, k); // because it need to begin from 1
        return result;
    }

    private void compute(List<List<Integer>> result, List<Integer> temp, int start, int n, int k  ) {
        if (temp.size() == k) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        for (int i = start; i <= n; i++) {
            temp.add(i);
            compute(result, temp, i + 1, n, k);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC77Combinations cb = new LC77Combinations();
        List<List<Integer>> result = cb.combinations(4,2);
        System.out.println(result);
    }
}
