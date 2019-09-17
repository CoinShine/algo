package com.shine.leetcode;

import org.junit.Test;

/**
 * DESCRIPTION:翻转二叉树
 *
 * @author Shine
 * @create 2019/9/17 15:10
 */
public class Test261 {

	public TreeNode invertTree(TreeNode root) {
		if(root == null) return null;
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.right =left;
		root.left = right;
		return root;
	}
	@Test
	public void test01(){
		TreeNode a = new TreeNode(4);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(7);
		a.left = b;
		a.right =c;
		invertTree(a);

	}
	/**
	  Definition for a binary tree node.
	 */
	  public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }

}
