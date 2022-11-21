package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:求二叉树路径之和
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 分析：使用深度优先搜索所有从根节点到叶子节点的路径，使用先序遍历
 * 时间复杂度O(N)
 * @author Shine
 * @create 2019/9/17 10:31
 */
public class Test113 {


	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		int pathValue=0;
		List<List<Integer>> result  = new ArrayList<>(); // 最终结果
		List<Integer> re = new ArrayList<>(); // 保存某一次符合条件的路径
		preOrder(root,result,re,sum,pathValue);
		return result;
	}

	private void preOrder(TreeNode root,List<List<Integer>> result,List<Integer> re,int sum,int pathValue){
		if(root==null) return;
		pathValue+=root.val;
		//path+="->"+root.val;
		re.add(root.val);
		if(pathValue==sum && root.left==null && root.right == null){
			result.add(new ArrayList<>(re)); // 引用需要new对象
			//System.out.println("path:"+path);
		}
		preOrder(root.left,result,re,sum,pathValue);
		preOrder(root.right,result,re,sum,pathValue);

		//pathValue-=root.val; 这里的pathValue在后边不会用到，所以这里不用clear
		re.remove(re.size()-1); // 这里的re是个引用，所以必须要清理
	}


	@Test
	public void test01(){
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(4);
		TreeNode c = new TreeNode(8);
		TreeNode d = new TreeNode(11);
		TreeNode e = new TreeNode(13);
		TreeNode f = new TreeNode(4);
		TreeNode g = new TreeNode(7);
		TreeNode h = new TreeNode(2);
		TreeNode i = new TreeNode(5);
		TreeNode j = new TreeNode(1);
		a.left=b;
		a.right=c;
		b.left=d;
		c.left=e;
		c.right =f;
		d.left = g;
		d.right=h;
		f.left=i;
		f.right=j;
		List<List<Integer>> lists = pathSum(a, 22);
		//System.out.println(lists.toString());
	}



	/*  Definition for a binary tree node.*/
	  public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }
}
