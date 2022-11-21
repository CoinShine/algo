package com.shine.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

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

	public int maxDepth1(TreeNode root){
		Queue<TreeNode> queue = new LinkedList<>();
		Queue<Integer> level = new LinkedList<>();
		int depth =1;
		int maxLength = 0;
		if(root == null) return maxLength;
		queue.add(root);
		level.add(depth);
		while (!queue.isEmpty()){
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode currentNode = queue.poll();
				Integer lev = level.poll();
				lev++;
				if(currentNode.left!=null){
					queue.add(currentNode.left);
					level.add(lev);
				}
				if(currentNode.right!=null){
					queue.add(currentNode.right);
					level.add(lev);
				}
				if(currentNode.left==null&&currentNode.right==null){
					maxLength = Math.max(maxLength,--lev);
				}

			}
		}
		return maxLength;
	}


	@Test
	public void test01(){
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(9);
		TreeNode c = new TreeNode(20);
		TreeNode d = new TreeNode(15);
		TreeNode e = new TreeNode(7);
		a.left = b;
		a.right = c;
		c.left = d;
		c.right = e;
		int i = maxDepth1(a);
		System.out.println(i);
	}

	 class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
		TreeNode(int x) {
			val = x;
		}

	}

}

