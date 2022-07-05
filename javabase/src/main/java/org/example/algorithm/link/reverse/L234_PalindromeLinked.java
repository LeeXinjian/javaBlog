package org.example.algorithm.link.reverse;

import org.example.algorithm.util.link.ListNode;
import org.example.algorithm.util.link.ListNodeUtil;

public class L234_PalindromeLinked {

    ListNode curNode;

    public static void main(String[] args) {
        int[] nodes = {1,2,2,1};
        ListNode head = ListNodeUtil.initNodes(nodes);

        System.out.println(new L234_PalindromeLinked().isPalindrome(head));

    }

    public boolean check(ListNode head){
        if (head == null) {
            return true;
        }

        //递归到最下层元素
        if (!check(head.next)) {
            return false;
        }

        if (head.val != curNode.val) {
            return false;
        }

        curNode = curNode.next;
        return true;

    }

    public boolean isPalindrome(ListNode head) {
        // 左指针
        curNode = head;
        //
        return check(head.next);
    }



}
