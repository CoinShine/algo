package com.shine.advanced.leetcode;

/**
 * description: 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * @author shine
 * @date 2019/9/29 15:08
 * @version 1.0
 */
public class Test208 {

	private TrieNode root;
	/** Initialize your data structure here. */
	public Test208() {
		root = new TrieNode();
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			if (!node.containsKey(currentChar)) {
				node.put(currentChar, new TrieNode());
			}
			node = node.get(currentChar);
		}
		node.setEnd();
	}


	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char current = word.charAt(i);
			if(node.containsKey(current)){
				node = node.get(current);
			}else{
				return false;
			}
		}
		return node.isEnd;

	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (int i = 0; i < prefix.length(); i++) {
			char current = prefix.charAt(i);
			if(node.containsKey(current)){
				node = node.get(current);
			}else {
				return false;
			}
		}
		return true;
	}


	/**
	 * 构造字典树 只包含小写字母的字典树，最多包含26个分支
	 */
	class TrieNode {

		// R links to node children
		private TrieNode[] children;

		private boolean isEnd;

		public TrieNode() {
			children = new TrieNode[26];
		}

		public boolean containsKey(char ch) {
			return children[ch -'a'] != null;
		}
		public TrieNode get(char ch) {
			return children[ch -'a'];
		}
		public void put(char ch, TrieNode node) {
			children[ch -'a'] = node;
		}
		public void setEnd() {
			isEnd = true;
		}
		public boolean isEnd() {
			return isEnd;
		}
	}

	/**
	 * Your Trie object will be instantiated and called as such:
	 * Trie obj = new Trie();
	 * obj.insert(word);
	 * boolean param_2 = obj.search(word);
	 * boolean param_3 = obj.startsWith(prefix);
	 */
}
