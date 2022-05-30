package org.example.algorithm.link.reverse;

import org.example.algorithm.util.link.ListNode;

public class L206_ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }

        return prev;
    }

    public ListNode reverseListRecursion(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}

