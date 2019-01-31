package main.java.problems.arrays;

import java.util.HashMap;
import java.util.Map;

public class FindFourElementsToGivenSum {

    public static void main(String[] args) {
        int[] array = new int[] {1,5,1,0,6,0};
        findFourElements(array, 7);
    }

    private static void findFourElements(int[] array, int targetSum) {
        Map<Integer,String> sumsMap = new HashMap<>();
        for(int i=0; i<array.length; i++) {
            for(int j=i+1; j<array.length; j++) {
                int current = array[i] + array[j];
                int rest = targetSum - current;
                if(sumsMap.containsKey(rest)) {
                    System.out.println(" elements:" + array[i] +"," + array[j] + "," + sumsMap.get(rest));
                }
            }
            for(int k=0; k<i; k++) { //let's put in 'sums' hashtable all possible sums a[i] + a[k] where k < i
                sumsMap.put(array[k] + array[i], array[k] + "," + array[i]);
            }
        }
    }
}
