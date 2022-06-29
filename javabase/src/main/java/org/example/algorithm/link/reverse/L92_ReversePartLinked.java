package org.example.algorithm.link.reverse;

import org.example.algorithm.util.link.ListNode;

import java.util.Stack;

import static org.example.algorithm.util.link.ListNodeUtil.initAndPrintNodes;
import static org.example.algorithm.util.link.ListNodeUtil.printNode;

public class L92_ReversePartLinked {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5};
        printNode(
                new L92_ReversePartLinked()
                        .reverseBetweenIterator(
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

    public ListNode reverseBetweenIterator(ListNode head, int left, int right) {
        if (head == null) {
            return head;
        }

        ListNode result = head;

        int curIndex = 1;
        ListNode prev = null;
        // 遍历
        while (curIndex < left && head != null) {
            prev = head;
            head = head.next;
            curIndex++;
        }

        // 暂存反转后的尾元素
        ListNode revertEnd = head;

        ListNode revertPrev = null;
        // 需要反转的部分
        while (curIndex <= right && head != null) {
            // 暂存当前
            ListNode tmp = head.next;

            // 指针反转
            head.next = revertPrev;
            // 位移前置指针
            revertPrev = head;
            // 指针后移
            head = tmp;
            curIndex++;
        }

        // 前半段链接到转移之后的头指针
        prev.next = revertPrev;
        // 移花接木后半段
        revertEnd.next = head;


        return result;

    }


}
