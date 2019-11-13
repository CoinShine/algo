package com.shine.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
 * @author shine
 * @date 2019/11/13 12:12
 * @version 1.0
 */
public class Test269 {
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

			for (int j = 0; j < Math.max(w1.length(), w2.length()); j++) {
				Character c1 = j < n1 ? w1.charAt(j) : null;
				Character c2 = j < n2 ? w2.charAt(j) : null;

				if (c1 != null && !adjList.containsKey(c1)) {
					adjList.put(c1, new ArrayList<Character>());
				}

				if (c2 != null && !adjList.containsKey(c2)) {
					adjList.put(c2, new ArrayList<Character>());
				}

				if (c1 != null && c2 != null && c1 != c2 && !found) {
					adjList.get(c1).add(c2);
					found = true;
				}
			}
		}

		Set<Character> visited = new HashSet<>();
		Set<Character> loop = new HashSet<>();
		Stack<Character> stack = new Stack<Character>();

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
	// 将当前节点 u 加入到 visited 集合以及 loop 集合中。
	private boolean topologicalSort(Map<Character, List<Character>> adjList, char u,
							Set<Character> visited, Set<Character> loop, Stack<Character> stack) {
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
}
