package org.example.thread.collections.map.demo;

import com.google.common.collect.Maps;

import java.util.HashMap;

public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("abc","123");
    }
}
