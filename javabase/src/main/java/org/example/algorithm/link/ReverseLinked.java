package org.example.algorithm.link;

public class ReverseLinked {


    public static void main(String[] args) {

        // 初始头节点
        Node orgNode = initNode();
        // 结果结点
        Node result = new Node();
        // 能获取下一个
        while (orgNode != null) {

            // 当前节点
            Node newCurNode = new Node(orgNode.value);
            newCurNode.next = result.next;

            result.next = newCurNode;


            // 后移
            orgNode = orgNode.next;
        }

        while ((result = result.next) != null) {
            System.out.println("curNode:" + result.value);
        }

    }

    static Node initNode() {
        // do init;
        return new Node("A", new Node("B", new Node("C")));
    }
}

class Node{

    String value;

    Node next;

    Node(){}

    Node(String val){
        this.value = val;
    }

    Node(String val,Node next){
        this.value = val;
        this.next = next;
    }
}
