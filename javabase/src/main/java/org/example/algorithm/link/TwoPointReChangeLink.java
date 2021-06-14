package org.example.algorithm.link;

import org.example.algorithm.base.Node;
import static org.example.algorithm.base.Node.soutLink;

public class TwoPointReChangeLink {

    public static void main(String[] args) {

        int pos = 5;

        Node node = initNode();
        soutLink(node);
        node = doConvert(node, pos);
        soutLink(node);
    }

    private static Node doConvert(Node node, int pos) {
        Node first = null;
        Node secend = null;
        Node firstTail = null;
        int curPos = 0;
        while (node != null) {
            if (curPos < pos) {
                // 初始化尾指针
                if (curPos == 0) {
                    firstTail = new Node(first, node.getValue());
                    first = firstTail;
                }else{
                    first = new Node(first, node.getValue());
                }
            } else {
                secend = new Node(secend, node.getValue());
            }

            node = node.getNext();
            curPos++;
        }

        firstTail.setNext(secend);
        return first;
    }

    private static void soutLink(Node node) {
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
        System.out.println("--finish");
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
