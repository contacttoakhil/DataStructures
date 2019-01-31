package main.java.problems.others;

import java.util.Arrays;

/**
 * This problem resembles the problem of finding all subsets of a set. The idea is also similar to include and to exclude the bit. Time: O(2^n)
 */
public class GenerateStringOfNBits {
    int[] array;

    public GenerateStringOfNBits(int numOfBits) {
        this.array = new int[numOfBits];

    }

    public void generate() {
        generateNBits(array.length);
    }

    private void generateNBits(int n) {
        if(n <= 0)
            System.out.println(Arrays.toString(array));
        else
        {
            array[n-1] = 0;
            generateNBits(n-1);
            array[n-1] = 1;
            generateNBits(n-1);
        }
    }

    public static void main(String[] args) {
        GenerateStringOfNBits stringOfNBits = new GenerateStringOfNBits(3);
        stringOfNBits.generate();
    }
}
