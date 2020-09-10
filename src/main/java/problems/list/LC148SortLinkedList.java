package main.java.problems.list;

/***
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class LC148SortLinkedList {

    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }

        //partition the list
        ListNode p1 = head;
        ListNode firstEnd = getFirstEnd(head);
        ListNode p2 = firstEnd.next;
        firstEnd.next = null;

        //sort each list
        p1 = sortList(p1);
        p2 = sortList(p2);

        //merge two lists
        return merge(p1, p2);
    }

    //get the list partition point
    private ListNode getFirstEnd(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(slow!=null && fast!=null){
            if(fast.next==null||fast.next.next==null)
                return slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return head;
    }

    //merge two list
    private ListNode merge(ListNode n1, ListNode n2){
        ListNode head = new ListNode(0);
        ListNode p = head;
        ListNode p1 = n1;
        ListNode p2 = n2;
        while(p1!=null && p2!=null){
            if(p1.val<p2.val){
                p.next = p1;
                p1 = p1.next;
            }else{
                p.next = p2;
                p2 = p2.next;
            }

            p = p.next;
        }

        if(p1!=null){
            p.next = p1;
        }

        if(p2!=null){
            p.next = p2;
        }

        return head.next;
    }

}
