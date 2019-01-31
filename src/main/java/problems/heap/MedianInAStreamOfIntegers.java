package main.java.problems.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianInAStreamOfIntegers {
    public Queue<Integer> minHeap;
    public Queue<Integer> maxHeap;
    public int numOfElements;

    public MedianInAStreamOfIntegers() {
        minHeap = new PriorityQueue<Integer>(10, (o1, o2) -> o1-o2); // by default it is min-heap
        maxHeap = new PriorityQueue<Integer>(10, (o1,o2) -> o2-o1);
        numOfElements = 0;
    }

    public void addNumberToStream(Integer element) {
        boolean beforeInsertionEven = (numOfElements%2 == 0);
        maxHeap.add(element);
        numOfElements++;
        if(beforeInsertionEven) {
            if (minHeap.isEmpty()) return;
            if(maxHeap.peek() > minHeap.peek()) {   // newly inserted element should be less than all element in min-heap.
                Integer maxHeapRoot = maxHeap.poll();
                Integer minHeapRoot = minHeap.poll();
                maxHeap.add(minHeapRoot);
                minHeap.add(maxHeapRoot);
            }
        }
        else
        {
            minHeap.add(maxHeap.poll());    // max-heap has N+2 elements so pop its root and push to min-heap.
        }
    }

    public Double getMedian() {
        if (numOfElements%2 != 0)
            return new Double(maxHeap.peek());
        else
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianInAStreamOfIntegers streamMedian = new MedianInAStreamOfIntegers();

        streamMedian.addNumberToStream(1);
        System.out.println(streamMedian.getMedian()); // should be 1

        streamMedian.addNumberToStream(5);
        streamMedian.addNumberToStream(10);
        streamMedian.addNumberToStream(12);
        streamMedian.addNumberToStream(2);
        System.out.println(streamMedian.getMedian()); // should be 5

        streamMedian.addNumberToStream(3);
        streamMedian.addNumberToStream(8);
        streamMedian.addNumberToStream(9);
        System.out.println(streamMedian.getMedian()); // should be 6.5
    }

}
