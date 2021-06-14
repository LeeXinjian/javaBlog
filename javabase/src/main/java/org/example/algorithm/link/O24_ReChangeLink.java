package org.example.algorithm.link;

import org.example.algorithm.base.Node;
import static org.example.algorithm.base.Node.soutLink;

/**
 * 翻转链表
 */
public class O24_ReChangeLink {

    public static void main(String[] args) {
        Node node = initNode();
        soutLink(node);
        node = doConvert(node);
        soutLink(node);
    }

    private static Node doConvert(Node node) {
        if (node == null || node.getNext() == null) {
            return node;
        }

        Node tmp = null;
        while (node != null) {
            tmp = new Node(tmp, node.getValue());
            node = node.getNext();
        }

        return tmp;
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
