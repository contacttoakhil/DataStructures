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
        int c = 0;
        ListNode head = new ListNode(0);
        ListNode l3 = head;

        while(l1!=null || l2!=null) {
            if(l1 != null) {
                c = c + l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                c = c + l2.val;
                l2 = l2.next;
            }
            l3.next = new ListNode(c%10);
            c = c/10;
            l3 = l3.next;
        }
        if(c == 1) {
            l3.next = new ListNode(c);
        }
        return head.next;
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

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
