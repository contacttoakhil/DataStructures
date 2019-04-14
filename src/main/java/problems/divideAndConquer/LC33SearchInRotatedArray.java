package main.java.problems.divideAndConquer;

public class LC33SearchInRotatedArray {

    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int start, int finish, int target) {
        if(start > finish) return -1;
        int mid = start + (finish-start)/2;
        if(nums[mid] == target) return mid;
        else if(nums[start] <= nums[mid]) {      // first half is in sorted order.
            if(target >= nums[start] && target < nums[mid])  // data is element to search
                return binarySearch(nums, start,mid-1, target);
            else
                return binarySearch(nums,mid+1, finish, target);
        }
        else {                                     // secondary half is in sorted order
            if(target > nums[mid]  && target <= nums[finish])
                return binarySearch(nums, mid+1,finish, target);
            else
                return binarySearch(nums, start,mid-1, target);
        }
    }

    public static void main(String[] args) {
        LC33SearchInRotatedArray searchInRotatedArray = new LC33SearchInRotatedArray();
        System.out.println(searchInRotatedArray.search(new int[] {4,5,6,7,0,1,2}, 0));
        System.out.println(searchInRotatedArray.search(new int[] {3,1}, 1));  // 1
    }
}
