package main.java.problems.others;

/***
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
 *
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)
 *
 * Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
 *
 *
 *
 * Example 1:
 *
 * Input: 20
 * Output: 6
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9.
 * 9 converts to 6.
 * 10 converts to 01 which is just 1.
 * 16 converts to 91.
 * 18 converts to 81.
 * 19 converts to 61.
 * Example 2:
 *
 * Input: 100
 * Output: 19
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 *
 *
 * Note:
 *
 * 1 <= N <= 10^9
 */
public class LC1088ConfusingNumberII {
    private int count = 0;
    private int n = 0;

    public int confusingNumberII(int N) {
        n = N;
        search(0);
        return count;
    }

    private void search(long num) {
        if (num > n) return;
        if (num != 0) {
            if (rotate(num) != num) ++count;
        }
        if (num != 0) search(num * 10);
        search(num * 10 + 1);
        search(num * 10 + 6);
        search(num * 10 + 8);
        search(num * 10 + 9);
    }

    private long rotate(long x) {
        long y = 0;
        for (; x != 0; x /= 10) {
            int k = (int) (x % 10);
            switch (k) {
                case 6:
                    k = 9;
                    break;
                case 9:
                    k = 6;
                    break;
            }
            y = y * 10 + k;
        }
        return y;
    }

    public static void main(String[] args) {
        LC1088ConfusingNumberII confusingNumberII = new LC1088ConfusingNumberII();
        System.out.println(confusingNumberII.confusingNumberII(100));
    }
}
