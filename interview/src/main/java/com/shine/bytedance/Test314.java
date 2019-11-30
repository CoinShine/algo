package com.shine.bytedance;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * description:二叉树的垂直遍历
 * 给定一个二叉树，返回其结点 垂直方向（从上到下，逐列）遍历的值。
 * 如果两个结点在同一行和列，那么顺序则为 从左到右
 * 输入: [3,9,8,4,0,1,7,null,null,null,2,5]（注意：0 的右侧子节点为 2，1 的左侧子节点为 5）
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
 * 输出:
 * [
 * [4],
 * [9,5],
 * [3,0,1],
 * [8,2],
 * [7]
 * ]
 * 使用BFS+有序Hash表实现，首先定义跟节点index为0，然后下一层left为-1，right为1，以此类推
 *
 * @author shine
 * @version 1.0
 * @date 2019/11/16 22:18
 */
public class Test314 {



	public List<List<Integer>> verticalOrder(TreeNode root) {
		if (root == null) return new ArrayList<>();
		Queue<QueueNode> queue = new LinkedList<>();
		queue.add(new QueueNode(0, root));
		Map<Integer, List<Integer>> map = new TreeMap<>();
		List<Integer> re;
		while (!queue.isEmpty()) {
			QueueNode node = queue.poll();
			if (map.containsKey(node.index)) {
				List<Integer> list = map.get(node.index);
				list.add(node.node.val);
				map.put(node.index,list);
			} else {
				re = new ArrayList<>();
				re.add(node.node.val);
				map.put(node.index, re);
			}
			if (node.node.left != null) {
				queue.add(new QueueNode(node.index - 1, node.node.left));
			}

			if (node.node.right != null) {
				queue.add(new QueueNode(node.index + 1, node.node.right));
			}
		}
		return new ArrayList<>(map.values());

	}

	class QueueNode {
		int index;
		TreeNode node;

		public QueueNode(int index, TreeNode node) {
			this.index = index;
			this.node = node;
		}
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
