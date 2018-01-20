package main.java.ds.heap;

import java.util.*;
import java.util.stream.Collectors;

public class MinPriorityQueue<K extends Comparable<K>>{
    private int size;
    private K[] keys;

    public MinPriorityQueue()
    {
        this(2);
    }

    public MinPriorityQueue(int capacity)
    {
        validateCapacity(capacity);
        this.size = 0;
        this.keys = (K[]) new Comparable[capacity + 1];
    }

    public void insert(K value)
    {
        resizeIfNeeded();
        keys[++size] = value;
        swim(size);
    }

    public K deleteMin() {
        K minimum = min();
        swap(1, size--);
        sink(1);
        keys[size+1] = null;
        return minimum;
    }

    public K min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return keys[1];
    }

    public int size() {
        return size;
    }

    public boolean contains(K value) {
        return Arrays.stream(keys).filter(Objects::nonNull).anyMatch(i->i.equals(value));
    }

    @Override
    public String toString() {
        return Arrays.stream(this.keys).filter(Objects::nonNull).map(Objects::toString).collect(Collectors.joining(","));
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    private void resizeIfNeeded() {
        if(size == keys.length - 1) {
            K[] old = keys;
            keys = (K[]) new Comparable[keys.length * 2];
            System.arraycopy(old, 1, keys, 1, size);
        }
    }

    private void validateCapacity(int capacity) {
        if(capacity<0 || capacity>Integer.MAX_VALUE)
            throw new IllegalArgumentException("Capacity is not valid");
    }

    public boolean validate() {
        return isMinPriorityQueue(1);
    }

    private boolean isMinPriorityQueue(int k) {
        if (k > size) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= size && greater(k, left))  return false;
        if (right <= size && greater(k, right)) return false;
        return isMinPriorityQueue(left) && isMinPriorityQueue(right);
    }

    public Iterator<K> iterator() {
        return new PriorityQueueIterator<>(this);
    }

    static class PriorityQueueIterator<T extends Comparable<T>> implements Iterator<T> {
        private MinPriorityQueue<T> priorityQueue = null;

        PriorityQueueIterator(MinPriorityQueue<T> priorityQueue) {
            this.priorityQueue = priorityQueue;
        }

        @Override
        public boolean hasNext() {
            return !priorityQueue.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return priorityQueue.deleteMin();
        }
    }

    //----------------------------- Utility methods --------------------------------
    void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            swap(k, k/2);
            k = k/2;
        }
    }
    void sink(int k) {
        int size = size();
        while (2*k <= size) {
            int j = 2*k;
            if (j < size && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }
    boolean greater(int i, int j) {
        return keys[i].compareTo(keys[j]) > 0;
    }

    void swap(int i, int j) {
        K temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }
}
