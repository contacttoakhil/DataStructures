package main.java.problems.list;

/***
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class LC02AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry == 1) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        LC02AddTwoNumbers temp3 = new LC02AddTwoNumbers();
        ListNode start = temp3.addTwoNumbers(getNumberOne(), getNumberTwo());
        print(start);
    }

    // helper methods
    private static void print(ListNode start) {
        while(start != null) {
            System.out.print(start.val);
            start = start.next;
        }
    }

    private static ListNode getNumberOne() {
        ListNode ln1 = new ListNode(1);
        return ln1;
    }

    private static ListNode getNumberTwo() {
        ListNode ln1 = new ListNode(9);
        ListNode ln2 = new ListNode(9);
        ln1.next = ln2;
        return ln1;
    }

}
