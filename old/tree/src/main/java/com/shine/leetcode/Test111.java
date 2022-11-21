package com.shine.leetcode;

import com.shine.pojo.TreeNode;

/**
 * description:求二叉树的最小深度
 * @author shine
 * @date 2019/10/31 20:44
 * @version 1.0
 */
public class Test111 {
	public int minDepth(TreeNode root) {
		if(root == null) return 0;
		if(root.left == null) return minDepth(root.right)+1;
		if(root.right == null) return minDepth(root.left)+1;
		return Math.min(minDepth(root.left),minDepth(root.right))+1;
	}
}
