package com.shine.leetcode;

/**
 * DESCRIPTION:解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6)
 * 解题思路
 *
 * 同样的，递归就是压栈压栈压栈，出栈出栈出栈的过程，我们可以利用动态规划的思想，省略压栈的过程，直接从 bottom 到 top。
 *
 * 用一个 dp 数组， dp [ i ] 代表字符串 s [ i, s.len-1 ]，也就是 s 从 i 开始到结尾的字符串的解码方式。
 *
 * 这样和递归完全一样的递推式。
 *
 * 如果 s [ i ] 和 s [ i + 1 ] 组成的数字小于等于 26，那么
 *
 * dp [ i ] = dp[ i + 1 ] + dp [ i + 2 ]
 *
 *多种解法
 *https://leetcode-cn.com/problems/decode-ways/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-3/
 * @author Shine
 * @create 2019/11/11 23:18
 */
public class Test91 {
	public int numDecodings(String s) {
		int len = s.length();
		int[] dp = new int[len + 1];
		dp[len] = 1; //将递归法的结束条件初始化为 1
		//最后一个数字不等于 0 就初始化为 1
		if (s.charAt(len - 1) != '0') {
			dp[len - 1] = 1;
		}
		for (int i = len - 2; i >= 0; i--) {
			//当前数字时 0 ，直接跳过，0 不代表任何字母
			if (s.charAt(i) == '0') {
				continue;
			}
			//判断两个字母组成的数字是否小于等于 26
			int ten = (s.charAt(i) - '0') * 10;
			int one = s.charAt(i + 1) - '0';
			if (ten + one > 26) {
				dp[i+2] = 0;
			}
			dp[i] = dp[i + 1] + dp[i + 2];

		}
		return dp[0];
	}
}
