package test.java.heap;

import main.java.ds.heap.IndexMinPriorityQueue;
import main.java.ds.heap.MinPriorityQueue;
import org.junit.Test;
import test.java.DataGenerator;

import static junit.framework.TestCase.assertTrue;

public class TestHeap {

    @Test
    public void testMinPriorityQueue()
    {
        testHeap(new MinPriorityQueue(), DataGenerator.generateAlphabeticStrings(20, 10));
    }

    @Test
    public void testMinPriorityQueueBasic()
    {
        MinPriorityQueue<String> h = new MinPriorityQueue<>();
        h.insert("p");
        h.insert("r");
        h.insert("i");
        h.insert("o");
        System.out.println("Heap content:" + h);
        System.out.println("Deleted: " + h.deleteMin());
        System.out.println("Deleted: " + h.deleteMin());
        System.out.println("Deleted: " + h.deleteMin());
        System.out.println(h);
    }

    @Test
    public void testIndexMinPriorityQueueBasic()
    {
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };
        IndexMinPriorityQueue<String> pq = new IndexMinPriorityQueue<>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }
        pq.print();
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            System.out.println("Deleted: index:" + i + " item:" + strings[i]);
        }
        System.out.println();
    }

    private static <T extends Comparable<T>> void testHeap(MinPriorityQueue priorityQueue, T[] elements)
    {
        assertTrue(ensureInsert(priorityQueue, elements));
        System.out.println("Heap content:" + priorityQueue);
        assertTrue(ensureDelete(priorityQueue, elements));
        System.out.println("Heap content:" + priorityQueue);
    }

    private static <T extends Comparable<T>> boolean ensureInsert(MinPriorityQueue priorityQueue, T[] elements) {
        for (T element : elements) {
            priorityQueue.insert(element);
        }
        return true;
    }

    private static <T extends Comparable<T>> boolean ensureDelete(MinPriorityQueue priorityQueue, T[] elements) {
        while (!priorityQueue.isEmpty()) {
            T deletedElement = (T) priorityQueue.deleteMin();
            if(priorityQueue.contains(deletedElement)) {
                System.err.println("Element " + deletedElement + " still present post delete operation.");
                return false;
            }
        }
        return true;
    }
}
