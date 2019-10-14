package main.java.ds.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/***
 * A monotonic queue with three operations:
 *
 * push: push an element into the queue; O (1) (amortized)
 *
 * pop: pop an element out of the queue; O(1) (pop = remove, it can't report this element)
 *
 * max: report the max element in queue;O(1). It doesn't pop the max element: It pops the first element (in original order) in queue.
 *
 * It takes only O(n) time to process a N-size sliding window minimum/maximum problem.
 */
public class MaxQueue<T extends Comparable<T>> {
    private Deque<T> q = new ArrayDeque<>();

    public void push(T element){

        while(!q.isEmpty() && q.peekLast().compareTo(element) <0) q.pollLast();
        q.offerLast(element);
    }
    public T front(){
        return q.peekFirst();
    }

    public void pop(Integer n){
        if (n.equals(q.peekFirst()))
            q.pollFirst();
    }
}
