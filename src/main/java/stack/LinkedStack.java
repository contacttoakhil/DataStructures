package main.java.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements IStack<E> {
    private Node<E> top = null;
    private int size = 0;

    @Override
    public boolean push(E e) {
        Node<E> newNode = new Node<>(e,null);
        newNode.next = top;
        top = newNode;
        size++;
        return true;
    }

    @Override
    public E pop() {
        E e = peek();
        top = top.next;
        size--;
        return e;
    }

    @Override
    public E peek() {
        if(isEmpty())
            throw new NoSuchElementException();
        return top.val;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(E e) {
        if(!isEmpty()) {
            Node<E> node = top;
            while (node != null) {
                if(node.val.equals(e)) return true;
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public boolean validate() {
        int elementsCount = 0;
        Node<E> node = top;
        while(node != null)
        {
            if(node.val == null) return false;
            node = node.next;
            elementsCount++;
        }
        return (elementsCount == size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(this.top);
    }

    private static class ListIterator<E> implements Iterator<E>
    {
        private Node<E> top;

        ListIterator(Node<E> top) {
            this.top = top;
        }

        @Override
        public boolean hasNext() {
            return (top!=null);
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            E e = top.val;
            top = top.next;
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
                    ", next=" + next +
                    '}';
        }
    }
}
