package com.shine.array.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DESCRIPTION: 有重复元素的集合求不重复的子集个数
 * 首先对原数组进行排序，然后求子集，对最后的结果用set去重
 *
 * @author Shine
 * @create 9/12/2019 3:58 PM
 */
public class Test90 {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums); // 默认从小到大排序
		Set<List<Integer>> set  = new HashSet<>();
		List<Integer> item = new ArrayList<>();
		set.add(item);
		generate(0, nums, item, set);
		return new ArrayList<>(set); // 将结果转换成List返回
	}

	public void generate(int i, int[] nums, List<Integer> item, Set<List<Integer>> result) {
		if (i >= nums.length) return;
		item.add(nums[i]);
		result.add(new ArrayList<>(item));
		generate(i + 1, nums, item, result);
		item.remove(item.size() - 1);
		generate(i + 1, nums, item, result);
	}


	public List<List<Integer>> subsetsWithDup2(int[] nums) {
		Arrays.sort(nums); // 默认从小到大排序
		Set<List<Integer>> result = new HashSet<>();
		int size = 1 << nums.length;
		for (int i = 0; i < size; i++) {
			List<Integer> item = new ArrayList<>();
			for (int j = 0; j < nums.length; j++) {
				if ((i & (1 << j)) != 0) {
					item.add(nums[j]);
				}
			}
			result.add(item);
		}
		return new ArrayList<>(result); // 将结果转换成List返回
	}

	@Test
	public void test01() {
		int[] nums = {1, 2, 2};
		List<List<Integer>> lists = subsetsWithDup2(nums);
		System.out.println(lists.toString());
	}
}
