package main.java.problems.arrays;

/***
 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the day.
 *
 *
 *
 * Example 1:
 *
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 */
public class LC1052GrumpyBookstoreOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int base = 0, currentWindow = 0, bestWindow = 0;
        for (int i = 0; i < grumpy.length; i++) {
            if(grumpy[i] == 1)                   currentWindow = currentWindow + customers[i];
            else                                 base = base + customers[i];         // Sum the costumers of non grumpy minutes
            if(i>=X && grumpy[i-X]==1)           currentWindow = currentWindow - customers[i-X];
            bestWindow = Math.max(bestWindow, currentWindow);   // record the max sum of the sliding window of size X
        }
        return base + bestWindow;
    }

    public static void main(String[] args) {
        LC1052GrumpyBookstoreOwner grumpyBookstoreOwner = new LC1052GrumpyBookstoreOwner();
        System.out.println(grumpyBookstoreOwner.maxSatisfied(new int[] {1,0,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3));
    }
}
