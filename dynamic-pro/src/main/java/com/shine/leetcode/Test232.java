package com.shine.leetcode;

/**
 * description:零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * dp[i] 代表金额i的最优解（最小硬币个数） dp[1]=1,dp[2]=1...dp[5]=1...
 * 金额i可以由i-1和1(coind[0])，i-2和2(coins[1])，i-5和5(coins[2])组合
 * 状态转移方程：dp[i] = min(dp[i-1],dp[i-2],dp[i-5]...)+1
 * @author shine
 * @date 2019/9/26 19:17
 * @version 1.0
 */
public class Test232 {
	public int coinChange(int[] coins, int amount) {
		if(coins.length == 0 || amount==0) return 0;
		int[] dp = new int[amount+1];
		for (int i = 1; i <= amount; i++) {
			dp[i]=-1; // dp[0] = 0
		}
		for (int i=1;i<=amount;i++){
			for (int j = 0; j < coins.length; j++) {
				if(coins[j]<=i&&dp[i-coins[j]]!=-1){ // 说明金额i可以由硬币组合而成
					if(dp[i]==-1 || dp[i]>dp[i-coins[j]]+1){ // 首次组合金额i 或 组合金额i的硬币数比当前组合成金额i的硬币数小
						dp[i]=dp[i-coins[j]]+1;  // 替换为小的硬币数
					}
				}
			}
		}
		return dp[amount];
	}
}
