package org.example.algorithm.link.reverse;

import org.example.algorithm.util.link.ListNode;

import java.util.HashMap;

public class L206_ReverseList {

    public ListNode reverseList(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }

}
