package com.shine.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description:树中距离之和
 * 给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
 * 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
 * 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
 *
 * 示例 1:
 *
 * 输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * 输出: [8,12,6,10,10,10]
 * 解释:
 * 如下为给定的树的示意图：
 *   0
 *  / \
 * 1   2
 *    /|\
 *   3 4 5
 *
 * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推
 *
 * 复杂度分析：
 * 时间复杂度：O(N)，其中 N 是树中的节点个数。
 * 空间复杂度：O(N)
 * @author shine
 * @date 2019/11/16 22:19
 * @version 1.0
 */
public class Test834 {
	int[] ans, count;
	List<Set<Integer>> graph;
	int N;
	public int[] sumOfDistancesInTree(int N, int[][] edges) {
		this.N = N;
		graph = new ArrayList<Set<Integer>>();
		ans = new int[N];
		count = new int[N];
		Arrays.fill(count, 1);

		for (int i = 0; i < N; ++i)
			graph.add(new HashSet<Integer>());
		for (int[] edge: edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		dfs(0, -1);
		dfs2(0, -1);
		return ans;
	}

	public void dfs(int node, int parent) {
		for (int child: graph.get(node))
			if (child != parent) {
				dfs(child, node);
				count[node] += count[child];
				ans[node] += ans[child] + count[child];
			}
	}

	public void dfs2(int node, int parent) {
		for (int child: graph.get(node))
			if (child != parent) {
				ans[child] = ans[node] - count[child] + N - count[child];
				dfs2(child, node);
			}
	}
}
