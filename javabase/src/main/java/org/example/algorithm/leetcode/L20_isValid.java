package org.example.algorithm.leetcode;

import java.util.HashMap;
import java.util.LinkedList;

public class L20_isValid {

    public boolean isValid(String s) {
        // 存一下关系
        HashMap<Character, Character> releation = new HashMap<>();
        releation.put('}', '{');
        releation.put(')', '(');
        releation.put(']', '[');

        LinkedList<Character> q = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Character beginFlag = releation.get(c);
            // 说明当前是头
            if (beginFlag == null) {
                q.add(c);
            } else {
                // 当前没有元素
                if(q.size() == 0){
                    return false;
                }

                // 当前是尾端,
                if (q.removeLast() != beginFlag) {
                    return false;
                }
            }
        }

        return q.size() == 0;

    }
}
