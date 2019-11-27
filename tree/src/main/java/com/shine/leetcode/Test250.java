package com.shine.leetcode;

import com.shine.pojo.TreeNode;
import org.junit.Test;

/**
 * description: 统计同值子树
 * 给定一个二叉树，统计该二叉树数值相同的子树个数。
 *
 * 同值子树是指该子树的所有节点都拥有相同的数值。
 *
 * 示例：
 *
 * 输入: root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * 输出: 4
 *
 * 官方解法
 * 深度优先搜索 ：
 * 给定树中的一个结点，若其满足下面条件中的一个，则子树同值:
 *   1、该节点没有子结点 （基本情况）
 *   2、该节点的所有子结点都为同值子树，且结点与其子结点值相同。
 * 这样我们可以在树中使用深度优先搜索，自底向上的判断每个子树是否为同值子树
 *	复杂度分析
 *
 * 时间复杂度 : O(N)。由于算法的深度优先特性，每个结点的 is_uni 状态是自底向上计算的。给定子结点的is_uni ，
 * 计算结点自身的 is_uni 只需要 O(1) 的时间。故每个结点需要 O(1) 时间，总复杂度为 O(N)。
 *
 * 空间复杂度 : O(H)， 其中 H 为树的高度。每次 is_uni 递归调用都需要栈空间。由于我们在调用 is_uni(node.right) 前
 * 会先处理完 is_uni(node.left)，递归栈的大小由从根到叶的最长路径决定 - 换而言之，是树的高度。
 * @author shine
 * @date 2019/11/11 15:45
 * @version 1.0
 */
public class Test250 {
	private int count = 0;
	public int countUnivalSubtrees(TreeNode root) {
		if (root == null) return 0;
		dfs(root);
		return count;
	}

	public boolean dfs(TreeNode node){
		if (node.left == null && node.right == null) { // 如果节点没有子节点
			count++;
			return true;
		}
		boolean flag = true;

		// 判断节点的子节点是不是同值子树，递归左右子树
		if (node.left != null) {
			flag = dfs(node.left) && flag && node.left.val == node.val;
		}

		if (node.right != null) {
			flag = dfs(node.right) && flag && node.right.val == node.val;
		}
		// 如果不是同值子树返回false
		if (!flag) return false;
		count++;
		return true;
	}


	@Test
	public void test01(){
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(1);
		TreeNode c = new TreeNode(5);
		TreeNode d = new TreeNode(5);
		TreeNode e = new TreeNode(5);
		TreeNode f = new TreeNode(5);

		a.left = b;
		a.right = c;
		//b.left = d;
		//b.right = e;
		//c.right = f;

		int i = countUnivalSubtrees(a);
		System.out.println(i);
	}
}
