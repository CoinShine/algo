package com.shine.bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * 深度优先搜索 + 子树计数
 * 复杂度分析：
 * 时间复杂度：O(N)，其中 N 是树中的节点个数。
 * 空间复杂度：O(N)
 * @author shine
 * @date 2019/11/16 22:19
 * @version 1.0
 */
public class Test834 {


	/**
	 * 我们设两个节点为 x 和 y，它们在树中有一条边直接相连。如果将这条边删除，会得到一棵以 x 为根节点的子树 X 以及一棵以 y 为根节点的子树 Y。
	 *
	 * 如上图所示，ans[x] 的值由三部分组成。第一部分是子树 X 中所有节点到 x 的总距离，记为 x@X；第二部分是子树 Y 中所有节点到 y 的距离，记为 y@Y；
	 * 第三部分是子树 Y 中所有节点从 y 到达 x 的总距离，它等于 Y 中的节点个数，记为 #(Y)。将这三部分进行累加，得到 ans[x] = x@X + y@Y + #(Y)。
	 * 同理我们有 ans[y] = y@Y + x@X + #(X)，因此 ans[x] - ans[y] = #(Y) - #(X)。
	 *
	 * 我们指定 0 号节点为树的根节点，对于每个节点 node，设 S(node) 为以 node 为根的子树（包括 node 本身）。
	 * 我们用 count[node] 表示 S(node) 中节点的个数，并用 stsum[node]（subtree sum，子树和）表示 S(node) 中所有节点到 node 的总距离。
	 *
	 * 我们可以使用深度优先搜索计算出所有的 count 和 stsum。对于节点 node，我们计算出它的每个子节点的 count 和 stsum 值，
	 * 那么就有 count[node] = sum(count[child]) + 1 以及 stsum[node] = sum(stsum[child] + count[child])，其中 sum() 表示对子节点进行累加。
	 *
	 * 当所有节点计算完之后，对于根节点，它的答案 ans[root] 即为 stsum[root]。我们再进行一次深度优先搜索，并且根据上文推出的两个相邻节点 ans 值的关系，
	 * 得到其它节点的 ans 值，即：对于节点 parent（父节点）以及节点 child（子节点），有 ans[child] = ans[parent] - count[child] + (N - count[child])，
	 * 与上文的 ans[y] = ans[x] - #(Y) + #(X) 相对应。
	 *
	 * 当第二次深度优先搜索结束后，我们就得到了所有节点对应的 ans 值。
	 *
	 * @param N
	 * @param edges
	 * @return
	 */
	private int[] ans, count;
	private List<List<Integer>> graph;
	public int[] sumOfDistancesInTree(int N, int[][] edges) {
		graph = new ArrayList<>();
		ans = new int[N];
		count = new int[N]; // count表示以i为根的树的节点个数
		Arrays.fill(count, 1);

		for (int i = 0; i < N; ++i)
			graph.add(new ArrayList<>());
		for (int[] edge: edges) { // 构造无向图
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		dfs(0, -1);
		dfs2(0, -1,N);
		return ans;
	}

	/**
	 * 第一次dfs 计算出根节点子树和 和 所有以孩子节点为根的子树节点的数量
	 * @param node
	 * @param parent
	 */
	public void dfs(int node, int parent) {
		for (int child: graph.get(node))
			if (child != parent) {
				dfs(child, node);
				count[node] += count[child]; // count[node] = sum(count[child]) + 1  根节点等于所有的子节点加1 初始为1
				ans[node] += ans[child] + count[child]; // 子树和等于孩子节点子树和+孩子节点
			}
	}

	/**
	 * 第二次dfs 根据根节点的子树和 还有以各孩子节点为根的子树中节点的数量计算出所有节点的子树和
	 * @param node
	 * @param parent
	 * @param n
	 */
	public void dfs2(int node, int parent,int n) {
		for (int child: graph.get(node)) {
			if (child != parent) {
				ans[child] = ans[node] - count[child] + n - count[child];  // ans[child] = ans[parent] - count[child] + (N - count[child])
				dfs2(child, node,n);
			}
		}
	}

	@Test
	public void test01(){
		int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
		int[] ints = sumOfDistancesInTree(6, edges);
	}
}
