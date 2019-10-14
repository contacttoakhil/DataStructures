package main.java.problems.graphs;

/***
 * N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.
 *
 * The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
 *
 * The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.
 *
 * Example 1:
 *
 * Input: row = [0, 2, 1, 3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 * Example 2:
 *
 * Input: row = [3, 2, 0, 1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 *
 * Note:
 * 1) len(row) is even and in the range of [4, 60].
 * 2) row is guaranteed to be a permutation of 0...len(row)-1.
 *
 * Solution: The backtracking solution will time out. We can observe:
 * a) instead of couples (0, 1), (2, 3), (4, 5), ..., we could just consider couples (0, 0), (1, 1), (2, 2), ... without changing the answer.
 * b) we could imagine that we have N two-seat couches 0, 1, 2, ..., N-1. This is because the person sitting on the left-most seat of the row must be paired with the person sitting on the second-left-most seat, the third-left-most paired with
 * the fourth-left-most, and so on. Call a person happy if they are with their partner on the same couch. Intuitively, a swap that keeps both persons swapped unhappy is not part of some optimal solution. We'll call this the happy swap assumption (HSA).
 *
 */
public class LC765CouplesHoldingHands {

    public int minSwapsCouples(int[] row) {
        int N = row.length/ 2;
        UF uf = new UF(N);
        for (int i = 0; i < N; i++) {
            int a = row[2*i];
            int b = row[2*i + 1];
            uf.union(a/2, b/2);
        }
        return N - uf.count;
    }

    public static void main(String[] args) {
        LC765CouplesHoldingHands couplesHoldingHands = new LC765CouplesHoldingHands();
        System.out.println(couplesHoldingHands.minSwapsCouples(new int[] {0,2,1,3}));
        System.out.println(couplesHoldingHands.minSwapsCouples(new int[] {3,2,0,1}));
    }
}

class UF {
    private int[] parents;
    public int count;
    UF(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        count = n;
    }

    private int find(int i) {
        if (parents[i] == i) {
            return i;
        }
        parents[i] = find(parents[i]);
        return parents[i];
    }

    public void union(int i, int j) {
        int a = find(i);
        int b = find(j);
        if (a != b) {
            parents[a] = b;
            count--;
        }
    }
}
