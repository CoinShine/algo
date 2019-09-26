package com.shine.leetcode;

import org.junit.Test;

/**
 * description: 爬楼梯问题
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * @author shine
 * @date 2019/9/26 14:25
 * @version 1.0
 */
public class Test70 {
	/**
	 * 使用动态规划 走到第i阶的走法为i-1的走法和i-2走法之和
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {
		if(n==1 || n==2 ) return n;
		int[] dp = new int[n]; // 设置动态规划数组 将每阶的走法存起来
		dp[0] = 1;
		dp[1] = 2;
		for (int i = 2; i < n; i++) {
			dp[i] = dp[i-1]+dp[i-2];
		}
		return dp[n-1];
	}
	/**
	 * 暴力穷举 超时 时间复杂度 类似O(2^n)
	 * @param n
	 * @return
	 */
	public int climbStairs2(int n) {
		if(n==1) return 1;
		if(n==2) return 2;
		return climbStairs2(n-1)+climbStairs2(n-2);
	}
	@Test
	public void test01(){
		int i = climbStairs(45);
		System.out.println(i);
	}
}
