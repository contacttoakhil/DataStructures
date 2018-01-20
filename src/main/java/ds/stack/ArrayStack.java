package main.java.ds.stack;

import java.util.*;

public class ArrayStack<E> implements IStack<E>
{
    private static final int MIN_CAPACITY = 20;
    private E[] array;
    private int size = 0;

    public ArrayStack() {
        this.array = (E[]) new Object[MIN_CAPACITY];
    }

    @Override
    public boolean push(E e) {
        resizeIfNeeded();
        array[size++] = e;
        return true;
    }

    @Override
    public E pop() {
        E e = peek();
        array[size] = null;
        resizeIfNeeded();
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return array[--size];
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(E e) {
        return Arrays.stream(array).filter(Objects::nonNull).anyMatch(i->i.equals(e));
    }

    @Override
    public boolean validate() {
        int count = 0;
        for (int i=0; i<array.length; i++) {
            E e = array[i];
            if (i<size) {
                if (e==null) return false;
                count++;
            } else {
                if (e!=null) return false;
            }
        }
        return (count==size);
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeIfNeeded() {
        if(size == array.length) { // Need to expand storage?
            array = Arrays.copyOf(array, Math.multiplyExact(size,2)); // if current-size is n make it 2n. Method will throw ArithmeticException in case of overflow.
        }
        else if(size >= MIN_CAPACITY && size <= (array.length/2)) { // need to compress?
            Object[] cleanedArray = Arrays.stream(array).filter(Objects::nonNull).toArray();
            array = (E[]) cleanedArray;
        }
        return;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayStackIterator<>(this);
    }

    private class ArrayStackIterator<E> implements Iterator<E> {
        private ArrayStack<E> stack = null;
        private int index = 0;

        ArrayStackIterator(ArrayStack<E> arrayStack) {
            this.stack = arrayStack;
        }

        @Override
        public boolean hasNext() {
            return (index+1 <= stack.size);
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return stack.array[index++];
        }
    }
}
