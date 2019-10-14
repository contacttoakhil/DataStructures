package main.java.problems.others;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/***
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *
 * Note:
 * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 *
 */
public class LC480SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        TreeSet<Integer> left = getSet(nums);
        TreeSet<Integer> right = getSet(nums);
        for(int i = 0; i < nums.length; i++) {
            addNumber(i, left, right);
            if(left.size() + right.size() == k) {
                double med;
                if(left.size() == right.size())
                    med = ((double)nums[left.last()] + nums[right.first()]) / 2;
                else if(left.size() < right.size())
                    med = nums[right.first()];
                else
                    med = nums[left.last()];

                int start = i - k + 1;
                result[start] = med;

                if(!left.remove(start))
                    right.remove(start);
            }
        }
        return result;
    }

    private TreeSet<Integer> getSet(int[] nums) {
        return new TreeSet<>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                return nums[a] == nums[b] ? a - b : nums[a] < nums[b] ? -1 : 1;
            }
        });
    }

    private void addNumber(int num, TreeSet<Integer> left, TreeSet<Integer> right) {
        if(left.size() <= right.size()) {
            right.add(num);
            int m = right.first();
            right.remove(m);
            left.add(m);
        } else {
            left.add(num);
            int m = left.last();
            left.remove(m);
            right.add(m);
        }
    }
    public static void main(String[] args) {
        LC480SlidingWindowMedian slidingWindowMedian = new LC480SlidingWindowMedian();
        double[] result = slidingWindowMedian.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3);
        System.out.println(Arrays.toString(result));
    }
}

/**
 * Using TreeSet is better than using PriorityQueue as remove() takes O(N) for priority queue whereas it is O(logN) for TreeSet.
 */
class MedianQueue {
    private int[] nums;
    private Comparator<Integer> comparator = null;
    private TreeSet<Integer> left = null;
    private TreeSet<Integer> right = null;

    MedianQueue(int[] nums) {
        this.nums = nums;
        comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        left = new TreeSet<>(comparator.reversed());
        right = new TreeSet<>(comparator);
    }

    public void add(int n) {
        if(left.size() <= right.size()) {
            right.add(n);
            int m = right.first();
            right.remove(m);
            left.add(m);
        } else {
            left.add(n);
            int m = left.last();
            left.remove(m);
            right.add(m);
        }
    }

    public int totalSize() {
        return left.size() + right.size();
    }

    /*public double median() {
        double med;
        if(left.size() == right.size())
            med = ((double) nums[left.last()] + nums[right.first()]) / 2;
        else if(left.size() < right.size())
            med = nums[right.first()];
        else
            med = nums[left.last()];

        int start = i - k + 1;
        result[start] = med;

        if(!left.remove(start))
            right.remove(start);
    }*/
    /*public double median() {
        while (maxHeap.size() - minHeap.size() >= 2) minHeap.offer(maxHeap.poll());
        while (minHeap.size() - maxHeap.size() >= 1) maxHeap.offer(minHeap.poll());
        return maxHeap.size() == minHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2.0 : maxHeap.peek();
    }

    public boolean remove(long n) {
        return maxHeap.remove(n) || minHeap.remove(n);
    }*/

}
