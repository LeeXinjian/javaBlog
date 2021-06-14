package org.example.algorithm.link;

import org.example.algorithm.base.Node;
import static org.example.algorithm.base.Node.soutLink;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。
 */
public class O22_EndKElementOfLinked {

    public static void main(String[] args) {
        Node node = initNode();
        int endK = doGetEndK(8, node);

        soutLink(node);
        System.out.println("结果为" + endK);
    }

    private static int doGetEndK(int k, Node head) {
        Node fast = head;
        Node slow = head;

        int cur = 0;
        while (fast != null) {
            if (cur >= k) {
                slow = slow.getNext();
            }
            fast = fast.getNext();
            cur++;
        }

        return slow == null ? -1 : slow.getValue();
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
