package com.shine.leetcode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * description: 给定两个单词（beginWord 和 endWord）和一个字典，
 * 找到从 beginWord 到 endWord 的最短转换序列的长度。
 * 转换需遵循如下规则：
 * 每次转换只能改变一个字母。转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 1.如果不存在这样的转换序列，返回 0。
 * 2.所有单词具有相同的长度。
 * 3.所有单词只由小写字母组成。
 * 4.字典中不存在重复的单词。
 * 5.你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * <p>
 * 单词与单词之间的转换，可以理解为一张图，图的顶点为单词，如果两个单词可以互相转换
 * 那么两个单词所代表的顶点之间有一条边，从begin到end所有路径中最短的，即为广度优先搜索
 *
 * @author shine
 * @version 1.0
 * @date 2019/9/24 16:23
 */
public class Test127 {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Map<String, List<String>> graph = new HashMap<>();
		constructGraph(beginWord, wordList, graph);
		return BFS_graph(beginWord, endWord, graph);
	}

	private int BFS_graph(String beginWord, String endWord, Map<String, List<String>> graph) {
		Queue<Node> queue = new LinkedList<>(); // 定义搜索队列
		Set<String> visit = new HashSet<>(); // 定义已经访问的节点
		queue.add(new Node(beginWord, 1)); // 添加起始点，起始步数为1
		visit.add(beginWord); // 将起始点添加到访问列表
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			String word = node.word;
			int step = node.step;
			if (word.equals(endWord))
				return step;
			List<String> neighbors = graph.get(word); // 获取相邻节点集合
			for (int i = 0; i < neighbors.size(); i++) {
				if (!visit.contains(neighbors.get(i))) {
					visit.add(neighbors.get(i));
					queue.add(new Node(neighbors.get(i), step + 1));
				}
			}
		}
		return 0;
	}

	private boolean connect(String word1, String word2) {
		int count = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i))
				count++;
		}
		return count == 1;
	}

	/**
	 * @param beginWord
	 * @param wordList  单词词典
	 * @param graph     Map<单词节点，相邻节点单词列表>
	 */
	private void constructGraph(String beginWord, List<String> wordList, Map<String, List<String>> graph) {
		wordList.add(beginWord);
		for (int i = 0; i < wordList.size(); i++) {
			graph.put(wordList.get(i), new ArrayList<>());
		}
		for (int i = 0; i < wordList.size(); i++) {
			for (int j = i + 1; j < wordList.size(); j++) {
				if (connect(wordList.get(i), wordList.get(j))) {
					List<String> list_i = graph.get(wordList.get(i));
					list_i.add(wordList.get(j));
					graph.put(wordList.get(i), list_i);
					List<String> list_j = graph.get(wordList.get(j));
					list_j.add(wordList.get(i));
					graph.put(wordList.get(j), list_j);
				}
			}
		}
	}
	public class Node {
		String word;
		int step;

		Node(String word, int step) {
			this.word = word;
			this.step = step;
		}
	}

	@Test
	public void test01() {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		System.out.println(ladderLength(beginWord,endWord,wordList));
	}
}
