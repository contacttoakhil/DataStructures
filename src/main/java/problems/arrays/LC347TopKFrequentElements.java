package main.java.problems.arrays;

import java.util.*;

/***
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Hint: Use bucket sort to get O(N) or use priority queue to get O(NlogK).
 */
public class LC347TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = countFrequencyOfEachElement(nums);
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && res.size() < k; i--) {
            if (bucket[i] != null) {
                res.addAll(bucket[i]);
            }
        }
        return res;
    }

    public List<Integer> topKFrequentUsingPQ(int[] nums, int k) {
        Map<Integer, Integer> map = countFrequencyOfEachElement(nums);
        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> map.get(n1) - map.get(n2));
        // keep k top frequent elements in the heap
        for (int n: map.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }
        List<Integer> top_k = new ArrayList<>();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    private Map<Integer, Integer> countFrequencyOfEachElement(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }
        return frequencyMap;
    }

    public static void main(String[] args) {
        LC347TopKFrequentElements topKFrequentElements = new LC347TopKFrequentElements();
        System.out.println(topKFrequentElements.topKFrequent(new int[]{1,1,1,2,2,3}, 2));
        System.out.println(topKFrequentElements.topKFrequentUsingPQ(new int[]{1,1,1,2,2,3}, 2));
    }
}
