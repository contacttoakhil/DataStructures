package main.java.problems.binarySearch;

import java.util.Arrays;

/***
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 *
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
 *
 *
 *
 * Example 1:
 *
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation:
 * A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 */

public class LC1011CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int D) {
        int hi = Arrays.stream(weights).sum();
        int lo = Arrays.stream(weights).max().getAsInt();
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int days = getRequiredDays(weights, mid);
            if(days > D)  lo = mid + 1;
            else          hi = mid - 1;
        }
        return lo;
    }

    private int getRequiredDays(int[] weights, int maxCap) {
        int days = 1, cap = 0;
        for (int weight : weights) {
            cap = cap + weight;
            if(cap > maxCap) {
                days++;
                cap = weight;
            }
        }
        return days;
    }

    public static void main(String[] args) {
        LC1011CapacityToShipPackagesWithinDDays capacityToShipPackagesWithinDDays = new LC1011CapacityToShipPackagesWithinDDays();
        int days = capacityToShipPackagesWithinDDays.shipWithinDays(new int[] {1,2,3,4,5,6,7, 8,9,10}, 5);
        System.out.println(days);
    }
}
