package main.java.ds.list;

public class SLLNode<E> {
    E val;
    SLLNode<E> next;

    public SLLNode(E data) {
        this.val = data;
        this.next = null;
    }

    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }

    public SLLNode<E> getNext() {
        return next;
    }

    public void setNext(SLLNode<E> next) {
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
