package org.opensource.fileutil.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Trie data structure is used to store the file content and that will help to
 * search the text
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
		TrieNode current = root;
		final int wc = word.length();
		for (int i = 0; i < wc; i++) {
			Character c = word.charAt(i);
			TrieNode node = current.children.get(c);
			if (node == null) {
				node = new TrieNode();
				current.children.put(c, node);
			}
			current = node;
		}
		current.setEndOfLine(Boolean.TRUE);
		current.setFrequncy(current.getFrequncy() == null ? 1 : current.getFrequncy() + 1);
	}

	public void insert(String word, PriorityQueue<MinHeapNode> priorityQueue) {
		TrieNode current = root;
		final int wc = word.length();
		for (int i = 0; i < wc; i++) {
			Character c = word.charAt(i);
			TrieNode node = current.children.get(c);
			if (node == null) {
				node = new TrieNode();
				current.children.put(c, node);
			}
			current = node;
		}
		current.setEndOfLine(Boolean.TRUE);
		current.setFrequncy(current.getFrequncy() + 1);
		insertIntoPriorityQueue(word, current, priorityQueue);
	}

	private void insertIntoPriorityQueue(String word, TrieNode root, PriorityQueue<MinHeapNode> priorityQueue) {
		MinHeapNode node = null;
		priorityQueue.add(new MinHeapNode(root, word, root.getFrequncy()));
	}

	public boolean find(String str) {
		TrieNode temp = root;
		Map<Character, TrieNode> triNode = null;
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			triNode = temp.children;
			TrieNode node = triNode.get(str.charAt(i));;
			if(node == null) {
				flag = false;
				break;
			}
			temp = node;
		}

		return flag;
	}

	/**
	 * TriNode contains Map<Character, TrieNode>
	 * 
	 * @author sshaw
	 *
	 */
	@SuppressWarnings("unused")
	private class TrieNode {
		private Map<Character, TrieNode> children;
		private Boolean endOfLine;
		private Integer frequncy;
		private Integer linkToNode;

		public TrieNode() {
			this.children = new HashMap<>();
			this.endOfLine = Boolean.FALSE;
		}

		public Map<Character, TrieNode> getChildren() {
			return children;
		}

		public void setChildren(Map<Character, TrieNode> children) {
			this.children = children;
		}

		public Boolean getEndOfLine() {
			return endOfLine;
		}

		public void setEndOfLine(Boolean endOfLine) {
			this.endOfLine = endOfLine;
		}

		public Integer getFrequncy() {
			return frequncy;
		}

		public void setFrequncy(Integer frequncy) {
			this.frequncy = frequncy;
		}

		public Integer getLinkToNode() {
			return linkToNode;
		}

		public void setLinkToNode(Integer linkToNode) {
			this.linkToNode = linkToNode;
		}

	}

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	public class MinHeapNode {
		private TrieNode root; // leave node of the trie
		private String world;
		private Integer frequency;

		public MinHeapNode(TrieNode root, String word, Integer frequency) {
			super();
			this.root = root;
			this.world = word;
			this.frequency = frequency;
		}

		public TrieNode getRoot() {
			return root;
		}

		public void setRoot(TrieNode root) {
			this.root = root;
		}

		public String getWorld() {
			return world;
		}

		public void setWorld(String world) {
			this.world = world;
		}

		public Integer getFrequency() {
			return frequency;
		}

		public void setFrequency(Integer frequency) {
			this.frequency = frequency;
		}

	}
}
