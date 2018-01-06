package main.java.queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayQueue<E> implements IQueue<E> {
    private static final int MIN_CAPACITY = 20;
    private E[] array;
    private int rear = 0;
    private int front = 0;

    public ArrayQueue() {
        this.array = (E[]) new Object[MIN_CAPACITY];
    }

    @Override
    public boolean offer(E e) {
        Objects.requireNonNull(e);
        resizeIfNeeded();
        array[rear%array.length] = e;
        rear++;
        return true;
    }

    @Override
    public E poll() {
        E e = peek();
        array[front%array.length] = null;
        front++;
        if(isEmpty()) {
            front = rear = 0;
        }
        resizeIfNeeded();
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException("Underflow Exception");
        return array[front%array.length];
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(E e) {
        return Arrays.stream(array).filter(Objects::nonNull).anyMatch(i->i.equals(e));
    }

    @Override
    public int size() {
        return rear - front;
    }

    @Override
    public boolean validate() {
        if(isEmpty()) return true;
        int actualFront = (front > array.length) ? (front%array.length) : front;
        int actualRear = (rear > array.length) ? (rear%array.length) : rear;
        int count = 0;
        for(int i=0; i<array.length; i++) {
            E e = array[i];
            if((actualFront==actualRear) ||
                    (actualFront<actualRear && (i>=actualFront && i<actualRear)) ||
                    (actualRear<actualFront) && (i<actualRear || i>=actualFront))
            {
                if(e == null) return false;
                count++;
            }
            else
            {
                if(e!=null) return false;
            }
        }
        return (count == size());
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator<>(this);
    }

    private void resizeIfNeeded() {
        int currentSize = size();
        E[] temp = null;
        boolean needToCompress = size() >= MIN_CAPACITY && size() < (currentSize + currentSize<<1);
        boolean needToExpand = currentSize >= array.length;
        int adjLast = rear%array.length;
        if(needToExpand)
        {
            temp = (E[]) new Object[currentSize + (currentSize>>1)]; // arrays can wrap around so ensure to take the first section
            if (adjLast<=front) {
                System.arraycopy(array, 0, temp, array.length-adjLast, adjLast+1);
            }
            System.arraycopy(array, front, temp, 0, array.length-front);
        }
        else if(needToCompress)
        {
            temp = (E[]) new Object[currentSize + (currentSize<<1)];
            int endIndex = (rear>array.length)?array.length:rear;
            if (adjLast<=front) {
                System.arraycopy(array, 0, temp, array.length-front, adjLast);
            }
            System.arraycopy(array, front, temp, 0, endIndex-front);
        }
        if(needToExpand || needToCompress)
        {
            array = temp;
            rear = (rear - front);
            front = 0;
        }
    }

    private static class ArrayQueueIterator<E> implements Iterator<E> {
        private ArrayQueue<E> queue = null;
        private int index = 0;

        ArrayQueueIterator(ArrayQueue<E> queue) {
            this.queue = queue;
        }

        @Override
        public boolean hasNext() {
            return ( (queue.front+index) < (queue.rear) );
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return queue.array[queue.front + index++];
        }
    }
}
