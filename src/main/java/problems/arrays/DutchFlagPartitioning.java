package main.java.problems.arrays;

import java.util.Arrays;

public class DutchFlagPartitioning {

    private int[] input;

    public DutchFlagPartitioning(int[] input) {
        this.input = input;
    }

    public static void main(String[] args) {
        DutchFlagPartitioning dfp = new DutchFlagPartitioning(new int[] {4, 9, 4, 5, 1, 9, 4, 4, 9, 4, 4, 1, 4, 12, 1, 2});
        dfp.dutchFlagParition(4);
        dfp.printResult();
    }

    /**
     * A[0 : smaller-1] are all smaller than pivotElement
     * A[smaller : equal-1] are equal to pivotElement
     * A[equal : larger-1] are larger than pivotElement
     * @param pivotElement
     */
    void dutchFlagParition(int pivotElement) {
        int smaller = 0, equal = 0, larger = input.length;
        while(equal < larger) {
            if(input[equal] < pivotElement)
                swap(smaller++, equal);
            else if (input[equal] > pivotElement)
                swap(equal, --larger);
            else if (input[equal] == pivotElement)
                equal++;
        }
    }

    void swap(int indexOne, int indexTwo) {
        int temp = input[indexOne];
        input[indexOne] = input[indexTwo];
        input[indexTwo] = temp;
    }

    void printResult() { Arrays.toString(input); }

}

// Time: O(n) Space: O(1)

// variations
// Sort an array of 0s, 1s and 2s using dutch flag partition scheme.
// Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.