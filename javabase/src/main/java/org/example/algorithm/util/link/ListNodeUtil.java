package org.example.algorithm.util.link;

public class ListNodeUtil {

    public static final int[] defalutInt = {1,2,3,4,5,6,7,8,9,10};

    public static ListNode initNodes(int[] array) {

        ListNode head = new ListNode();
        ListNode point = head;

        for (int i = 0; i < array.length; i++) {
            point.next = new ListNode(array[i]);
            point = point.next;
        }

        return head.next;
    }

    public static ListNode initAndPrintNodes(int[] array) {
        ListNode listNode = initNodes(array);
        printNode(listNode);
        System.out.println("-------初始化分割线--------");
        return listNode;
    }

    public static void printNode(ListNode head) {
        ListNode print = head;
        while (print != null) {
            System.out.println(print.val);
            print = print.next;
        }
    }
}
