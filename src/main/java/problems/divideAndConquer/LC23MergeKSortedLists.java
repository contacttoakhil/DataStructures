package main.java.problems.divideAndConquer;

import main.java.problems.list.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LC23MergeKSortedLists {

    private static int compare(ListNode o1, ListNode o2) {
        if (o1.val < o2.val)
            return -1;
        else if (o1.val == o2.val)
            return 0;
        else
            return 1;
    }

    public ListNode mergeKListsUsingPQ(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue= new PriorityQueue<>(lists.length, LC23MergeKSortedLists::compare);
        ListNode head = new ListNode(0);
        ListNode start = head;
        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);
        while (!queue.isEmpty()){
            start.next = queue.poll();
            start = start.next;
            if (start.next != null)
                queue.add(start.next);
        }
        return head.next;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        return divideAndConquer(lists, 0, lists.length - 1);
    }

    private ListNode divideAndConquer(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        } else if (start == end - 1) {
            return merge2Lists(lists[start], lists[end]);
        } else if (start > end) {
            return null;
        }

        // DIVIDE
        ListNode left = divideAndConquer(lists, start, start + (end - start) / 2);
        ListNode right = divideAndConquer(lists, start + (end - start) / 2 + 1, end);

        // MERGE
        return merge2Lists(left, right);
    }

    // Same as leetcode problem 21. Merge Two Sorted Lists
    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode l3 = head;
        while (l1!=null || l2!=null) {
            if( l1 != null && l2 != null) {
                if(l1.val < l2.val) {
                    l3.next = l1;
                    l1= l1.next;
                }
                else
                {
                    l3.next = l2;
                    l2 = l2.next;
                }
                l3 = l3.next;
            }
            else if(l1 != null)
            {
                l3.next = l1;
                break;
            }
            else
            {
                l3.next = l2;
                break;
            }
        }
        return head.next;
    }

}

