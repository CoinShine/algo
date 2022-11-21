package com.shine.leetcode;

import com.shine.pojo.TreeNode;

/**
 * description: 求二叉查找树最近公共祖先
 * 详见236
 * @author shine
 * @date 2019-10-23 21:48
 * @version 1.0
 */
public class Test235 {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null) return root;
		if(p.val<root.val&&q.val<root.val)
			return lowestCommonAncestor(root.left,p,q);
		else if(p.val>root.val&&q.val>root.val)
			return lowestCommonAncestor(root.right,p,q);
		else
			return root;
	}

	/**
	 * 非递归写法
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		while(root!=null){
			if(root.val>p.val&&root.val>q.val){
				root = root.left;
			}else if(root.val<p.val&&root.val<q.val){
				root = root.right;
			}else{
				return root;
			}
		}
		return null;
	}
}
