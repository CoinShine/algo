package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * description: 火星词典
 * 现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。
 *
 * 假设，您并不知道其中字母之间的先后顺序。但是，会收到词典中获得一个 不为空的 单词列表。
 * 因为是从词典中获得的，所以该单词列表内的单词已经 按这门新语言的字母顺序进行了排序。
 *
 * 您需要根据这个输入的列表，还原出此语言中已知的字母顺序。
 *
 *
 * 示例 1：
 *
 * 输入:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * 输出: "wertf"
 *  分析：拓扑排序得到正确顺序：将每个字母看成是图里的顶点，它们之间的关系就好比是连接顶点与顶点的边，
 *  而且是有向边，所以这个图是一个有向图。最后对这个有向图进行拓扑排序，就可以得出最终的结果。
 *  第一步是根据输入构建一个有向图；第二步是对这个有向图进行拓扑排序。
 *
 *  用深度优先的方法进行拓扑排序，一定要借用下面三者。
 *
 * 1、visited 集合，用来记录哪些顶点已经被访问过。
 * 2、stack 堆栈，从某个顶点出发，访问完了所有其他顶点，最后才把当前的这个顶点加入到堆栈里。
 * 即，若要该点加入到 stack 里，必须先把跟它有联系的顶点都处理完。举例说明，如果我要学习课程 A，
 * 得先把课程 B，C 以及 D 都看完。
 * 3、loop 集合，为了有效防止有向图里出现环的情况。举例说明如下。
 * 	  当每一轮访问结束后，都必须要把 loop 集合清空，才能把其他顶点也加入到堆栈里
 *
 * 第一步是根据输入构建一个有向图；第二步是对这个有向图进行拓扑排序
 * @author shine
 * @date 2019/11/13 12:12
 * @version 1.0
 */
public class Test269 {

	/**
	 * 将词典中字符串的字符两两对比，只有第一个不同的字符才是正确的排序，如ert和wrf，只能推断出e的优先级高于w，剩余字符的优先级不能推断。
	 * 将字符串的优先级构建为图，然后进行拓扑排序。如果图中无环，则将拓扑排序输出，否则顺序是非法的
	 * @param words
	 * @return
	 */
	public String alienOrder0(String[] words) {
		//1.构建图
		Map<Character, Set<Character>> map = new HashMap<>();
		for (int i = 0; i < words.length - 1; i++) {
			for (int j = 0; j < words[i].length() && j < words[i + 1].length(); j++) {
				//如果字符相同，比较下一个
				if (words[i].charAt(j) == words[i + 1].charAt(j)) continue;
				//保存第一个不同的字符顺序
				Set<Character> set = map.getOrDefault(words[i].charAt(j), new HashSet<>());
				set.add(words[i + 1].charAt(j));
				map.put(words[i].charAt(j), set);
				break;
			}
		}

		//2.拓扑排序
		//创建保存入度的数组
		int[] degrees = new int[26];
		Arrays.fill(degrees, -1);
		//注意，不是26字母都在words中出现，所以出度分为两种情况：没有出现的字母出度为-1，出现了的字母的出度为非负数
		for (String str : words) {
			//将出现过的字符的出度设定为0
			for (char c : str.toCharArray())
				degrees[c - 'a'] = 0;
		}
		for (char key : map.keySet()) {
			for (char val : map.get(key)) {
				degrees[val - 'a']++;
			}
		}
		//创建StringBuilder保存拓扑排序
		StringBuilder sb = new StringBuilder();
		//创建一个Queue保存入度为0的节点
		Queue<Character> list = new LinkedList<>();

		int count = 0;//计算图中节点数
		for (int i = 0; i < 26; i++) {
			if (degrees[i] != -1) count++;
			if (degrees[i] == 0) {
				list.add((char) ('a' + i));
			}
		}

		while (!list.isEmpty()) {
			Character cur = list.poll();
			sb.append(cur);
			//将邻接点出度-1
			if (map.containsKey(cur)) {
				Set<Character> set = map.get(cur);
				for (Character c : set) {
					degrees[c - 'a']--;
					if (degrees[c - 'a'] == 0) list.add(c);
				}
			}
		}

		//判断是否有环
		if (sb.length() != count) return "";
		else return sb.toString();
	}




	// 基本情况处理，比如输入为空，或者输入的字符串只有一个
	public String alienOrder(String[] words) {
		if (words == null || words.length == 0)
			return null;
		if (words.length == 1) {
			return words[0];
		}

		// 构建有向图：定义一个邻接链表 adjList，也可以用邻接矩阵
		Map<Character, List<Character>> adjList = new HashMap<>();

		for (int i = 0; i < words.length - 1; i++) {
			String w1 = words[i], w2 = words[i + 1];
			int n1 = w1.length(), n2 = w2.length();

			boolean found = false;

			for (int j = 0; j < Math.max(n1,n2); j++) {
				Character c1 = j < n1 ? w1.charAt(j) : null;
				Character c2 = j < n2 ? w2.charAt(j) : null;

				if (c1 != null && !adjList.containsKey(c1)) {
					adjList.put(c1, new ArrayList<>());
				}

				if (c2 != null && !adjList.containsKey(c2)) {
					adjList.put(c2, new ArrayList<>());
				}

				if (c1 != null && c2 != null && c1 != c2 && !found) {
					adjList.get(c1).add(c2);
					found = true;
				}
			}
		}

		Set<Character> visited = new HashSet<>();
		Set<Character> loop = new HashSet<>();
		Stack<Character> stack = new Stack<>();

		for (Character key : adjList.keySet()) {
			if (!visited.contains(key)) {
				if (topologicalSort(adjList, key, visited, loop, stack)) {
					return "";
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
	// 将当前节点 u 加入到 visited 集合以及 loop 集合中。 判断拓扑排序是否有环
	private boolean topologicalSort(Map<Character, List<Character>> adjList, char u, Set<Character> visited, Set<Character> loop, Stack<Character> stack) {
		visited.add(u);
		loop.add(u);

		if (adjList.containsKey(u)) {
			for (int i = 0; i < adjList.get(u).size(); i++) {
				char v = adjList.get(u).get(i);

				if (loop.contains(v))
					return true;

				if (!visited.contains(v)) {
					if (topologicalSort(adjList, v, visited, loop, stack)) {
						return true;
					}
				}
			}
		}
		loop.remove(u);
		stack.push(u);
		return false;
	}


	@Test
	public void test01(){

		String[] words = {"wrt","wrf","er","ett", "rftt"};
		String s = alienOrder(words);
		System.out.println(s);
	}
}
