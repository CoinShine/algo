package com.shine.meituan;

/**
 * description:具有所有最深结点的最小子树
 * 给定一个根为 root 的二叉树，每个结点的深度是它到根的最短距离。
 *
 * 如果一个结点在整个树的任意结点之间具有最大的深度，则该结点是最深的。
 *
 * 一个结点的子树是该结点加上它的所有后代的集合。
 *
 * 返回能满足“以该结点为根的子树中包含所有最深的结点”这一条件的具有最大深度的结点
 *
 * @author shine
 * @date 2019/11/20 12:13
 * @version 1.0
 */
public class Test865 {

	/**
	 * dfs(node) 返回的结果有两个部分：
	 *
	 * Result.node：包含所有最深节点的最小子树的根节点。
	 * Result.dist：node 到最深节点的距离。
	 * 分别计算 dfs(node) 的两个返回结果：
	 *
	 * 对于 Result.node：
	 *
	 * 如果只有一个 childResult 具有最深节点，返回 childResult.node。
	 *
	 * 如果两个孩子都有最深节点，返回 node。
	 *
	 * Result.dist 为 childResult.dist 加 1。
	 *。
	 * @param root
	 * @return
	 */
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		return dfs(root).node;
	}

	// Return the result of the subtree at this node.
	public Result dfs(TreeNode node) {
		if (node == null) return new Result(null, 0);
		Result L = dfs(node.left),
				R = dfs(node.right);
		if (L.dist > R.dist) return new Result(L.node, L.dist + 1);
		if (L.dist < R.dist) return new Result(R.node, R.dist + 1);
		return new Result(node, L.dist + 1);
	}
}

/**
 * The result of a subtree is:
 *       Result.node: the largest depth node that is equal to or
 *                    an ancestor of all the deepest nodes of this subtree.
 *       Result.dist: the number of nodes in the path from the root
 *                    of this subtree, to the deepest node in this subtree.
 */
class Result {
	TreeNode node;
	int dist;
	Result(TreeNode n, int d) {
		node = n;
		dist = d;
	}
}
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}
