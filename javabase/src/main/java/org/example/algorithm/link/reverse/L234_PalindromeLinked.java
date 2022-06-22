package org.example.algorithm.link.reverse;

import org.example.algorithm.util.link.ListNode;
import org.example.algorithm.util.link.ListNodeUtil;

public class L234_PalindromeLinked {

    public static void main(String[] args) {
        int[] nodes = {1,2,2,1};
        ListNode head = ListNodeUtil.initNodes(nodes);

        System.out.println(new L234_PalindromeLinked().isPalindrome(head));

    }

    public boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        ListNode newHead = reverNode(temp);
        while (head != null && newHead != null) {
            if (head.val != newHead.val) {
                return false;
            }

            head = head.next;
            newHead = newHead.next;
        }

        return head == null && newHead == null;
    }

    public ListNode reverNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverNode(head.next);


        head.next.next = head;
        head.next = null;

        return newHead;
    }

}
