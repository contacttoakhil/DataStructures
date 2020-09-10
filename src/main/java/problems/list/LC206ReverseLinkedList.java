package main.java.problems.list;

import main.java.ds.list.SinglyLinkedList;

public class LC206ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    //====================================================================================
    private ListNode head;
    private void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    // Push at start
    public void push(int data) {
        ListNode new_node = new ListNode(data);
        new_node.next = head;
        head = new_node;
    }

    //====================================================================================

    public static void main(String[] args) {
        LC206ReverseLinkedList list = new LC206ReverseLinkedList();
        list.push(50);
        list.push(40);
        list.push(30);
        list.push(20);
        list.push(10);
        list.printList(list.head);
        System.out.println();
        list.head = list.reverseList(list.head);
        list.printList(list.head);
    }
}
