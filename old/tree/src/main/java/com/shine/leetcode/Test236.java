package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:二叉树最近公共祖先
 * 两个结果 x和y的最近公共祖先z，满足在树上离根最远，并且x和y都是z的子孙
 * <p>
 * 分析：方法一  即求x节点路径和y节点路径中最后一个相同节点 查找路径需要多次遍历 时间复杂度为O(N),但是效率稍微低一些
 * 方法二  使用递归的方式 递归查找左右子树 P或Q是否存在 如果存在则返回
 *
 * @author Shine
 * @create 2019/9/17 11:11
 */
public class Test236 {


	/*Definition for a binary tree node.*/
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}


	private List<List<TreeNode>> re = new ArrayList<>();
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode result = root;
		preOrder(root, p, new ArrayList<>()); // 查找p节点的路径，由于Java中List复制是浅拷贝，所以使用result保存结果
		preOrder(root, q, new ArrayList<>()); // 查找q节点的路径
		int shortLen = Math.min(re.get(0).size(), re.get(1).size()); // 对路径短的遍历
		for (int i = 0; i < shortLen; i++) {
			if (re.get(0).get(i).equals(re.get(1).get(i))) {
				result = re.get(0).get(i);
			}
		}
		return result;
	}


	/**
	 * 深度优先搜索
	 *
	 * @param root
	 * @param search
	 * @param path
	 */
	private void preOrder(TreeNode root, TreeNode search, List<TreeNode> path) {
		// 终止条件
		if (root == null) return;

		// process
		path.add(root);
		if (root.equals(search)) {
			re.add(new ArrayList<>(path));  // 因为path是引用所以需要额外的list保存路径
			return;
		}

		// 下一层
		preOrder(root.left, search, path);
		preOrder(root.right, search, path);

		// 因为这里是引用，所以需要清理
		path.remove(path.size() - 1);
	}

	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) return root;
		TreeNode left = lowestCommonAncestor2(root.left, p, q); // 遍历左子树
		TreeNode right = lowestCommonAncestor2(root.right, p, q); //遍历右子树
		return left == null ? right : right == null ? left : root;
	}

	@Test
	public void test01() {
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(5);
		TreeNode c = new TreeNode(1);
		TreeNode d = new TreeNode(6);
		TreeNode e = new TreeNode(2);
		TreeNode f = new TreeNode(0);
		TreeNode g = new TreeNode(8);
		TreeNode h = new TreeNode(7);
		TreeNode i = new TreeNode(4);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		e.left = h;
		e.right = i;

		TreeNode x = new TreeNode(11);
		TreeNode y = new TreeNode(12);

		TreeNode treeNode = lowestCommonAncestor(a, b, i);
		System.out.println(treeNode.toString());
	}
}
