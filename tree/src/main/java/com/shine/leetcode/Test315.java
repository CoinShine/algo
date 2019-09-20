package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: 使用二叉搜索树解决逆序数问题
 * @author shine
 * @date 2019/9/19 22:28
 * @version 1.0
 */
public class Test315 {
	public List<Integer> countSmaller(int[] nums) {
		if(nums.length==0) return new ArrayList<>(0);
		List<Integer> result = new ArrayList<>();
		List<BSTreeNode> list = new ArrayList<>();
		List<Integer> count = new ArrayList<>();
		for (int i = nums.length-1; i >=0 ; i--) {
			list.add(new BSTreeNode(nums[i]));
		}
		count.add(0); // 第一个节点count_small = 0

		for (int i = 1; i < list.size(); i++) {
			int[] count_small = new int[]{0}; // 方法局部变量调用需要存引用 基本数据类型返回为空 int count_small =0;是有问题的
			insert_BSTree(list.get(0), list.get(i), count_small);
			count.add(count_small[0]);
		}
		for (int i = count.size()-1; i >=0 ; i--) {
			result.add(count.get(i));
			list.remove(i);
		}
		return  result;
	}



	private void insert_BSTree(BSTreeNode root,BSTreeNode node ,int[] count_small){
		if(node.val<root.val){
			root.count++;
			if(root.left!=null){
				insert_BSTree(root.left,node,count_small);
			}else{
				root.left = node;
			}
		}else{
			if(node.val==root.val) count_small[0] +=root.count;
			else count_small[0] +=root.count+1;
			if(root.right!=null){
				insert_BSTree(root.right,node,count_small);
			}else{
				root.right = node;
			}
		}
	}

	public class BSTreeNode {
		int val;
		int count;
		BSTreeNode left;
		BSTreeNode right;

		BSTreeNode(int x) {
			val = x;
			count = 0;
			left = null;
			right = null;
		}
	}

	@Test
	public void test01(){
		//int[] nums={5,6,2,1};
		//List<Integer> integers = countSmaller(nums);
		//System.out.println(integers.toString());
		int[] a = new int[]{0};
		test02(a);
		System.out.println(Arrays.toString(a));
	}

	private void test02(int[] b){
		b[0]=10;
	}
}
