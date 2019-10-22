package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * description: 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * 分析：方法一 将树进行中序遍历，判断得到的结果是否为上升序列，如果是怎为二叉搜索树 所有的节点遍历一次 时间复杂度为O(N)
 * 		方法二 递归 分别求出左右子树中最小值和最大值，比较左子树中的最大值和根节点和右子树中的最小值的关系是否满足
 * 			  left_max<=root<=right_min 时间复杂度为O(N)
 * @author shine
 * @date 2019-10-22 21:49
 * @version 1.0
 */
public class Test98 {
	/**
	 * 使用中序遍历
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		inorder(result,root);
		List<Integer> temp = new ArrayList<>(result);
		Collections.sort(result);
		return temp.equals(result) && temp.size()!=new HashSet<>(result).size();

	}

	private void inorder(List<Integer> result, TreeNode node){
		if(node == null) return ;
		inorder(result,node.left);
		result.add(node.val);
		inorder(result,node.right);
	}


	/**
	 * 使用递归
	 * @param root
	 * @return
	 */
	TreeNode prev = null;
	public boolean isValidBST2(TreeNode root) {
		return helper(root);
	}

	/**
	 * 中序遍历中只判断前驱和当前节点
	 * @param root
	 * @return
	 */
	private boolean helper(TreeNode root){
		if(root == null) return true;
		if(!helper(root.left)) return false;
		if(prev!=null&&prev.val>=root.val) return false;
		prev = root;
		return helper(root.right);
	}

	public boolean isValid(TreeNode node,Integer min,Integer max){
		if(node == null) return true;
		if(min!=null && node.val<=min) return false;
		if(max!=null && node.val >= max) return false;
		return isValid(node.left,min, node.val) && isValid(node.right,node.val,max);
	}

	public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }

   @Test
	public void test01(){
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(1);
		TreeNode c = new TreeNode(4);
		TreeNode d = new TreeNode(3);
		TreeNode e = new TreeNode(6);

		a.left = b;
		a.right = c;
		c.left = d;
		c.right = e;

	   boolean validBST = isValidBST2(a);
	   System.out.println(validBST);

   }

}
