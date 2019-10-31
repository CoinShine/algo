package com.shine.leetcode;
import com.shine.pojo.TreeNode;

/**
 * description: 求二叉树的最大深度
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  分析：求二叉树的最大深度和最小深度，可以用BFS和DFS
 *  BFS 遍历每一层时 判断是否为叶子节点 第一个为叶子节点的就是最小深度，最后一个为叶子节点的就是最大深度
 *  DFS 遍历时 遇到叶子节点更新最小值或最大值 记录深度
 *  时间复杂度都是O(N)
 * @author shine
 * @date 2019/10/31 20:31
 * @version 1.0
 */
public class Test104 {
	public int maxDepth(TreeNode root) {
		return root == null ? 0:Math.max(maxDepth(root.left),maxDepth(root.right))+1;
	}
}
