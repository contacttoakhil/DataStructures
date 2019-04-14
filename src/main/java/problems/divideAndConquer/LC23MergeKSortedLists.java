package main.java.problems.divideAndConquer;

import main.java.problems.list.ListNode;

public class LC23MergeKSortedLists {

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
