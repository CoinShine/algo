package com.shine.bytedance;

/**
 * description:将二叉树转为双向链表(就地)
 *
 * 标准的中序遍历采用 左 -> 根 -> 右 的顺序，其中 左 和 右 的部分调用递归。
 *
 * 本题的处理在于将前一个结点与当前结点链接，因此，必须跟踪最后一个结点，该结点在新的双向链表中是当前最大的
 * 另外一个细节：我们也需要保留第一个，也就是最小的结点，以完成闭环
 * 具体算法：
 * 	1、将 first 和 last 结点 初始化为 null。
 * 	2、调用标准中序遍历 helper(root) :
 * 		若结点不为 null :
 * 			调用左子树递归 helper(node.left)。
 * 			若 last 结点不为空，将 last 与当前的 node 链接。
 * 			否则初始化 first 结点。
 * 			将当前结点标记为最后 : last = node.
 * 			调用右子树递归 helper(node.right)。
 * 	3、将最前与最后的结点链接完成闭环，返回 first 。
 * @author shine
 * @date 2019/11/16 22:19
 * @version 1.0
 */
public class Test426 {
	// the smallest (first) and the largest (last) nodes
	Node first = null;
	Node last = null;

	public void helper(Node node) {
		if (node != null) {
			// left
			helper(node.left);
			// node
			if (last != null) {
				// link the previous node (last)
				// with the current one (node)
				last.right = node;
				node.left = last;
			} else {
				// keep the smallest node
				// to close DLL later on
				first = node;
			}
			last = node;
			// right
			helper(node.right);
		}
	}

	public Node treeToDoublyList(Node root) {
		if (root == null) return null;

		helper(root);
		// 将最后一个节点和第一个节点相连
		last.right = first;
		first.left = last;
		return first;
	}


	class Node {
		public int val;
		public Node left;
		public Node right;

		public Node() {
		}

		public Node(int _val, Node _left, Node _right) {
			val = _val;
			left = _left;
			right = _right;
		}
	}
}
