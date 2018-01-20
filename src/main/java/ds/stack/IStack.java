package main.java.ds.stack;

import java.util.EmptyStackException;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * A stack is a last in, first out (LIFO) abstract data type with push and pop as major operations.The push operation adds a new item to the top of the stack, or
 * initializes the stack if it is empty. If the stack is full and does not contain enough space to accept the given item, the stack is then considered to be in an
 * overflow state. The pop operation removes an item from the top of the stack.
 *
 * http://en.wikipedia.org/wiki/Stack_(abstract_data_type)
 *
 */
public interface IStack<E> extends Iterable<E>{

    /**
     * Pushes an element on the stack.
     * @param e the element to push onto this stack.
     * @return true if element pushed successfully else false.
     */
    boolean push(E e);

    /***
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     * @return the object at the top of this stack.
     * @throws EmptyStackException  if this stack is empty.
     */
    E pop();

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     *
     * @return  the object at the top of this stack.
     * @throws  EmptyStackException  if this stack is empty.
     */
    E peek();

    /**
     * Resets the size of the stack to zero.
     */
    void clear();

    /**
     * Tests if this stack is empty.
     *
     * @return  {@code true} if and only if this stack contains no elements; {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Verifies whether stack contains this element, if yes returns true.
     * @param e the element to be searched onto this stack.
     * @return {@code true} if stack contains this element; {@code false} otherwise.
     */
    boolean contains(E e);

    /**
     * Validates the stack to ensure all elements are valid onto this stack.
     * @return
     */
    boolean validate();

    /**
     * Returns the number of elements on the stack.
     * @return the count of elements onto this stack.
     */
    int size();

    /**
     * Prints the comma separated list of elements onto this stack.
     */
    default void print(CharSequence delimiter)
    {
        StringJoiner joiner = new StringJoiner(delimiter);
        for(E e : this) {
            joiner.add(Objects.toString(e));
        }
        System.out.println(joiner);
    }
}
