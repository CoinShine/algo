package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * DESCRIPTION:二叉树右视图 （考虑下左视图怎么写）
 * 借助队列使用广搜，输入每层最后一个节点
 *
 * @author Shine
 * @create 2019/9/17 16:59
 */
public class Test199 {
	private Queue<TreeNode> nodeQueue = new LinkedList<>(); // 定义队列，存储广搜结果
	private Queue<Integer> depthQueue = new LinkedList<>(); // 定义广搜每个节点对应的层数

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root != null) {
			nodeQueue.add(root);
			depthQueue.add(0);
		}
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.poll(); // nodeQueue和depthQueue是一一对应的
			int depth = depthQueue.poll();

			if (result.size() == depth) { // 遍历每层第一节点时放入
				result.add(node.val);
			} else {
				result.set(depth, node.val); // 将每层右边的节点更新到结果中
			}
			if (node.left != null) {
				nodeQueue.add(node.left);
				depthQueue.add(depth + 1);
			}
			if (node.right != null) {
				nodeQueue.add(node.right);
				depthQueue.add(depth + 1);
			}
		}
		return result;
	}

	@Test
	public void test01() {
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(5);
		TreeNode e = new TreeNode(4);
		a.left = b;
		a.right = c;
		b.right = d;
		c.right = e;
		List<Integer> integers = rightSideView(a);
		System.out.println(integers.toString());
	}


	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
