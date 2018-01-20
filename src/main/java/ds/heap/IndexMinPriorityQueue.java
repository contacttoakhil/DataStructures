package main.java.ds.heap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPriorityQueue<K extends Comparable<K>> implements Iterable<Integer>{
    private int capacity;        // maximum number of elements on PQ
    private int size;           // number of elements on PQ
    private int[] indices;        // binary heap using 1-based indexing
    private int[] revIndices;        // inverse of indices - revIndices[indices[i]] = indices[revIndices[i]] = i
    private K[] keys;      // keys[i] = priority of i

    /**
     * Initialize a index min priority queue with default capacity of 2
     */
    public IndexMinPriorityQueue()
    {
        this(2);
    }

    /**
     * Initialize a index min priority queue with max capacity {@code capacity}
     * @param capacity the keys on this priority queue use index from {@code 0} to {@code capacity - 1}
     * @throws IllegalArgumentException if {@code capacity} not valid
     */
    public IndexMinPriorityQueue(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
        this.size = 0;
        this.keys = (K[]) new Comparable[capacity + 1];
        this.indices = new int[capacity + 1];
        this.revIndices = new int[capacity + 1];
        Arrays.fill(revIndices, -1);
    }

    /**
     * Inserts {@code key} into queue and associates it with index {@code i}
     * @param i is the index
     * @param key is the key to be inserted
     * @throws IllegalArgumentException if is invalid or an item already exists at index {@code i}
     */
    public void insert(int i, K key) {
        ensureIndexNotInUse(i);
        size = size + 1;
        revIndices[i] = size;
        indices[size] = i;
        keys[i] = key;
        swim(size);
    }

    /**
     * Removes a minimum key and returns its associated index.
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int delMin() {
        int minIndex = minIndex();
        swap(1, size--);
        sink(1);
        assert minIndex == indices[size +1];
        revIndices[minIndex] = -1;
        keys[minIndex] = null;
        indices[size +1] = -1;
        return minIndex;
    }

    /**
     * Gets the index of the smallest (minimum) key in this index-min priority queue.
     *
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int minIndex() {
        ensureQueueIsNotEmpty();
        return indices[1];
    }

    /***
     * Decrease the key for index {@code i} to the provided value {@code key}
     * @param i the index of the key to decrease
     * @param key the decreased key value for index {@code i}
     * @throws IllegalArgumentException if index is invalid or if {@code key} is greater than existing value for the key at index {@code i}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void decreaseKey(int i, K key) {
        ensureIndexIsInUse(i);
        if (keys[i].compareTo(key) <= 0)
            throw new IllegalArgumentException("For index i: " + i + " old value:" + keys[i] + " and new value: " + key + " so decrease key operation not valid.");
        keys[i] = key;
        swim(revIndices[i]);
    }

    /***
     * Increase the key for index {@code i} to the provided value {@code key}
     * @param i the index of the key to increase
     * @param key the increased key value for index {@code i}
     * @throws IllegalArgumentException if index is invalid or if {@code key} is lesser than existing value for the key at index {@code i}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void increaseKey(int i, K key) {
        ensureIndexIsInUse(i);
        if (keys[i].compareTo(key) >= 0)
            throw new IllegalArgumentException("For index i: " + i + " old value:" + keys[i] + " and new value: " + key + " so increase key operation not valid.");
        keys[i] = key;
        sink(revIndices[i]);
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Is {@code i} an index on this priority queue?
     *
     * @param  i an index
     * @return {@code true} if {@code i} is an index on this priority queue;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= i < capacity}
     */
    public boolean contains(int i) {
        validateIndex(i);
        return revIndices[i] != -1;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns a minimum key.
     *
     * @return a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public K minKey() {
        ensureQueueIsNotEmpty();
        return keys[indices[1]];
    }

    /**
     * Gets key for the index {@code i}
     * @param i the index whose key needs to be fetched
     * @return the key for index {@code i}
     */
    public K getKey(int i) {
        ensureIndexIsInUse(i);
        return keys[i];
    }

    public void print() {
        System.out.println("Index Min Priority Queue:");
        for (Integer i: this) {
            System.out.println(i + " " + keys[i]);
        }
    }

    /***************************************************************************
     * private helper methods
     ***************************************************************************/
    private void ensureIndexNotInUse(int i) {
        if(contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
    }
    private void ensureIndexIsInUse(int i) {
        if(!contains(i)) throw new IllegalArgumentException("Item does not exist at index:" + i);
    }
    private void validateIndex(int i) {
        if( i<0 || i>capacity ) throw new IllegalArgumentException("Index:" + i + " is not valid for array size:" + keys.length);
    }
    private void validateCapacity(int capacity) {
        if(capacity<0 || capacity>Integer.MAX_VALUE)
            throw new IllegalArgumentException("Capacity is not valid");
    }
    private void ensureQueueIsNotEmpty() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
    }
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            swap(k, k/2);
            k = k/2;
        }
    }
    private void sink(int k) {
        while (2*k <= size) {
            int j = 2*k;
            if (j < size && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }
    private boolean greater(int i, int j) {
        return keys[indices[i]].compareTo(keys[indices[j]]) > 0;
    }

    private void swap(int i, int j) {
        int temp = indices[i];
        indices[i] = indices[j];
        indices[j] = temp;
        revIndices[indices[i]] = i;
        revIndices[indices[j]] = j;
    }

    /***************************************************************************
     * Iterators.
     ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Integer> iterator() { return new PriorityQueueIterator(); }

    private class PriorityQueueIterator implements Iterator<Integer> {
        private IndexMinPriorityQueue<K> copy;

        public PriorityQueueIterator() {
            copy = new IndexMinPriorityQueue<K>(indices.length - 1);
            for (int i = 1; i <= size; i++)
                copy.insert(indices[i], keys[indices[i]]);
        }
        public boolean hasNext()  { return !copy.isEmpty();                     }
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
}
