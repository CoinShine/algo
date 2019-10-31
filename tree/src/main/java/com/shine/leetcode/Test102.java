package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * description: 二叉树的层次遍历
 * <p>
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * 分析：1.使用广度优先搜索层次遍历，如果是图记得 标记visit 在while循环中 在加一个循环，每次遍历一层
 * 2.也可以使用深度优先搜索实现，深度遍历时 每层初始化空数组
 *
 * @author shine
 * @version 1.0
 * @date 2019/10/31 17:16
 */
public class Test102 {

	/**
	 * 使用广度优先搜索，比记录每层层数遍历
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		if(root == null) return new ArrayList<>();
		List<List<Integer>> result  = new ArrayList<>();

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()){
			int levelSize = queue.size();
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < levelSize; i++) {
				TreeNode currNode = queue.poll();
				list.add(currNode.val);
				if(currNode.left!=null){
					queue.add(currNode.left);
				}
				if(currNode.right != null){
					queue.add(currNode.right);
				}
			}
			result.add(list);
		}
		return result;

	}

	List<List<Integer>> res  = new ArrayList<>();
	public List<List<Integer>> levelOrder2(TreeNode root) {
		if(root == null) return new ArrayList<>();
		dfs(root,0);
		return res;
	}

	public void dfs(TreeNode root,int level){
		if(root == null) return;
		if(res.size() < level+1){
			List<Integer> list = new ArrayList<>();
			res.add(list);
		}
		res.get(level).add(root.val);
		dfs(root.left,level+1);
		dfs(root.right,level+1);
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	@Test
	public void test01(){
		TreeNode node = new TreeNode(3);
		TreeNode node1 = new TreeNode(9);
		TreeNode node2 = new TreeNode(20);
		TreeNode node3 = new TreeNode(15);
		TreeNode node4 = new TreeNode(7);
		node.left = node1;
		node.right = node2;
		node2.left = node3;
		node2.right = node4;

		List<List<Integer>> lists = levelOrder2(node);
		System.out.println(lists.toString());
	}
}
