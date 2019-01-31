package main.java.problems.arrays;

public class PairClosestToSum {

    static void closestPair(int[] array, int target) {
        int i = 0,j = 0; int diff = Integer.MAX_VALUE;
        int left = 0; int right = array.length -1;
        while (left < right) {
            int elementSum = array[left] + array[right];
            int currentDiff = Math.abs(target - elementSum);
            if(currentDiff < diff){
                i = left;   j=right;
                diff =  currentDiff;
            }
            if(elementSum < target)
                left++;
            else
                right--;
        }
        System.out.println("result: " + array[i] + "," + array[j]);
    }
    public static void main(String[] args) {
        int arr[] =  {10, 22, 28, 29, 30, 40}, x = 52;
        closestPair(arr, x);
    }
}
