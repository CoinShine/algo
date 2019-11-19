package com.shine.ali;

/**
 * description:分割回文串 II
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的最少分割次数。
 *
 * 示例:
 * 	输入: "aab"
 * 	输出: 1
 * 	解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * @author shine
 * @date 2019/11/19 11:58
 * @version 1.0
 */
public class Test132 {
	/**
	 * 	参考题解
	 * @param s
	 * @return
	 */
		public int minCut(String s) {
			int n = s.length();
			int[] min_s = new int[n];
			boolean[][] dp = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				min_s[i] = i;
				for (int j = 0; j <= i; j++) {
					if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1])) {
						dp[j][i] = true;
						min_s[i] = j == 0 ? 0 : Math.min(min_s[i], min_s[j - 1] + 1);
					}
				}
			}
			return min_s[n - 1];
		}
}
