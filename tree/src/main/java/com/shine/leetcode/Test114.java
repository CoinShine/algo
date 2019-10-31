package com.shine.leetcode;

import org.junit.Test;

/**
 * DESCRIPTION:二叉树原地转链表
 * 单链表仍然使用树的结构，left=null right=next
 * @author Shine
 * @create 2019/9/17 15:48
 */
public class Test114 {
	public void flatten(TreeNode root) {

		while (root != null) {
			//左子树为 null，直接考虑下一个节点
			if (root.left == null) {
				root = root.right;
			} else {
				// 找左子树最右边的节点
				TreeNode pre = root.left;
				while (pre.right != null) {
					pre = pre.right;
				}
				//将原来的右子树接到左子树的最右边节点
				pre.right = root.right;
				// 将左子树插入到右子树的地方
				root.right = root.left;
				root.left = null;
				// 考虑下一个节点
				root = root.right;
			}
		}
	}


	@Test
	public void test01(){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(5);
		TreeNode d = new TreeNode(3);
		TreeNode e = new TreeNode(4);
		TreeNode f = new TreeNode(6);
		a.left=b;
		a.right=c;
		//b.left=d;
		//b.right=e;
		//c.right=f;
		flatten(a);
	}
	/**
	 * Definition for a binary tree node.
	 */
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}




