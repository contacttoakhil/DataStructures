package main.java.problems.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LC215KthLargestElementInArray {

    public int findKthLargestUsingSorting(Integer[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    public int findKthLargestUsingPriorityQueue(Integer[] nums, int k) {
        int p = 0;
        int numElements = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();           // Use min heap
        for (int n : nums) {
            pq.add(n);
        }
        while (numElements - k + 1 > 0) {
            p = pq.poll();
            k++;
        }
        return p;
    }

    public int findKthLargestUsingQuickSort(Integer[] nums, int k) {
        return findKthLargestUsingQuickSortHelper(nums, 0, nums.length-1, k-1);
    }

    public int findKthLargestUsingMedian(Integer[] array, int k) {
        // Step 1: Divide the list into n/5 lists of 5 element each.
        int noOfRequiredLists = (int) Math.ceil(array.length / 5.0);
        // Step 2: Find pivotal element aka median of medians.
        int medianOfMedian =  findMedianOfMedians(array, noOfRequiredLists);
        //Now we need two lists split using medianOfMedian as pivot. All elements in list listOne will be grater than medianOfMedian and listTwo will have elements lesser than medianOfMedian.
        List<Integer> listWithGreaterNumbers = new ArrayList<>(); // elements greater than medianOfMedian
        List<Integer> listWithSmallerNumbers = new ArrayList<>(); // elements less than medianOfMedian
        for (Integer element : array) {
            if (element < medianOfMedian) {
                listWithSmallerNumbers.add(element);
            } else if (element > medianOfMedian) {
                listWithGreaterNumbers.add(element);
            }
        }
        // Next step.
        if (k <= listWithGreaterNumbers.size()) return findKthLargestUsingMedian((Integer[]) listWithGreaterNumbers.toArray(new Integer[listWithGreaterNumbers.size()]), k);
        else if ((k - 1) == listWithGreaterNumbers.size()) return medianOfMedian;
        else if (k > (listWithGreaterNumbers.size() + 1)) return findKthLargestUsingMedian((Integer[]) listWithSmallerNumbers.toArray(new Integer[listWithSmallerNumbers.size()]), k-listWithGreaterNumbers.size()-1);
        return -1;
    }

    private int findKthLargestUsingQuickSortHelper(Integer[] nums, int l, int h, int k) {
        if(k<l || k>h) return -1;
        int p = partition(nums, l, h);
        if(p == k)
            return nums[p];
        else if(p > k)
            return findKthLargestUsingQuickSortHelper(nums, l, p-1, k);
        else
            return findKthLargestUsingQuickSortHelper(nums, p+1, h, k);
    }

    private int partition(Integer[] nums, int l, int h) {
         int pivot = nums[h];
        int left = l;
        for(int i=l; i<h; i++) {
            if(nums[i]>pivot) {
                swap(nums, i, left++);
            }
        }
        swap(nums, left, h);
        return left;
    }

    private void swap(Integer[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int findMedianOfMedians(Integer[] mainList, int noOfRequiredLists) {
        int[] medians = new int[noOfRequiredLists];
        for (int count = 0; count < noOfRequiredLists; count++) {
            int startOfPartialArray = 5 * count;
            int endOfPartialArray = startOfPartialArray + 5;
            Integer[] partialArray = Arrays.copyOfRange((Integer[]) mainList, startOfPartialArray, endOfPartialArray);
            // Step 2: Find median of each of these sublists.
            int medianIndex = partialArray.length/2;
            medians[count] = partialArray[medianIndex];
        }
        // Step 3: Find median of the medians.
        return medians[medians.length / 2];
    }


    public static void main(String[] args) {
        LC215KthLargestElementInArray kthLargestElementInArray = new LC215KthLargestElementInArray();
        Integer[] numbers = new Integer[]{2, 3, 5, 4, 1, 12, 11, 13, 16, 7, 8, 6, 10, 9, 17, 15, 19, 20, 18, 23, 21, 22, 25, 24, 14};
        System.out.println(kthLargestElementInArray.findKthLargestUsingMedian(numbers, 8));
        System.out.println(kthLargestElementInArray.findKthLargestUsingPriorityQueue(numbers, 8));
        System.out.println(kthLargestElementInArray.findKthLargestUsingQuickSort(numbers, 8));
        System.out.println(kthLargestElementInArray.findKthLargestUsingSorting(numbers, 8));
    }
}
