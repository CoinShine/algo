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
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> item = new ArrayList<>();
		result.add(item);
		generate(0, nums, item, result);
		return result;
	}

	public void generate(int i, int[] nums, List<Integer> item, List<List<Integer>> result) {
		if (i >= nums.length) return;
		item.add(nums[i]);
		result.add(new ArrayList<>(item));
		generate(i + 1, nums, item, result);
		item.remove(item.size() - 1);
		generate(i + 1, nums, item, result);
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
