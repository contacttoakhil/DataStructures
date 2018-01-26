package main.java.problems.list;

import main.java.ds.list.SLLNode;

public class DetectCycleInList {

    public boolean containsLoop(SLLNode head) {
        SLLNode fast = head;
        SLLNode slow = head;

        while( fast != null && fast.getNext() != null ) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if(slow == fast)
                break;
        }
        // if odd number of elements then skip the middle element
        if (fast == null || fast.getNext() == null) {
            return false;
        }
        return true;
    }

}
