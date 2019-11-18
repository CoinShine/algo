package com.shine.bytedance;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * description:二叉树的垂直遍历
 * 给定一个二叉树，返回其结点 垂直方向（从上到下，逐列）遍历的值。
 * 如果两个结点在同一行和列，那么顺序则为 从左到右
 * <p>
 * 输入: [3,9,8,4,0,1,7,null,null,null,2,5]（注意：0 的右侧子节点为 2，1 的左侧子节点为 5）
 * <p>
 * 3
 * /\
 * /  \
 * 9   8
 * /\  /\
 * /  \/  \
 * 4  01   7
 * /\
 * /  \
 * 5   2
 * <p>
 * 输出:
 * <p>
 * [
 * [4],
 * [9,5],
 * [3,0,1],
 * [8,2],
 * [7]
 * ]
 * 计算返回结果的size是多大，然后初始化。这个可以用给一个递归计算，方法见countChild.
 * 以root作为开始节点进行BFS开始层级遍历，这里要注意要用一个Index来对应垂直结果的下标。
 * @author shine
 * @version 1.0
 * @date 2019/11/16 22:18
 */
public class Test314 {
	private int leftMax = 0;
	private int rightMax = 0;

	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		countChild(root, 0, 0);
		// init result size.
		for (int i = 0; i < leftMax + rightMax + 1; i++) {
			ans.add(new ArrayList<>());
		}
		Map<TreeNode, Integer> node2Index = new HashMap();
		node2Index.put(root, leftMax);
		// start bfs.
		Queue<TreeNode> queue = new LinkedList();
		queue.add(root);
		while (queue.size() > 0) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode tn = queue.poll();
				int index = node2Index.get(tn);
				ans.get(index).add(tn.val);
				if (tn.left != null) {
					queue.add(tn.left);
					node2Index.put(tn.left, index - 1);
				}
				if (tn.right != null) {
					queue.add(tn.right);
					node2Index.put(tn.right, index + 1);
				}
			}
		}
		return ans;
	}

	/**
	 * 递归计算左右size
	 * @param root
	 * @param leftIndex
	 * @param rightIndex
	 */
	public void countChild(TreeNode root, int leftIndex, int rightIndex) {
		if (root == null) {
			return;
		}
		countChild(root.left, leftIndex + 1, rightIndex - 1);
		leftMax = Math.max(leftIndex, leftMax);
		rightMax = Math.max(rightMax, rightIndex);
		countChild(root.right, leftIndex - 1, rightIndex + 1);
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
