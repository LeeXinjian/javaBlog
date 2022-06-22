package org.example.algorithm.leetcode;

public class Trie {

    TrieNode head;

    public Trie() {
        head = new TrieNode();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }

    public void insert(String word) {
        TrieNode curNode = head;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curNode.childNodes[index] == null) {
                curNode.childNodes[index] = new TrieNode();
            }
            curNode.isEnd = false;
            curNode = curNode.childNodes[index];
        }

        curNode.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode curNode = head;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curNode.childNodes[index] == null) {
                return false;
            }
            curNode = curNode.childNodes[index];
        }

        return curNode.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode curNode = head;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (curNode.childNodes[index] == null) {
                return false;
            }
            curNode = curNode.childNodes[index];
        }
        return true;
    }
}

class TrieNode {

    boolean isEnd = true;

    TrieNode[] childNodes = new TrieNode[26];
}


