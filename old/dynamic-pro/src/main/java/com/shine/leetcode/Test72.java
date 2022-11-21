package com.shine.leetcode;

import org.junit.Test;

/**
 * description: 编辑距离
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 *
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 *
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * @author shine
 * @date 2019/11/4 22:12
 * @version 1.0
 */
public class Test72 {
	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		int[][] dp = new int[m+1][n+1];
		for (int i = 0; i <n+1 ; i++) {
			dp[0][i] = i;
		}
		for (int i = 0; i < m+1; i++) {
			dp[i][0] = i;
		}
		for (int i = 1; i < m+1; i++) {
			for (int j = 1; j < n+1 ; j++) {
				if(word1.charAt(i-1)==word2.charAt(j-1)) //dp[i][j] 从1开始 相当于word1的第i个字符和word2的第j个字符相同
					dp[i][j] = dp[i-1][j-1];
				else{
					dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;
				}
			}
		}
		return dp[m][n];
	}
	@Test
	public void test01(){
		String word1="horse",word2="ros";
		System.out.println(minDistance(word1,word2));
	}
}
