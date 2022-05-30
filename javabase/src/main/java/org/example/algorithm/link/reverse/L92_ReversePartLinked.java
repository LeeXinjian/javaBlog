package org.example.algorithm.link.reverse;

import org.example.algorithm.util.link.ListNode;

import static org.example.algorithm.util.link.ListNodeUtil.initAndPrintNodes;
import static org.example.algorithm.util.link.ListNodeUtil.printNode;

public class L92_ReversePartLinked {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5};
        printNode(
                new L92_ReversePartLinked()
                        .reverseBetween(
                                initAndPrintNodes(ints), 2, 4)
        );


    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        return helper(head, m, n, 1);
    }

    /*
     * 当前递归的定义是, 获取到当前节点结果
     */
    public ListNode helper(ListNode head, int m, int n, int index) {
        if (head == null) {
            return head;
        }

        if (index < m) {
            head.next = helper(head.next, m, n, ++index);
            return head;
        }

        ListNode orgHead = head;

        ListNode prev = null;
        while (index <= n && head != null) {
            ListNode next = head.next;

            head.next = prev;

            prev = head;
            head = next;

            index++;
        }


        orgHead.next = head;
        return prev;
    }


}
