package com.shine.leetcode;

/**
 * description:最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续
 * 子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 将求n个数的最大子段和转化为求以第i个数为结尾的最大子段和
 * dp[i] ，求出dp[i]中最大的数，即为所求的最大子段和
 * 状态转移方程：dp[i] = max(nums[i],dp[i-1]+nums[i])
 *
 * @author shine
 * @version 1.0
 * @date 2019/9/26 18:45
 */
public class Test53 {
	public int maxSubArray(int[] nums) {
		if (nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int result = dp[0];
		for (int i = 1; i < nums.length; i++) {
			dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
			if (dp[i] > result) result = dp[i];
		}
		return result;
	}
}
