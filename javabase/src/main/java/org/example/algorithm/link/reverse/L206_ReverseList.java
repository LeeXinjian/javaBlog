package org.example.algorithm.link.reverse;

import org.example.algorithm.util.link.ListNode;

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

    /**
     * 遍历方式 反转链表
     */
    public ListNode reverseListIterator(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode tmp = head;
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return tmp;
    }

}
