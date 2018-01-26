package main.java.problems.list;

import main.java.ds.list.SLLNode;
import main.java.ds.list.SinglyLinkedList;
import main.java.ds.stack.ArrayStack;
import main.java.ds.stack.IStack;

/**
 * Check if a linked list (singly) is palindrome.
 */
public class PalindromeList {

    private void checkPalindrome(SLLNode<Integer> origHead) {
        boolean result = isPalindrome(origHead);
        System.out.println("Using reversal: " + result);
        result = isPalindromeIterative(origHead);
        System.out.println("Using fast-slow: " + result);
    }

    private boolean isPalindrome(SLLNode<Integer> origHead) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        SLLNode reverseHead = reverseLinkedList.reverseAndClone(origHead);
        while(origHead != null && reverseHead != null) {
            if(!origHead.getVal().equals(reverseHead.getVal()))
                return false;
            origHead = origHead.getNext();
            reverseHead = reverseHead.getNext();
        }
        return true;
    }


    private boolean isPalindromeIterative(SLLNode<Integer> origHead) {
        SLLNode<Integer> fast, slow;
        fast = slow = origHead;
        IStack<Integer> stack = new ArrayStack<>();
        while( fast != null && fast.getNext() != null ) {
            stack.push(slow.getVal());
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        if (fast != null) {      // if odd number of elements then skip the middle element
            slow = slow.getNext();
        }
        while(slow != null) {
            int top = stack.pop();
            if(top != slow.getVal())
                return false;
            slow = slow.getNext();
        }
        return true;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        linkedList.insert(10, 0);        linkedList.insert(20, 0);        linkedList.insert(30, 0);        linkedList.insert(20, 0);        linkedList.insert(10, 0);
        linkedList.printList();
        linkedList.clear();
        PalindromeList palindromeList = new PalindromeList();
        palindromeList.checkPalindrome(linkedList.getHead());
        linkedList.insert(10, 0);        linkedList.insert(20, 0);        linkedList.insert(20, 0);        linkedList.insert(10, 0);
        linkedList.printList();
        palindromeList.checkPalindrome(linkedList.getHead());
    }
}
