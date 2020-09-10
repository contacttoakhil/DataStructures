package main.java.problems.others;

/***
 * Input: 00010111
 * Output: 1
 */
public class MinSwapToBringAllOnesTogether {

    private int minSWap(int[] array) {
        // Count 0s and say its ct.
        // Count 1s before ct as they need to be moved or swapped
        return count(array, count(array, array.length,0), 1);
    }

    private int count(int[] input, int end, int num) {
        int count = 0;
        for(int i=0; i<end; i++){
            if(input[i] == num)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        MinSwapToBringAllOnesTogether bringAllOnesTogether = new MinSwapToBringAllOnesTogether();
        System.out.println(bringAllOnesTogether.minSWap(new int[]{ 0, 0, 0, 1, 0, 1, 1, 1}));
    }
}
