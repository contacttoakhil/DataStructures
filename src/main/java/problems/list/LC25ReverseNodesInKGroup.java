package main.java.problems.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 * Source: https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11423/Short-but-recursive-Java-code-with-comments/12145
 */
public class LC25ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode current = head, next = null, prev = null;
        int count = 0;
        while (count < k && current != null)  { // reverse first k nodes
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        if (next != null) // next is pointer to (k+1)th node
            head.next = reverseKGroup(next, k);
        return prev; // prev is now head
    }

    public ListNode reverseKGroup3(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);

        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for(ListNode prev = dmy, tail = head; n >= k; n -= k) {
            for (int i = 1; i < k; i++) {
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }

            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head==null || head.next ==null || k==1)    return head;
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        ListNode begin = dummyhead;
        int i=0;
        while (head != null){
            i++;
            if (i%k != 0){
                head = head.next;
                continue;
            }
            begin = reverse(begin, head.next);
            head = begin.next;
        }
        return dummyhead.next;

    }
    /**
     * Reverse a link list between begin and end exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |
     * begin       end
     * after call begin = reverse(begin, end)
     *
     * 0->3->2->1->4->5->6
     *          |  |
     *      begin end
     * @return the reversed list's 'begin' node, which is the precedence of node end
     */
    public ListNode reverse(ListNode begin, ListNode end){
        ListNode curr = begin.next;
        ListNode next, first;
        ListNode prev = begin;
        first = curr;
        while (curr!=end){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }

    // ================= Utility Area =================

    ListNode head;  // head of list
    /* Utility functions */

    /* Inserts a new Node at front of the list. */
    public void push(int new_data)
    {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        ListNode new_node = new ListNode(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    /* Function to print linked list */
    void printList()
    {
        ListNode temp = head;
        while (temp != null)
        {
            System.out.print(temp.val+" ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        LC25ReverseNodesInKGroup llist = new LC25ReverseNodesInKGroup();
        llist.push(50);
        llist.push(40);
        llist.push(30);
        llist.push(20);
        llist.push(10);

        System.out.println("Original Linked List: ");
        llist.printList();
        llist.head = llist.reverseKGroup(llist.head, 3);
        System.out.println("Reversed list");
        llist.printList();
    }
}
