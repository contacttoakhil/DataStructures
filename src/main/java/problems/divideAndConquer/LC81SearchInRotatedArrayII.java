package main.java.problems.divideAndConquer;

public class LC81SearchInRotatedArrayII {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) return true;

            // the only difference from the first one, trickly case, just updat left and right
            if( (nums[start] == nums[mid]) && (nums[end] == nums[mid]) ) {++start; --end;}
            else if (nums[start] <= nums[mid]) {
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
        return false;
    }

    public static void main(String[] args) {
        LC81SearchInRotatedArrayII searchInRotatedArrayII = new LC81SearchInRotatedArrayII();
        System.out.println(searchInRotatedArrayII.search(new int[]{2,5,6,0,0,1,2},0)); // true
        System.out.println(searchInRotatedArrayII.search(new int[]{2,5,6,0,0,1,2},3)); // false
        System.out.println(searchInRotatedArrayII.search(new int[]{1,3,1,1,1},3)); // true

    }
}
