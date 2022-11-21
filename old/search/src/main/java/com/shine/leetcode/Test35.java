package com.shine.leetcode;

import org.junit.Test;

/**
 * description:给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素
 *
 * @author shine
 * @version 1.0
 * @date 2019/9/18 15:18
 */
public class Test35 {
	public int searchInsert(int[] nums, int target) {
		int begin = 0;
		int end = nums.length - 1;
		int mid = end / 2;
		while (begin <= end) {
			mid = begin + ((end - begin) >> 1);
			if (nums[mid] == target) return mid;
			else if (target > nums[mid]) begin = mid + 1;
			else end = mid - 1;
		}
		// 不在数组中，返回位置 已包含边界mid=0和mid=nums.length的情况
		return target > nums[mid] ? mid + 1 : mid;
	}

	@Test
	public void test01() {
		int nums[] = {1, 3, 5, 6, 8};
		int i = searchInsert(nums, 7);
		System.out.println(i);
	}
}
