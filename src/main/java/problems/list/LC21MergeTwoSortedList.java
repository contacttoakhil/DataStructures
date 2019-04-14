package main.java.problems.list;

public class LC21MergeTwoSortedList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
