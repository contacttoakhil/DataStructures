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
        int n = getLength(head);
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

        HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        
        return dmy.next;
    }
    private int getLength(ListNode head) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);
        return n;
    }

}
