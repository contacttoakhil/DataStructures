package main.java.ds.queue;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A queue is a particular kind of abstract data type or collection in which the entities in the collection are kept in order and the principal (or only) operations on the collection are the addition of entities to the rear terminal position and removal
 * of entities from the front terminal position. This makes the queue a First-In-First-Out (FIFO) data structure. In a FIFO data structure, the first element added to the queue will be the first one to be removed. This is equivalent to the requirement
 * that once a new element is added, all elements that were added before have to be removed before the new element can be removed.
 *
 * http://en.wikipedia.org/wiki/Queue_(abstract_data_type)
 *
 */
public interface IQueue<E> extends Iterable<E>{

    /**
     * Inserts the specified element into this queue.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else {@code false}
     * @throws NullPointerException if the specified element is null and this queue does not permit null elements
     */
    boolean offer(E e);

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E poll();

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E peek();

    /**
     * Clear the entire queue.
     */
    void clear();

    /**
     * Does the queue contain the value.
     *
     * @param e to find in the queue.
     * @return {@code true} if the queue contains the value.
     */
    boolean contains(E e);

    /**
     * Get the size of the queue.
     *
     * @return size of the queue.
     */
    int size();

    /**
     * Validate the queue according to the invariants.
     *
     * @return {@code true} if the queue is valid.
     */
    boolean validate();

    /**
     * Tests if this queue is empty.
     *
     * @return  {@code true} if and only if this queue contains no elements; {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Prints the comma separated list of elements onto this stack.
     */
    default void print()
    {
        StringJoiner joiner = new StringJoiner(",");
        for(E e : this) {
            joiner.add(Objects.toString(e));
        }
        System.out.println("Queue content:" + joiner);
    }
}
