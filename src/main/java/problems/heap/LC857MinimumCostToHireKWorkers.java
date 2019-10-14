package main.java.problems.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/***
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 *
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
 *
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 *
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 *
 *
 * Note:
 *
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 *
 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
 *
 */
public class LC857MinimumCostToHireKWorkers {

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] workers = initializeWorkers(quality, wage);
        double ans = Double.POSITIVE_INFINITY;
        int sumOfQuality = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder()); // max-heap
        for (Worker worker: workers) {
            maxHeap.offer(worker.quality);
            sumOfQuality += worker.quality;
            if (maxHeap.size() > K)
                sumOfQuality = sumOfQuality - maxHeap.poll();
            // When we have removed top (N-K) quality items then pool size would be K, then:
            // a) compute wage for these quality workers using wage-to-quality ratio of current worker
            // b) and take minimum of this considering wage-to-quality for all workers.
            if (maxHeap.size() == K)               //
                ans = Math.min(ans, sumOfQuality * worker.wageToQualityRatio());
        }
        return ans;
    }

    /*public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] workers = initializeWorkers(quality, wage);
        double ans = Double.POSITIVE_INFINITY;
        int sumOfQuality = 0;
        PriorityQueue<Integer> pool = new PriorityQueue();
        for (Worker worker: workers) {
            pool.offer(-worker.quality);        // more the quality higher on min-heap post negation
            sumOfQuality += worker.quality;
            if (pool.size() > K)
                sumOfQuality += pool.poll();
            // When we have removed top (N-K) quality items then pool size would be K, then:
            // a) compute wage for these quality workers using wage-to-quality ratio of current worker
            // b) and take minimum of this considering wage-to-quality for all workers.
            if (pool.size() == K)               //
                ans = Math.min(ans, sumOfQuality * worker.wageToQualityRatio());
        }
        return ans;
    }*/
    private Worker[] initializeWorkers(int[] quality, int[] wage) {
        int count = quality.length;
        Worker[] workers = new Worker[count];
        for (int i = 0; i < count; ++i)
            workers[i] = new Worker(quality[i], wage[i]);
        Arrays.sort(workers);       // Now lower the idx higher the wage-to-quality ratio.
        return workers;
    }
    public static void main(String[] args) {
        LC857MinimumCostToHireKWorkers minimumCostToHireKWorkers = new LC857MinimumCostToHireKWorkers();
        System.out.println(minimumCostToHireKWorkers.mincostToHireWorkers(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2)); //105
        System.out.println(minimumCostToHireKWorkers.mincostToHireWorkers(new int[]{3, 1, 10, 10, 1}, new int[]{4, 8, 2, 2, 7}, 3)); // 30.666666666666664
    }
}

class Worker implements Comparable<Worker> {
    public int quality, wage;

    public Worker(int quality, int wage) {
        this.quality = quality;
        this.wage = wage;
    }

    // Higher ratio means worker is providing better quality at cheaper rate.
    public double wageToQualityRatio() {
        return (double) wage/quality;
    }

    @Override
    public int compareTo(Worker o) {
        return Double.compare(this.wageToQualityRatio(), o.wageToQualityRatio());
    }
}