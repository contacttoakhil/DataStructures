package main.java.problems.list;

import main.java.ds.list.SLLNode;
import main.java.ds.list.SinglyLinkedList;

/**
 * Reverse a linked list (singly)
 */
public class ReverseLinkedList {

    /**
     * Reverse the list with head node, create a copy and return it.
     * @param node
     * @return
     */
    public SLLNode reverseAndClone(SLLNode node) {
        SLLNode head = null;
        while (node != null) {
            SLLNode nodeToInsert = new SLLNode<>(node.getVal());
            nodeToInsert.setNext(head);
            head = nodeToInsert;
            node = node.getNext();
        }
        return head;
    }

    public void printList(SLLNode node) {
        SLLNode current = node;
        while (current != null) {
            if(current.getNext() == null)
                System.out.print(current.getVal());
            else
                System.out.print(current.getVal() + " -> ");
            current = current.getNext();
        }
    }
    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.insert(10,0);
        singlyLinkedList.insert(20,0);
        singlyLinkedList.insert(30,0);
        singlyLinkedList.insert(40,0);
        singlyLinkedList.insert(50,0);
        singlyLinkedList.printList();
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        SLLNode<Integer> reverseHead = reverseLinkedList.reverseAndClone(singlyLinkedList.getHead());
        reverseLinkedList.printList(reverseHead);
    }
}
