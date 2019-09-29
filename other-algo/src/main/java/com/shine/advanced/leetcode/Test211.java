package com.shine.advanced.leetcode;

import org.junit.Test;

/**
 * description: 添加和查找单词
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 *
 * 示例:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 说明:
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 * @author shine
 * @date 2019/9/29 15:46
 * @version 1.0
 */
public class Test211 {
	private TrieNode root;
	/** Initialize your data structure here. */
	public Test211() {
		root = new TrieNode();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(!node.contains(c)){
				node.setTrie(c,new TrieNode());
			}
			node=node.get(c);
		}
		node.setEnd();
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		return search_trie(root,word);
	}

	private  boolean search_trie(TrieNode node,String word){
		if("".equals(word)){
			return node.isEnd();
		}
		char c = word.charAt(0);
		if(c == '.'){
			for (int i = 0; i < 26; i++) {
				if(node.child[i] != null && search_trie(node.child[i],word.substring(1))){
					return true;
				}
			}
		}else{
			if(node.contains(c)&&search_trie(node.get(c),word.substring(1))){
				return true;
			}
		}
		return false;
	}

	class TrieNode{
		private final int TRIE_NUMS = 26;
		private TrieNode[] child ;
		private boolean is_end;
		public TrieNode(){
			child = new TrieNode[TRIE_NUMS];
		}

		private void setTrie(char ch, TrieNode node){
			child[ch-'a'] = node;
		}

		private TrieNode get(char ch){
			return child[ch-'a'];
		}

		private boolean contains(char ch){
			return child[ch-'a'] != null;
		}

		private void setEnd(){
			this.is_end = true;
		}

		private boolean isEnd(){
			return is_end;
		}
	}

	@Test
	public void test01(){
		Test211 test211 = new Test211();
		test211.addWord("bad");
		test211.addWord("dad");
		test211.addWord("mad");
		System.out.println(test211.search("pad"));
		System.out.println(test211.search("bad"));
		System.out.println(test211.search(".ad"));
		System.out.println(test211.search("b.."));

	}



}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
