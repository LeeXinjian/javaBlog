package org.example.algorithm.thread;

import org.example.algorithm.util.link.ListNode;
import org.example.algorithm.util.link.ListNodeUtil;

public class Test {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.initNodes(new int[]{1, 2, 4});
        ListNodeUtil.printNode(
                new Test().deleteDuplicates(head));

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        return deleteRepeat(head);
    }

    public ListNode deleteRepeat(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        // 值不相等
        if (node.val != node.next.val) {
            node.next = deleteRepeat(node.next);
            return node;
        }
        // 值相等
        ListNode tmp = node.next;
        while (tmp != null && tmp.val == node.val) {
            tmp = tmp.next;
        }
        return deleteRepeat(tmp);
    }
}
