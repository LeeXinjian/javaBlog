package org.example.algorithm.link;


import org.example.algorithm.util.link.ListNode;

import static org.example.algorithm.util.link.ListNodeUtil.initNodes;
import static org.example.algorithm.util.link.ListNodeUtil.printNode;

/**
 * 翻转链表
 */
public class O24_ReChangeLink {

    public static void main(String[] args) {

        int[] init = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        printNode(
                doConvert(
                        initNodes(init)
                )
        );
    }

    private static ListNode doConvert(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode tmp = null;
        while (node != null) {
            tmp = new ListNode(node.val, tmp);
            node = node.next;
        }

        return tmp;
    }


}
