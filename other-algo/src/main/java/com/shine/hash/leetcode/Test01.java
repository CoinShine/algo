package com.shine.hash.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * description:两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * 分析：方法一 暴力法 a+b=target 对应a先遍历数组，角标为0到length-1，对于b遍历 角标从a+1 到length-1 时间复杂度为O(n^2)
 * 		方法二 b=target-a 使用hash表 map或set 判断b是否在数组中，最后返回角标数组 时间复杂度为O(N) 空间复杂度为O(N)
 * @author shine
 * @date 2019/10/22 11:05
 * @version 1.0
 */
public class Test01 {
	public int[] twoSum(int[] nums, int target) {
		int length = nums.length;
		Map<Integer,Integer> map = new HashMap<>();
		for (int i = 0; i <length ; i++) {
			int a = nums[i];
			int b = target - a;
			if(map.containsKey(b)){
				return new int[]{map.get(b),i};
			}
			map.put(a,i);
		}
		return new int[]{};
	}
}
