package main.java.problems.arrays;

public class LC04MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if(totalLength %2 != 0)
            return findKth(nums1, nums2, 0, 0, totalLength/2+1);
        else {
            double mid = findKth(nums1, nums2, 0, 0, totalLength/2);
            double midNext = findKth(nums1, nums2, 0, 0, totalLength/2+1);
            return (mid + midNext)/2.0;
        }
    }

    private double findKth(int[] a, int[] b, int startA, int startB, int k) {
        if (startA >= a.length) return b[startB + k - 1];
        if (startB >= b.length) return a[startA + k - 1];
        if (k == 1) return Math.min(a[startA], b[startB]);

        int indexA = startA + k / 2 - 1;
        int indexB = startB + k / 2 - 1;
        int keyA = indexA < a.length ? a[indexA] : Integer.MAX_VALUE;   // value of element at indexA in array a
        int keyB = indexB < b.length ? b[indexB] : Integer.MAX_VALUE;   // value of element at indexB in array b
        // Here keyA < keyB means item at indexA in array a is smaller than item at indexB in array b, so we need to move the start index to indexA+1 as item at indexA is already smaller then keyB.
        if (keyA < keyB)
            return findKth(a, b, indexA + 1, startB, k - k / 2);
        else
            return findKth(a, b, startA, indexB + 1, k - k / 2);
    }

    public static void main(String[] args) {
        LC04MedianOfTwoSortedArrays medianofTwoSortedArrays = new LC04MedianOfTwoSortedArrays();
        System.out.println(medianofTwoSortedArrays.findMedianSortedArrays(new int[]{1,3} , new int[] {2}));    // 2.0
        System.out.println(medianofTwoSortedArrays.findMedianSortedArrays(new int[]{1,2} , new int[] {3,4}));  // 2.5
    }
}
