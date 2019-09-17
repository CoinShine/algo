package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:二叉树最近公共祖先
 * 两个结果 x和y的最近公共祖先z，满足在树上离根最远，并且x和y都是z的子孙
 * 即求x节点路径和y节点路径中最后一个相同节点
 *
 * @author Shine
 * @create 2019/9/17 11:11
 */
public class Test236 {


	/*Definition for a binary tree node.*/
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}


	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode result=root;
		List<List<TreeNode>> pResult = new ArrayList<>();
		List<List<TreeNode>> qResult = new ArrayList<>();
		preOrder(root,p,new ArrayList<>(),pResult); // 查找p节点的路径，由于Java中List复制是浅拷贝，所以使用result保存结果
		preOrder(root,q,new ArrayList<>(),qResult); // 查找q节点的路径
		int shortLen = Math.min(pResult.get(0).size(), qResult.get(0).size()); // 对路径短的遍历
		for (int i = 0; i <shortLen ; i++) {
			if(pResult.get(0).get(i).equals(qResult.get(0).get(i))){
				result = qResult.get(0).get(i);
			}
		}
		return result;
	}


	/**
	 * 深度优先搜索
	 * @param root
	 * @param search
	 * @param path
	 * @param result
	 */
	private void preOrder(TreeNode root, TreeNode search, List<TreeNode> path,List<List<TreeNode>> result){
		if(root==null) return;
		path.add(root);
		if(root.equals(search)){
			result.add(new ArrayList<>(path));
			return;
		}
		preOrder(root.left,search,path,result);
		preOrder(root.right,search,path,result);
		path.remove(path.size()-1);
	}

	@Test
	public void test01(){
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(5);
		TreeNode c = new TreeNode(1);
		TreeNode d = new TreeNode(6);
		TreeNode e = new TreeNode(2);
		TreeNode f = new TreeNode(0);
		TreeNode g = new TreeNode(8);
		TreeNode h = new TreeNode(7);
		TreeNode i = new TreeNode(4);
		a.left=b;
		a.right=c;
		b.left=d;
		b.right=e;
		c.left=f;
		c.right=g;
		e.left=h;
		e.right=i;

		TreeNode treeNode = lowestCommonAncestor(a, b, i);
		System.out.println(treeNode.toString());
	}
}
