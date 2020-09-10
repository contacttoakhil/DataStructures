package main.java.problems.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC480SlidingWindowMedian {
    private Queue<Integer> minHeap = new PriorityQueue<>();                 // store larger half
    private Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // smaller
    private double[] result = null;

    public double[] medianSlidingWindow(int[] nums, int k) {
        int j = 0;
        double[] result = new double[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
            maxHeap.add(minHeap.remove());
            // should have equal else minHeap should have one more
            if(maxHeap.size() > minHeap.size())
                minHeap.add(maxHeap.remove());
            if(minHeap.size() + maxHeap.size() == k) {
                result[j] = getMedian();
                if (!minHeap.remove(nums[j])) maxHeap.remove(nums[j]); // slide window
                j++;
            }
        }
        return result;
    }
    public double getMedian() {
        boolean numIsEven = minHeap.size() == maxHeap.size();
        return numIsEven ?(maxHeap.peek()+minHeap.peek())*0.5
                :(double)minHeap.peek();
    }

    public static void main(String[] args) {
        LC480SlidingWindowMedian slidingWindowMedian = new LC480SlidingWindowMedian();
        //double[] result = slidingWindowMedian.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        double[] result = slidingWindowMedian.medianSlidingWindow(new int[]{1,3,5,7}, 3);
        System.out.println(Arrays.toString(result));
    }
}
