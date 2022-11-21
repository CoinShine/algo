package com.shine.geektime;

import org.junit.Test;

/**
 * description: 二叉查找树插入删除操作
 * @author shine
 * @date 2019/08/29 15:38
 * @version 1.0
 */
public class BinaryTreeTest {
	private TreeNode tree;
	private int hight =1;
	public void delete(int data) {
		TreeNode p = tree; // p 指向要删除的节点，初始化指向根节点
		TreeNode pp = null; // pp 记录的是 p 的父节点
		while (p != null && p.data != data) {
			pp = p;
			if (data > p.data) p = p.right;
			else p = p.left;
		}
		if (p == null) return; // 没有找到

		// 要删除的节点有两个子节点
		if (p.left != null && p.right != null) { // 查找右子树中最小节点
			TreeNode minP = p.right;
			TreeNode minPP = p; // minPP 表示 minP 的父节点
			while (minP.left != null) {
				minPP = minP;
				minP = minP.left;
			}
			p.data = minP.data; // 将 minP 的数据替换到 p 中
			p = minP; // 下面就变成了删除 minP 了
			pp = minPP;
		}

		// 删除节点是叶子节点或者仅有一个子节点
		TreeNode child; // p 的子节点
		if (p.left != null) child = p.left;
		else if (p.right != null) child = p.right;
		else child = null;

		if (pp == null) tree = child; // 删除的是根节点
		else if (pp.left == p) pp.left = child;
		else pp.right = child;
	}

	/**
	 * 初始化数据
	 */
	public void init(){
		tree = new TreeNode(33);
		tree.left =new TreeNode(16);
		tree.right= new TreeNode(50);
		tree.left.left= new TreeNode(13);
		tree.left.right= new TreeNode(18);
		tree.left.left.right = new TreeNode(15);
		tree.left.right.left = new TreeNode(17);
		tree.left.right.right = new TreeNode(25);
		tree.left.right.right.left = new TreeNode(19);
		tree.left.right.right.right = new TreeNode(27);
		tree.right.left = new TreeNode(34);
		tree.right.right = new TreeNode(58);
		tree.right.right.left= new TreeNode(51);
		tree.right.right.right = new TreeNode(66);
		tree.right.right.left.right = new TreeNode(55);
	}

	public static class TreeNode {
		private int data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int data) {
			this.data = data;
		}
	}

	@Test
	public void testDel(){
		init();
		//delete(18);
		//StringBuilder sb = new StringBuilder();
		//StringBuilder stringBuilder = bianLi(sb, tree);
		//System.out.println(stringBuilder.toString());

		int i = maxDepth(tree);
		System.out.println(i);
	}

	/**
	 * 中序遍历
	 * @param sb
	 * @param tree
	 * @return
	 */
	private StringBuilder bianLi(StringBuilder sb,TreeNode tree){
		if(tree == null){
			return sb;
		}
		bianLi(sb,tree.left);
		sb.append(tree.data);
		sb.append(" ");
		bianLi(sb,tree.right);
		return sb;
	}

	private int maxDepth(TreeNode tree){
		if(tree!=null){
			return Math.max(maxDepth(tree.left)+1, maxDepth(tree.right)+1);
		}
		return 0;
	}
}
