package org.example.algorithm.link.reverse;

import javafx.util.Pair;
import org.example.algorithm.util.link.ListNode;
import org.example.algorithm.util.link.ListNodeUtil;

public class L25_ReverseKGroup {

    public static void main(String[] args) {
        int[] test = {1, 2};
        ListNode head = ListNodeUtil.initAndPrintNodes(test);
        ListNode newNode = new L25_ReverseKGroup().reverseKGroup(head, 2);
        ListNodeUtil.printNode(newNode);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        // 当前轮是否需要反转
        Pair<Boolean, ListNode> needReverse = isNeedReverse(k, head);
        if (!needReverse.getKey()) {
            return head;
        }
        // 下一轮的头节点
        ListNode nextBegin = needReverse.getValue();

        // 反转本轮,上轮的尾巴结点指向当前轮头节点
        ListNode curReverserHead = reverse(head, k);

        // 反转下一轮
        head.next = reverseKGroup(nextBegin, k);

        return curReverserHead;
    }


    /**
     * left : 是否需要反转
     * right : 元素
     */
    private Pair<Boolean, ListNode> isNeedReverse(int k, ListNode nextBegin) {
        for (int i = 0; i < k; i++) {
            // 无需反转
            if (nextBegin == null) {
                return new Pair<>(false, null);
            }
            // 位移
            nextBegin = nextBegin.next;
        }
        return new Pair<>(true, nextBegin);
    }


    public ListNode reverse(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        ListNode newHead = reverse(head.next, --k);

        head.next.next = head;
        head.next = null;

        return newHead;
    }


}
