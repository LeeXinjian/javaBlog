package org.example.algorithm.link.LRU;

import java.util.HashMap;

public class LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
    }


    int size;
    HashMap<Integer, Node> cache = new HashMap<>();
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.size = capacity;
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;
    }


    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }

        moveToHead(node);
        return node.value;
    }


    public void put(int key, int value) {
        Node node = cache.get(key);

        // 不存在
        if (node == null) {
            // 满了
            if (size == 0) {
                // 从尾部删除一个
                removeFromTail();
                size++;
            }
            // 新建结点
            Node newNode = new Node(key, value);
            // 新结点放入头部
            putToHead(newNode);
            // 放入缓存
            cache.put(key, newNode);
            // 可存入元素量-1
            size--;
        } else {
            // 存在
            moveToHead(node);
            node.value = value;
        }
    }


    private void moveToHead(Node node) {
        removeNode(node);
        putToHead(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 放入头部
    private void putToHead(Node node) {

        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;

    }

    // 尾部删除一个
    private void removeFromTail() {
        Node lastOne = tail.prev;
        cache.remove(lastOne.key);
        removeNode(lastOne);
    }
}

class Node {

    int key;
    int value;


    Node prev;
    Node next;


    Node() {

    }

    Node(int key, int vaule) {
        this.key = key;
        this.value = vaule;
    }

    Node(int key, int vaule, Node prev, Node next) {
        this.key = key;
        this.value = vaule;
        this.prev = prev;
        this.next = next;
    }
}