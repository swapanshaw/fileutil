package org.opensource.fileutil.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Tri data structure is used to store the file content and that will help to search the text
 * 
 * @author sshaw
 *
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode temp = root;
        final int wc = word.length();
        for (int i = 0; i < wc; i++) {
            Character c = word.charAt(i);
            if (temp.children.get(c) == null) {
                if (i != wc - 1) {
                    temp.children.put(c, new TrieNode());
                } else {
                    temp.children.put(c, new TrieNode(true));
                }
            }

        }

    }

    public boolean find(String str) {
        TrieNode temp = root;
        Map<Character, TrieNode> triNode = null;
        for (int i = 0; i < str.length(); i++) {
            triNode = temp.children;
            Set<Character> charSet = triNode.keySet();
            if (!charSet.contains(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * TriNode contains Map<Character, TrieNode>
     * 
     * @author sshaw
     *
     */
    private class TrieNode {
        private Map<Character, TrieNode> children;
        private Boolean endOfLine;

        public TrieNode() {
            this.children = new HashMap<>();
            this.endOfLine = false;
        }

        public TrieNode(Boolean endOfLine) {
            this.children = new HashMap<>();
            this.endOfLine = endOfLine;
        }
    }
}
