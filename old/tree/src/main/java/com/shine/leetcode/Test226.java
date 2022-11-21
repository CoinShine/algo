package com.shine.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * DESCRIPTION:翻转二叉树
 *
 * @author Shine
 * @create 2019/9/17 15:10
 */
public class Test226 {

	/**
	 * 递归实现
	 * @param root
	 * @return
	 */
	public TreeNode invertTree(TreeNode root) {
		if(root == null) return null;
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.right =left;
		root.left = right;
		return root;
	}

	/**
	 * 使用队列 广搜 迭代实现
	 * @param root
	 * @return
	 */
	public TreeNode invertTree2(TreeNode root) {
		if(root == null) return null;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()){
			TreeNode treeNode = queue.poll();
			TreeNode temp = treeNode.left;
			treeNode.left = treeNode.right;
			treeNode.right = temp;
			if(treeNode.left!=null) queue.offer(treeNode.left);
			if(treeNode.right!=null) queue.offer(treeNode.right);
		}
		return root;
	}


	@Test
	public void test01(){
		TreeNode a = new TreeNode(4);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(7);
		a.left = b;
		a.right =c;
		TreeNode treeNode = invertTree2(a);
		System.out.println(treeNode);

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
