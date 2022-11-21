package com.shine.pojo;

/**
 * DESCRIPTION:二叉树
 *
 * @author shine
 * @create 2019-07-17 14:20
 */
public class BinaryTreeNode<T>{

	//节点的值
	public T val;
	//左子树
	public BinaryTreeNode left;
	//右子树
	public BinaryTreeNode right;

	public BinaryTreeNode(T val) {
		this.val = val;
	}

	public BinaryTreeNode(T val, BinaryTreeNode left, BinaryTreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "BinaryTreeNode{" +
				"val=" + val +
				", left=" + left +
				", right=" + right +
				'}';
	}
}
