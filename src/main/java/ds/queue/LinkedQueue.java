package main.java.ds.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements IQueue<E>{
    private Node<E> front, rear;
    private int size;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    @Override
    public boolean offer(E e) {
        Node<E> node = new Node<>(e,null);
        if(rear == null)
        {
            front = rear = node;
        }
        else
        {
            rear.next = node;
            rear = rear.next;
        }
        size++;
        return true;
    }

    @Override
    public E poll() {
        E e = peek();
        front = front.next;
        if(front == null)  rear=null;
        size--;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        return front.val;
    }

    @Override
    public void clear() {
        size = 0; front=rear=null;
    }

    @Override
    public boolean contains(E e) {
        if(!isEmpty()) {
            Node<E> node = front;
            while (node != null) {
                if(node.val.equals(e)) return true;
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean validate() {
        int elementsCount = 0;
        Node<E> node = front;
        while(node != null)
        {
            if(node.val == null) return false;
            node = node.next;
            elementsCount++;
        }
        return (elementsCount == size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(front);
    }

    private static class ListIterator<E> implements Iterator<E>
    {
        private Node<E> front;

        ListIterator(Node<E> top) {
            this.front = top;
        }

        @Override
        public boolean hasNext() {
            return (front !=null);
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            E e = front.val;
            front = front.next;
            return e;
        }
    }

    private static class Node<E>
    {
        private E val;
        private Node<E> next;

        Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", children=" + next +
                    '}';
        }
    }
}
