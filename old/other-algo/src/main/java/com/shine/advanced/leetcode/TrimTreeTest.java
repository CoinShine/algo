package com.shine.advanced.leetcode;

import org.junit.Test;

/**
 * description: 字典树练习
 * 定义只有小写字母的字典树
 *
 * @author shine
 * @version 1.0
 * @date 2019-09-28 22:45
 */
public class TrimTreeTest {
	final int TRIM_MAX_CHAR_NUM = 26;

	@Test
	public void test01() {
		TrieNode root = new TrieNode();
		TrieNode[] child = root.child;
		TrieNode n1 = new TrieNode();
		TrieNode n2 = new TrieNode();
		TrieNode n3 = new TrieNode();
		root.child['a' - 'a'] = n1;
		root.child['b' - 'a'] = n2;
		root.child['c' - 'a'] = n3;
		n2.is_end = true;
		TrieNode n4 = new TrieNode();
		TrieNode n5 = new TrieNode();
		TrieNode n6 = new TrieNode();
		n1.child['b' - 'a'] = n4;
		n2.child['c' - 'a'] = n5;
		n3.child['f' - 'a'] = n6;

		TrieNode n7 = new TrieNode();
		TrieNode n8 = new TrieNode();
		TrieNode n9 = new TrieNode();
		n4.child['c' - 'a'] = n7;
		n4.child['d' - 'a'] = n8;
		n5.child['d' - 'a'] = n9;

		TrieNode n10 = new TrieNode();
		n6.child['g' - 'a'] = n10;
		TrieNode n11 = new TrieNode();
		n7.child['d' - 'a'] = n11;
		n7.is_end = true;
		n8.is_end = true;
		n9.is_end = true;
		n10.is_end = true;
		n11.is_end = true;
	}

	public class TrieNode {
		TrieNode[] child = new TrieNode[TRIM_MAX_CHAR_NUM]; // 设置字典树孩子节点的长度
		boolean is_end;

		public TrieNode() {
			is_end = false;
		}
	}
}
