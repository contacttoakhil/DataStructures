package main.java.problems.heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC295FindMedianDataStream {
    private Queue<Integer> minHeap = new PriorityQueue<>();                 // store larger half
    private Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // smaller
    private int numOfElements;

    public void addNumberFromStream(Integer element) {
        minHeap.add(element);
        maxHeap.add(minHeap.remove());
        // should have equal else minHeap should have one more
        if(maxHeap.size() > minHeap.size())
            minHeap.add(maxHeap.remove());
    }
    public double getMedian() {
        boolean numIsEven = minHeap.size() == maxHeap.size();
        return numIsEven ?(maxHeap.peek()+minHeap.peek())*0.5
                :(double)minHeap.peek();
    }

    public static void main(String[] args) {
        LC295FindMedianDataStream streamMedian = new LC295FindMedianDataStream();

        streamMedian.addNumberFromStream(1);
        System.out.println(streamMedian.getMedian()); // should be 1

        streamMedian.addNumberFromStream(5);
        streamMedian.addNumberFromStream(10);
        streamMedian.addNumberFromStream(12);
        streamMedian.addNumberFromStream(2);
        System.out.println(streamMedian.getMedian()); // should be 5

        streamMedian.addNumberFromStream(3);
        streamMedian.addNumberFromStream(8);
        streamMedian.addNumberFromStream(9);
        System.out.println(streamMedian.getMedian()); // should be 6.5
    }
}
