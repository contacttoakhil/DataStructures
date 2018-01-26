package main.java.ds.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class SinglyLinkedList<E> implements Iterable<E>{
    private SLLNode<E> head = null;
    private int size = 0;

    public void insert(E e, int pos) {
        SLLNode<E> nodeToInsert = new SLLNode<>(e);
        if(pos == 0) {
            nodeToInsert.next = head;
            head = nodeToInsert;
            size++;
        }
        else
        {
            if(isEmpty()) throw new EmptyListException();
            SLLNode<E> prev = head;
            for(int i=0; i<pos; i++)
                prev = prev.next;
            nodeToInsert.next = prev.next;
            prev.setNext(nodeToInsert);
            size++;
        }
    }

    public void delete(int pos) {
        if(isEmpty()) throw new EmptyListException();
        if(pos == 0) {
            SLLNode<E> nextToHead = head.next;
            head = null;
            head = nextToHead;
        }
        else
        {
            SLLNode<E> prev = getPreviousNode(pos);
            SLLNode<E> current = prev.next;
            prev.next = current.next;
            current = null;
        }
        size--;
    }
    public boolean isEmpty() { return  size==0;}

    public void clear() {
        head = null;
        size = 0;
    }

    public void printList() {
        StringJoiner joiner = new StringJoiner(" -> ");
        for(E e : this) {
            joiner.add(Objects.toString(e));
        }
        System.out.println(joiner);
    }

    public SLLNode<E> getHead() {
        return head;
    }
    private SLLNode<E> getPreviousNode(int pos) {
        SLLNode<E> prev = head;
        for(int i=0; i<pos; i++)
            prev = prev.next;
        return prev;
    }

    public Iterator<E> iterator() {
        return new ListIterator<>(this.head);
    }

    private static class ListIterator<E> implements Iterator<E>
    {
        private SLLNode<E> head;

        ListIterator(SLLNode<E> head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return (head!=null);
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            E e = head.val;
            head = head.next;
            return e;
        }
    }
}

class EmptyListException extends RuntimeException {

}