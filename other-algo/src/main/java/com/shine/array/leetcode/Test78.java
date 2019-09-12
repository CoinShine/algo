package com.shine.array.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 求子集（回溯、位运算）
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * @author shine
 * @version 1.0
 * @date 9/11/2019 6:21 PM
 */
public class Test78 {
	/**
	 * 回溯法
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> item = new ArrayList<>();
		generate2(0,nums,item,result);
		result.add(item);
		return result;
	}

	/**
	 * 递归实现一
	 * @param i
	 * @param nums
	 * @param item
	 * @param result
	 */
	public void generate(int i, int[] nums, List<Integer> item, List<List<Integer>> result) {
		if (i >= nums.length)
			return;
		item.add(nums[i]);
		result.add(new ArrayList<>(item));
		generate(i + 1, nums, item, result); // 将元素放入递归
		item.remove(item.size() - 1); // 将元素取出
		generate(i + 1, nums, item, result); // 取出元素后继续递归
	}


	/**
	 * 递归实现二
	 * @param i
	 * @param nums
	 * @param item
	 * @param result
	 */
	public void generate2(int i, int[] nums, List<Integer> item, List<List<Integer>> result){
		for(int j =i;j<nums.length;j++){
			item.add(nums[j]);
			result.add(new ArrayList<>(item));
			generate2(j+1,nums,item,result);
			item.remove(item.size()-1);
		}
	}

	/**
	 * 位运算法，对于某个元素有存现和不出现两种情况，
	 * 子集合总大小为2^n
	 * 例如集合只有ABC三个元素  A为100 B为10  C为1
	 * 遍历总集合大小，与集合中的元素做与运算，为真加入结果
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets2(int[] nums) {

		int setSize = 1<<nums.length;
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0;i<setSize;i++){
			List<Integer> item = new ArrayList<>();
			for(int j=0;j<nums.length;j++){
				if((i&(1<<j))!=0){
					item.add(nums[j]);
				}
			}
			result.add(item);
		}
		return result;
	}


	@Test
	public void test01() {
		int[] nums = {1, 2, 3};
		List<List<Integer>> subsets = subsets(nums);
		System.out.println(subsets.toString());

		//List list = new ArrayList();
		//list.add(2);
		//list.add(3);
		//list.add(5);
		//list.remove(2);
		////list.remove(5);
		//System.out.println(list.toString());
	}
}
