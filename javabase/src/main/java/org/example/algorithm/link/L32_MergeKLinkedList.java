package org.example.algorithm.link;


import org.example.algorithm.util.link.ListNode;

public class L32_MergeKLinkedList {

    public static void main(String[] args) {

    }


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }


    public ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = (start + end) / 2;
        ListNode left = mergeKLists(lists, start, mid);
        ListNode right = mergeKLists(lists, mid + 1, end);

        return mergeTwoListRecursion(left, right);

    }


    /**
     * 合并两个链表 - 递归
     */
    public ListNode mergeTwoListRecursion(ListNode one, ListNode two) {
        if (one == null) {
            return two;
        }

        if (two == null) {
            return one;
        }

        if (one.val < two.val) {
            one.next = mergeTwoListRecursion(one.next, two);
            return one;
        } else {
            two.next = mergeTwoListRecursion(one, two.next);
            return two;
        }
    }

    /**
     * 合并两个链表
     */
    public ListNode mergeTwoList(ListNode one, ListNode two) {
        ListNode result = new ListNode();
        ListNode head = result;
        while (one != null && two != null) {
            if (one.val < two.val) {
                head.next = one;
                one = one.next;
                head = head.next;
            } else {
                head.next = two;
                two = two.next;
                head = head.next;
            }
        }

        if (one != null) {
            head.next = one;
        }

        if (two != null) {
            head.next = two;
        }

        return result.next;
    }
}
