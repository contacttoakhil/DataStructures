package main.java.problems.list;

/***
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 *
 * https://leetcode.com/problems/palindrome-linked-list/
 */
public class LC234PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next==null)
            return true;
        int len = getLength(head);
        ListNode mid = findMiddle(head);
        ListNode newHead;
        if (len % 2 == 0) {
            newHead = mid.next;
            mid.next = null;
        } else {
            ListNode dummyNode = new ListNode(mid.val);
            dummyNode.next = mid.next;
            newHead = dummyNode;
        }

        // Step 2: reverse the list
        newHead = reverseList(newHead);

        // Step 3: compare the list
        ListNode p = head;
        ListNode q = newHead;
        while (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }

    private int getLength(ListNode head) {
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }
        return len;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        LC206ReverseLinkedList reverseLinkedList = new LC206ReverseLinkedList();
        return reverseLinkedList.reverseList(head);
    }
}
