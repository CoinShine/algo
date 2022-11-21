package com.shine.advanced.leetcode;

/**
 * description: 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * @author shine
 * @version 1.0
 * @date 2019/9/29 15:08
 */
public class Test208 {

	private TrieNode root;

	public Test208() {
		root = new TrieNode();
	}

	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(node.children[c-'a']==null){
				node.children[c-'a'] = new TrieNode();
			}
			node = node.children[c-'a'];
		}
		node.isEnd = true;
	}

	public boolean search(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (node.children[c-'a']!=null) {
				node = node.children[c-'a'];
			} else {
				return false;
			}
		}
		return node.isEnd;

	}

	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if (node.children[c-'a']!=null) {
				node = node.children[c-'a'];
			} else {
				return false;
			}
		}
		return true;
	}


	/**
	 * 构造字典树 只包含小写字母的字典树，最多包含26个分支
	 */

	class TrieNode {
		private TrieNode[] children;
		private boolean isEnd;

		public TrieNode() {
			children = new TrieNode[26];
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
