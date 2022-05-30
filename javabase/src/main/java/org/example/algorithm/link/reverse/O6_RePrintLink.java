package org.example.algorithm.link.reverse;


import org.example.algorithm.util.link.ListNode;
import org.example.algorithm.util.link.ListNodeUtil;

import java.util.ArrayList;

/**
 * 倒着输出链表
 */
public class O6_RePrintLink {

    public static void main(String[] args) {

        reversePrint(ListNodeUtil.initAndPrintNodes(ListNodeUtil.defalutInt));
    }

    public static int[] reversePrint(ListNode head) {
        ArrayList<Integer> ints = new ArrayList<>();
        doReverse(head, ints);
        return ints.stream().mapToInt(i -> i).toArray();
    }

    public static void doReverse(ListNode head, ArrayList<Integer> ints) {
        if (head == null) {
            return;
        }

        doReverse(head.next, ints);
        ints.add(head.val);
    }
}
