package com.shine.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * description:给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * @author shine
 * @version 1.0
 * @date 2019/9/18 16:18
 */
public class Test34 {
	public int[] searchRange(int[] nums, int target) {
		int first = searchFirst(nums, target);
		int last = searchLast(nums, target);
		return new int[]{first, last};
	}

	/**
	 * 查找第一个等于目标的数的角标
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	private int searchFirst(int[] nums, int target) {
		int begin = 0;
		int end = nums.length - 1;
		while (begin <= end) {
			int mid = begin + ((end - begin) >> 1);
			if (nums[mid] < target) begin = mid + 1;
			else end = mid - 1; // 找第一个end是大于等于
		}
		if (begin < nums.length && nums[begin] == target) return begin;
		else return -1;
	}

	/**
	 * 查找最后一个等于目标的数的角标
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	private int searchLast(int[] nums, int target) {
		int begin = 0;
		int end = nums.length - 1;
		while (begin <= end) {
			int mid = begin + ((end - begin) >> 1);
			if (nums[mid] > target) end = mid - 1;
			else begin = mid + 1;  //找最后一个，begin是小于等于
		}
		if (end>=0 && nums[end] == target) return end;
		else return -1;
	}

	@Test
	public void test01(){
		int[] nums=new int[]{1,2,3,4,6,6,6,6};
		//int[] nums=new int[0];
		int[] ints = searchRange(nums, 1);
		System.out.println(Arrays.toString(ints));
	}
}
