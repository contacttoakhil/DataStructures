package main.java.problems.others;

import java.util.TreeSet;

public class LC683KEmptySlots {

    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> active = new TreeSet();
        int day = 0;
        for (int flower: flowers) {
            day++;
            active.add(flower);
            Integer lower = active.lower(flower);
            Integer higher = active.higher(flower);
            boolean lowerBloomed = lower != null && flower - lower - 1 == k;
            boolean higherBloomed = higher != null && higher - flower - 1 == k;
            if(lowerBloomed || higherBloomed)
                return day;
        }
        return -1;
    }

    public static void main(String[] args) {
        LC683KEmptySlots kEmptySlots = new LC683KEmptySlots();
        System.out.println(kEmptySlots.kEmptySlots(new int[]{1,3,2},1));
    }
}
