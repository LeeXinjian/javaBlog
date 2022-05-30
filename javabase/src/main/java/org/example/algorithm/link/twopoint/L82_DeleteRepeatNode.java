package org.example.algorithm.link.twopoint;

import org.example.algorithm.util.link.ListNode;

public class L82_DeleteRepeatNode {

    /**
     * 递归
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        }

        int tmpValue = head.val;
        while (head != null && head.val == tmpValue) {
            head = head.next;
        }

        return deleteDuplicates(head);
    }


    public ListNode deleteDuplicatesTwoPoint(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode result = new ListNode(0, head);
        head = result;

        while (head.next != null && head.next.next != null) {
            // 出现值相等
            if (head.next.val == head.next.next.val) {
                int tmpVal = head.next.val;
                while (head.next != null && head.next.val == tmpVal) {
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }

        return result.next;
    }


}
