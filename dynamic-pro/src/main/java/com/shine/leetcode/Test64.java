package com.shine.leetcode;

import org.junit.Test;

/**
 * description: 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * dp[i][j] 为走到第i行第j列时最小的步骤，只能从左侧或上方走到当前位置，取两个钟最小的
 * dp[i][j] = min(dp[i][j-1],dp[i-1][j]) + grid[i][j]
 * 边界条件 d[i][0] =dp[i-1][0]+grid[i][0]  dp[0][j] = dp[0][j-1] + grid[0][j]
 * @author shine
 * @date 2019/9/27 17:34
 * @version 1.0
 */
public class Test64 {
	public int minPathSum(int[][] grid) {
		if(grid.length == 0) return 0;
		int[][] dp = new int[grid.length][grid[0].length];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < grid[0].length ; i++) { // 上侧边
			dp[0][i] = dp[0][i-1]+grid[0][i];
		}
		for (int i = 1; i < grid.length; i++) {
			dp[i][0] = dp[i-1][0] + grid[i][0]; // 左侧边
			for (int j = 1; j < grid[0].length ; j++) {
				dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
			}
		}
		return dp[grid.length-1][grid[0].length-1];
	}

	@Test
	public void test01(){
		int[][] grid ={{1,3,1},{1,5,1},{4,2,1}};
		System.out.println(minPathSum(grid));
	}
}
