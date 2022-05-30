package org.example.algorithm.link.reverse;

import org.example.algorithm.util.link.ListNode;
import org.example.algorithm.util.link.ListNodeUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class L19_DeleteLastKNode {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.initNodes(new int[]{1,2,3,4,5});
        ListNode node = new L19_DeleteLastKNode().removeNthFromEnd(head,2 );
        ListNodeUtil.printNode(node);
    }


    int limit = 0;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        limit = n;
        return remove(head);
    }


    public ListNode remove(ListNode head){
        if(head == null ){
            return head;
        }

        ListNode newNext = remove(head.next);
        limit--;

        if(limit == 0){
            return newNext;
        }

        head.next = newNext;
        return head;
    }

}
