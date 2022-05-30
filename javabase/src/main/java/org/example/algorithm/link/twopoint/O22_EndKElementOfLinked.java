package org.example.algorithm.link.twopoint;


import org.example.algorithm.util.link.ListNode;

import static org.example.algorithm.util.link.ListNodeUtil.initNodes;
import static org.example.algorithm.util.link.ListNodeUtil.printNode;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。
 */
public class O22_EndKElementOfLinked {

    public static void main(String[] args) {
        int[] init = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ListNode node = initNodes(init);
        int endK = doGetEndK(8, node);

        printNode(node);
        System.out.println("结果为" + endK);
    }

    private static int doGetEndK(int k, ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        int cur = 0;
        while (fast != null) {
            if (cur >= k) {
                slow = slow.next;
            }
            fast = fast.next;
            cur++;
        }

        return slow == null ? -1 : slow.val;
    }
}
