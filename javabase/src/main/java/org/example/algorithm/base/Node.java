package org.example.algorithm.base;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node {

    private Node next;

    private Integer value;

    public Node(Integer value){

    }

    public static void soutLink(Node node) {
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
        System.out.println("--finish");
    }
}