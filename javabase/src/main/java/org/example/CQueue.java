package org.example;

import java.util.LinkedList;

/**
 * 两个栈实现队列
 */
public class CQueue {

    LinkedList<Integer> A, B;

    public CQueue() {
        A = new LinkedList<Integer>();
        B = new LinkedList<Integer>();
    }

    public void appendTail(int value) {
        A.addLast(value);
    }

    public int deleteHead() {
        if (!B.isEmpty()){
            return B.removeLast();
        }

        if (A.isEmpty()){
            return -1;
        }

        synchronized (this){
            while (!A.isEmpty())
                B.addLast(A.removeLast());
        }

        return B.removeLast();
    }

    public static void main(String[] args) {
        String s = new String();
        for (int i = 0; i < s.length(); i++) {
            
        }
    }

}
