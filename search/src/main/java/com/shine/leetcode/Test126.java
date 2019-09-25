package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * description: 单词接龙2
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 输出从beginWord到endWord所有的最短路径 使用广度优先搜索记录路径
 *
 * @author shine
 * @version 1.0
 * @date 2019/9/25 15:30
 */
public class Test126 {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		Map<String,List<String>>  graph = new HashMap<>();
		LinkedList<Item> queue = new LinkedList<>();
		List<Integer> endWordIndex = new ArrayList<>(); // endWord在搜索队列的位置
		constructGraph(beginWord,wordList,graph);
		BFS_graph(beginWord,endWord,graph,queue,endWordIndex);
		List<List<String>> result=new ArrayList<>();
		for (int i = 0; i < endWordIndex.size(); i++) { // size有多大，就有多少条路径
			int pre = endWordIndex.get(i); // 当前路径中endWord的前驱
			List<String> path = new ArrayList<>();
			while (pre!=-1){
				path.add(queue.get(pre).node); // 将节点从后像前遍历
				pre = queue.get(pre).pre; // 找到前驱节点
			}
			List<String> re = new ArrayList<>();
			for (int j = path.size()-1; j >=0 ; j--) {
				re.add(path.get(j)); // 将endWord到beginWord的顺序翻转
			}
			result.add(re);
		}
		return result;
	}


	private void BFS_graph(String beginWord, String endWord, Map<String, List<String>> graph, LinkedList<Item> linkedList, List<Integer> endWordIndex) {
		Map<String, Integer> visit = new HashMap<>(); // 单词 步数
		linkedList.add(new Item(beginWord, 1, -1));
		int min_step = 0; // 达到endWord的最小步数
		visit.put(beginWord, 1);
		int front = 0; // 队列头指针
		while (front != linkedList.size()) { //当front等于size时，队列为空
			String node = linkedList.get(front).node; // 获取队头元素
			int step = linkedList.get(front).step;
			if (min_step != 0 && step > min_step) {
				break; // 代表所有最短路径已经搜索完成
			}
			if (node.equals(endWord)) {
				min_step = step; // 达到endWord
				endWordIndex.add(front);
			}
			List<String> neighbors = graph.get(node);
			for (int i = 0; i < neighbors.size(); i++) {
				if (!visit.containsKey(neighbors.get(i)) || visit.get(neighbors.get(i)) == step + 1) { // 没被访问过或另外的最短路径
					linkedList.add(new Item(neighbors.get(i), step+1, front));
					visit.put(neighbors.get(i), step + 1); // 标记到达邻接点neighbors[i] 的最小步数
				}
			}
			front++;
		}
	}


	private void constructGraph(String beginWord, List<String> wordList, Map<String, List<String>> graph) {
		int begin_word_has_been = 0; // 判断beginWord是否在wordList中出现过
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).equals(beginWord)) begin_word_has_been = 1;
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
			if (begin_word_has_been == 0 && connect(beginWord, wordList.get(i))) {
				List<String> list = graph.get(beginWord);
				if(list==null) list=new ArrayList<>();
				list.add(wordList.get(i));
				graph.put(beginWord, list);
			}
		}
	}

	private boolean connect(String word1, String word2) {
		int count = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i))
				count++;
		}
		return count == 1;
	}

	public class Item {
		private String node; // 当前节点存储的单词
		private int step; // 当前步数
		private int pre; // 前驱节点在队列中的位置

		private Item(String node, int step, int pre) {
			this.node = node;
			this.step = step;
			this.pre = pre;
		}
	}

	@Test
	public void test01(){
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		System.out.println(findLadders(beginWord,endWord,wordList));
	}
}
