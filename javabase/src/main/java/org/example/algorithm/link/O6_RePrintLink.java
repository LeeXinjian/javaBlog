package org.example.algorithm.link;


import org.example.algorithm.base.Node;

/**
 * 倒着输出链表
 */
public class O6_RePrintLink {

    public static void main(String[] args) {
        Node head = initNode();
        doRePrint(head);
    }

    private static void doRePrint(Node head) {
        if (head != null) {
            doRePrint(head.getNext());
            System.out.println(head.getValue());
        }
    }

    private static Node initNode() {
        Node first = new Node(null, 50);
        Node current = first;
        for (int i = 0; i < 10; i++) {
            current.setNext(new Node(null, i));
            current = current.getNext();
        }

        return first;
    }
}
