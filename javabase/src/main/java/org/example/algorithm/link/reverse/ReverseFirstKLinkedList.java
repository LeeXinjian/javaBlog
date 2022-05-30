package org.example.algorithm.link.reverse;

import org.example.algorithm.util.link.ListNode;

import static org.example.algorithm.util.link.ListNodeUtil.initAndPrintNodes;
import static org.example.algorithm.util.link.ListNodeUtil.printNode;

public class ReverseFirstKLinkedList {


    static ListNode lastN = null;

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6};
        printNode(
                reverseListRecursion(
                        initAndPrintNodes(ints),
                        5)
        );
    }


    public static ListNode reverseListRecursion(ListNode head, int stop) {
        if (stop == 1) {
            lastN = head.next;
            return head;
        }

        ListNode last = reverseListRecursion(head.next, --stop);

        head.next.next = head;
        head.next = lastN;
        return last;

    }


}
