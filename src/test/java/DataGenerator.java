package test.java;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DataGenerator {

    private static final Random RANDOM = new Random();

    /**
     * Generate and return an integer array of specified size of elements (may have duplicate)
     * @param size
     * @return
     */
    public static Integer[] generateIntegers(int size, int range)
    {
        return RANDOM.ints(size, 0, range).boxed().toArray(Integer[]::new);
    }

    /**
     * Generate and return an integer array of specified size of non-duplicate elements
     * @param size
     * @return
     */
    public static Integer[] generateIntegersWithoutDuplicates(int size, int range)
    {
        Set<Integer> integerSet = new HashSet<>(size);
        while(integerSet.size()<size) {
            while(integerSet.add(RANDOM.nextInt(range)) != true);
        }
        return integerSet.stream().toArray(Integer[]::new);
    }

    /**
     * Generate and return an integer array of specified size of non-duplicate elements
     * @param size
     * @return
     */
    public static Integer[] generateSortedIntegersWithoutDuplicates(int size, int range)
    {
        Set<Integer> integerSet = new HashSet<>(size);
        while(integerSet.size()<size) {
            while(integerSet.add(RANDOM.nextInt(range)) != true);
        }
        return integerSet.stream().sorted().toArray(Integer[]::new);
    }

    /**
     * Generates edged based on {@code vertexCount}
     * @param vertexCount number of vertices, if its 3 then vertices are [0,1,2]
     * @return the edges for the specified {@code vertexCount}
     */
    public static int[][] generateEdgesForGraph(int vertexCount) {
        int maxEdgesAllowed = vertexCount * (vertexCount - 1) + vertexCount;
        int edgeCount = RANDOM.nextInt(maxEdgesAllowed - 1) + 1; // random but never zero
        int[][] edges = new int[edgeCount][2];
        int i,j,count=0;
        while (count < edgeCount) {
            i = RANDOM.nextInt(vertexCount);
            j = RANDOM.nextInt(vertexCount);
            if(i != j) {
                edges[count][0] = i;
                edges[count][1] = j;
                count++;
            }
        }
        return edges;
    }

    /**
     * Generates random alphabetic strings of length {@code length}
     * @param count - number of strings needed
     * @param length - length of each string
     * @return array of random strings
     */
    public static  String[] generateAlphabeticStrings(int count, int length) {
        return generateRandomStrings(count, length, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    /**
     * Generates random alpha-numeric strings of length {@code length}
     * @param count - number of strings needed
     * @param length - length of each string
     * @return array of random strings
     */
    public static  String[] generateAlphaNumericStrings(int count, int length) {
        return generateRandomStrings(count, length, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    private static  String[] generateRandomStrings(int count, int length, String seedData) {
        String[] strings = new String[count];
        StringBuilder sb = new StringBuilder(length);
        for(int i=0; i< count; i++) {
            sb.setLength(0);
            for (int j = 0; j < length; j++) {
                sb.append(seedData.charAt(RANDOM.nextInt(seedData.length())));
            }
            strings[i] = sb.toString();
        }
        return strings;
    }

}
