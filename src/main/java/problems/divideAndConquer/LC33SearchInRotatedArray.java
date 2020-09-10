package main.java.problems.divideAndConquer;

public class LC33SearchInRotatedArray {

    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        if(start > end) return -1;
        int mid = start + (end-start)/2;
        if(nums[mid] == target) return mid;
        if(nums[start] <= nums[mid]) {      // first half is in sorted order.
            if(target >= nums[start] && target < nums[mid])  // data is element to search
                return binarySearch(nums, start,mid-1, target);
            else
                return binarySearch(nums,mid+1, end, target);
        }
        else {                                     // secondary half is in sorted order
            if(target > nums[mid]  && target <= nums[end])
                return binarySearch(nums, mid+1,end, target);
            else
                return binarySearch(nums, start,mid-1, target);
        }
    }

    public int search2(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) return mid;
            if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) //start-target-mid
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            else {
                if (nums[mid] < target && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        LC33SearchInRotatedArray searchInRotatedArray = new LC33SearchInRotatedArray();
        System.out.println(searchInRotatedArray.search(new int[] {4,5,6,7,0,1,2}, 0));  // 4
        System.out.println(searchInRotatedArray.search(new int[] {3,1}, 1));  // 1
        System.out.println(searchInRotatedArray.search2(new int[] {4,5,6,7,0,1,2}, 0)); // 4
        System.out.println(searchInRotatedArray.search2(new int[] {3,1}, 1));  // 1

    }
}
