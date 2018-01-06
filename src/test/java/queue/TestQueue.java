package test.java.queue;

import main.java.queue.ArrayQueue;
import main.java.queue.IQueue;
import main.java.queue.LinkedQueue;
import org.junit.Test;
import test.java.DataGenerator;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class TestQueue {

    @Test
    public void testArrayQueue()
    {
        Integer[] sampleData = DataGenerator.generateIntegersWithoutDuplicates(50, 1000);
        testQueue(new ArrayQueue<>(),sampleData);
    }

    @Test
    public void testLinkedQueue()
    {
        Integer[] sampleData = DataGenerator.generateIntegersWithoutDuplicates(50, 1000);
        testQueue(new LinkedQueue<>(),sampleData);
    }

    private static <E extends Comparable<E>> void testQueue(IQueue<E> queue, E[] sampleData)
    {
        assertTrue(ensureOffer(queue, sampleData, 0, sampleData.length));
        queue.print();
        assertTrue(ensurePoll(queue, sampleData, 0, queue.size()));
        ensureOfferPoll(queue, sampleData);
    }

    private static <E extends Comparable<E>> boolean ensureOffer(IQueue<E> queue, E[] sampleData, int startRange, int endRange)
    {
        for(int i=startRange; i<endRange; i++) {
            E e = sampleData[i];
            Objects.requireNonNull(e);
            boolean added = queue.offer(e);
            if(!added || !queue.contains(e)) {
                System.err.println("Something went wrong as element " + e + " does not exist on queue.");
                return false;
            }
            if(!queue.validate()) {
                System.err.println("Size mismatch during offer");
                return false;
            }
        }
        return true;
    }

    private static <E extends Comparable<E>> boolean ensurePoll(IQueue<E> queue, E[] sampleData, int startRange, int endRange)
    {
        for(int i=startRange; i<endRange; i++)
        {
            E e = queue.poll();
            Objects.requireNonNull(e);
            E elementInSample = sampleData[i]; // FIFO
            if(!e.equals(elementInSample)) {
                System.err.println("Queue has: " + e + " data has: " + elementInSample + " and FIFO issues with queue.");
                return false;
            }
            if(!queue.validate()) {
                System.err.println("Size mismatch during pop");
                return false;
            }
            if(queue.contains(e))
            {
                System.err.println("Element " + e + " still present post pop operation.");
                return false;
            }
        }
        return true;
    }

    private static <E extends Comparable<E>> boolean ensurePollIndexFix(IQueue<E> queue, E[] sampleData, int startRange, int endRange)
    {
        int quarter = sampleData.length/4;
        int half = sampleData.length/2;
        int changeOver = half-quarter;
        for(int i=startRange; i<endRange; i++)
        {
            E e = queue.poll();
            Objects.requireNonNull(e);
            int idx = i;
            if (idx < changeOver) {
                idx = quarter+i;
            } else if (idx>=changeOver && idx<half) {
                idx = i-changeOver;
            }
            E elementInSample = sampleData[idx]; // FIFO
            if(!e.equals(elementInSample)) {
                System.err.println("Queue has: " + e + " data has: " + elementInSample + " and FIFO issues with queue.");
                return false;
            }
            if(!queue.validate()) {
                System.err.println("Size mismatch during pop");
                return false;
            }
            if(queue.contains(e))
            {
                System.err.println("Element " + e + " still present post pop operation.");
                return false;
            }
        }
        return true;
    }

    private static <E extends Comparable<E>> void ensureOfferPoll(IQueue<E> queue, E[] sampleData)
    {
        int half = sampleData.length/2; // initial queue is empty - queue:[xxxx]
        assertTrue(ensureOffer(queue, sampleData, 0, half)); // add half of sample elements - queue:[q1q2xx]
        assertTrue(ensurePoll(queue, sampleData, 0,half/2)); // remove quarter q1 of sample elements - queue:[q2xxx]
        assertTrue(ensureOffer(queue, sampleData, 0,half/2)); // add quarter q1 of sample elements again - queue:[q2q1xx]
        assertTrue(ensureOffer(queue, sampleData, half, sampleData.length)); // add half of sample elements -  [q2q1q3q4]
        assertTrue(ensurePollIndexFix(queue, sampleData, 0,sampleData.length)); // now remove all to have[xxxx].
    }
}
