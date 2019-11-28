package com.shine.leetcode;

import com.shine.pojo.TreeNode;
import org.junit.Test;

import java.util.Stack;

/**
 * description:二叉搜索树中第K小的元素
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * 序遍历序列为递增序列，因此可中序遍历二叉搜索树，返回第K个元素
 * 时间复杂度：O(N)，N为树中节点个数。
 * 空间复杂度：O(log(N))
 * @author shine
 * @date 2019/11/11 16:15
 * @version 1.0
 */
public class Test230 {
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
		int res = 0;
		while (root!=null || !stack.isEmpty()){
			while (root!=null){
				stack.add(root);
				root = root.left;
			}
			root = stack.pop();
			res+=1;
			if(res == k) return root.val;
			root=root.right;
		}
		return 0;
	}

	private int res = 0;
	private int count = 0;
	public int kthSmallest1(TreeNode root, int k) {
		inOrder(root,k);
		return res;
	}

	public void inOrder(TreeNode root,int k){
		if(root == null) return;
		inOrder(root.left,k);
		count++;
		if(count == k) res=root.val;
		inOrder(root.right,k);
	}


	@Test
	public void test01(){
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(1);
		TreeNode c = new TreeNode(4);
		TreeNode d = new TreeNode(2);
		a.left = b;
		a.right = c;
		b.right = d;
		inOrder(a,3);
		System.out.println(res);
	}
}
