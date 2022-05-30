package org.example.algorithm.link.twopoint;

import org.example.algorithm.link.reverse.L206_ReverseLinkedList;
import org.example.algorithm.util.link.ListNode;
import org.example.algorithm.util.link.ListNodeUtil;

public class L143_ReorderList {

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4};
        ListNode head = ListNodeUtil.initAndPrintNodes(test);
        ListNode listNode = new L143_ReorderList().reorderList(head);
        ListNodeUtil.printNode(listNode);
    }


    public ListNode reorderList(ListNode head) {
        if (head == null) {
            return null;
        }

        // 找到中间
        ListNode midOne = getMidNode(head);
        // 反转中间
        ListNode reverseMid = new L206_ReverseLinkedList().reverseListRecursion(midOne);
        // 重新链接链表
        getListNode(head, midOne, reverseMid);

        return head;
    }

    private void getListNode(ListNode head, ListNode midOne, ListNode reverseMid) {
        // 没到中间
        while (head != midOne) {
            ListNode tmp = reverseMid.next;
            ListNode headTmp = head.next;

            reverseMid.next = head.next.next;
            head.next = reverseMid;


            reverseMid = tmp;
            head = headTmp;
        }
    }

    private ListNode getMidNode(ListNode head) {
        ListNode quickPoint = head;
        while (quickPoint != null && head != null) {
            quickPoint = quickPoint.next.next;
            head = head.next;
        }

        return head;
    }
}
