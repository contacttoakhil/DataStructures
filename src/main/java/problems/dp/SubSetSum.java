package main.java.problems.dp;

/**
 *  Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set (X) with sum equal to given sum K.
 *
 *  a) is sum is zero then always a possible answer for number of elements. All true.
 *  b) if set is empty we cannot have any subset hence no way to get any K. So never a possible answer. All false.
 *  c) if a subset X1 (subset of X without last element in X) has a subset-sum for k then X also has it which is X1. E.g. for X1={1,3,5} and k=8, X1 has a subset-sum then X={1,3,5,7} also has a subset-sum
 *  d) For i/p set X = {1,3,5,7,19} and k=20, if X wants to know possibility of subset-sum for 20 then it does if x1={1,3,5,7} can have a subset-sum of 20-19 i.e. 1. It applies only if k >= 19 i.e. last element in X.
 *
 *  https://stackoverflow.com/a/45427013/1216775
 *
 *  Also check: PerfectSumBT
 */
public class SubSetSum {
    boolean[][] solution;                                               //solution[i][j] will be true if using first i items (index 0 to i-1) we can get sum j from the set; else false. We return solution[k][n] finally.
    int[] input;
    int k;

    public SubSetSum(int[] input, int targetSum) {
        this.input = input;
        this.k = targetSum;
        this.solution = new boolean[input.length+1][k+1];
    }

    public boolean subsetSum() {
        int n = input.length;

        for (int i = 0; i <= n; i++) {     //case b: target-sum of zero is always true.
            solution[i][0] = true;
        }

        for (int j = 0; j <= k; j++) {    // case a: if set is empty then no target-sum possible.
            solution[0][j] = false;
        }

        for (int i = 1; i <= n; i++) {                  // n times
            for (int j = 1; j <= k; j++) {              // k times so time complexity O(n*k)
                if(solution[i-1][j]) {
                    solution[i][j] = solution[i-1][j];      // case c
                    continue;
                }
                if(j >= input[i-1])  {                       // if input element at current index is greater then target-sum then we should ignore it.
                    solution[i][j] = solution[i-1][j-input[i-1]];
                }
            }
        }
        return solution[n][k];
    }

    public boolean[][] getSolution() {      // used in PerfectSumDP problem
        return solution;
    }

    public static void main(String[] args) {
        int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        SubSetSum ss = new SubSetSum(set, sum);
        if (ss.subsetSum())
            System.out.println("Subset exists for given sum");
        else
            System.out.println("Subset does not exist for given sum");
    }
}
