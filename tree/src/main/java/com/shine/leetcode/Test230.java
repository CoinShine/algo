package com.shine.leetcode;

import com.shine.pojo.TreeNode;

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
		TreeNode p = root;
		int res = 0;
		while (p!=null || !stack.isEmpty()){
			while (p!=null){
				stack.add(p);
				p = p.left;
			}
			p = stack.pop();
			res+=1;
			if(res == k) return p.val;
			p=p.right;
		}
		return 0;
	}
}
