package com.shine.advanced.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DESCRIPTION:（79题）单词搜索
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 示例:
 *
 * 输入:
 * words = ['oath','pea','eat','rain'] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * 输出: ['eat','oath']
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 *
 * 提示:
 *
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？
 * 散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 * 分析：先将words构造成trie树，然后dfs 二维网格
 * @author Shine
 * @create 2019/11/2 14:42
 */
public class Test212 {
	Set<String> res= new HashSet<>();
	public List<String> findWords(char[][] board, String[] words) {
		Test208 trie = new Test208();
		for (String word : words) {
			trie.insert(word);
		}
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dfs(board,visited,"",i,j,trie);
			}
		}
		return new ArrayList<>(res);
	}

	private void dfs(char[][] board, boolean[][] visited, String str, int i, int j, Test208 trie) {
		if(i < 0 || i >= board.length || j < 0|| j >= board[0].length) return;
		if(visited[i][j]) return;
		str += board[i][j];
		if(!trie.startsWith(str)) return;

		if(trie.search(str)) res.add(str);
		visited[i][j] = true;
		dfs(board,visited,str,i-1,j,trie);
		dfs(board,visited,str,i+1,j,trie);
		dfs(board,visited,str,i,j-1,trie);
		dfs(board,visited,str,i,j+1,trie);
		visited[i][j] = false; // 回溯
	}
	
	@Test
	public void test01() {
		String[] words = {"oath", "pea", "eat", "rain"};
		char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
		List<String> lis = findWords(board, words);
		System.out.println(lis.toString());
	}

}
