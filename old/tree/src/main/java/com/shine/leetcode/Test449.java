package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 序列化和反序列化二叉搜索树
 * 			8
 * 		3      10
 *    1   6       15
 *
 * @author shine
 * @version 1.0
 * @date 2019/9/19 20:55
 */
public class Test449 {

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder data = new StringBuilder();
		preOrder(root,data);
		return data.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if(data==null||data.length()==0) return null;
		String[] split = data.split("#");
		List<TreeNode> list = new ArrayList<>();
		for (String s : split) {
			TreeNode node = new TreeNode(Integer.parseInt(s));
			list.add(node);
		}
		for(int i=1;i<list.size();i++){
			insert(list.get(0),list.get(i));
		}
		return list.get(0);
	}


	/**
	 * 前序遍历二叉搜索树，转换成字符串使用“*”分隔数字
	 *
	 * @param root
	 * @param data
	 */
	private void preOrder(TreeNode root, StringBuilder data) {
		if(root==null) return;
		String val = root.val + "#";  // string data+=val 常量使用+拼接 比如 8# + 7# 会在编译器处理
		data.append(val);
		preOrder(root.left,data); // 遍历左子树  方法局部变量调用 data 需要传引用 由于string存在于string常量池中。所以用StringBuilder，否则返回为空
		preOrder(root.right,data); //遍历右子树
	}

	/**
	 * 二叉搜索树插入节点
	 *
	 * @param root
	 * @param node
	 */
	private void insert(TreeNode root, TreeNode node) {
		if (node.val < root.val) {
			if (root.left != null) { // 如果左子树不为空，递归插入左子树
				insert(root.left,node);
			}else{ //如果左子树为空，将此节点当做当前节点的左孩子
				root.left = node;
			}
		}else{
			if(root.right!=null){
				insert(root.right,node);
			}else{
				root.right=node;
			}
		}
	}


	/**
	 * 查找某个值在没在二叉搜索树种
	 *
	 * @param root
	 * @param val
	 * @return
	 */
	private boolean search(TreeNode root, int val) {
		if (root == null) return false;
		if (root.val == val) return true;
		if (val < root.val) {
			if (root.left != null) {
				return search(root.left, val);
			} else {
				return false;
			}
		} else {
			if (root.right != null) {
				return search(root.right, val);
			} else {
				return false;
			}
		}
	}

	@Test
	public void test01(){
		TreeNode a = new TreeNode(8);
		TreeNode b = new TreeNode(3);
		TreeNode c = new TreeNode(10);
		TreeNode d = new TreeNode(1);
		TreeNode e = new TreeNode(6);
		TreeNode f = new TreeNode(15);
		a.left=b;
		a.right=c;
		b.left=d;
		b.right=e;
		c.right = f;
		TreeNode deserialize = deserialize(serialize(a));
		System.out.println("---------------");
	}

	/**
	 * 定义树结构
	 */
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
