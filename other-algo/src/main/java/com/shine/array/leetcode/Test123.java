package com.shine.array.leetcode;

import org.junit.Test;

/**
 * description: 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 解答：参考人家的答案，套用动态规划框架解决六道股票买卖问题
 * 1、穷举框架 利用「状态」进行穷举。我们具体到每一天，看看总共有几种可能的「状态」，再找出每个「状态」对应的「选择」。
 * 我们要穷举所有「状态」，穷举的目的是根据对应的「选择」更新状态。听起来抽象，你只要记住「状态」和「选择」两个词就行。
 * 比如本题：每天都有三种「选择」：买入、卖出、无操作，我们用 buy，sell，rest 表示这三种选择。但问题是，
 * 并不是每天都可以任意选择这三种选择的，因为 sell 必须在 buy 之后，buy 必须在 sell 之后。那么 rest
 * 操作还应该分两种状态，一种是 buy 之后的 rest（持有了股票），一种是 sell 之后的 rest（没有持有股票）。
 * 而且别忘了，我们还有交易次数 kk 的限制，就是说你 buy 还只能在 k > 0 的前提下操作。
 * 这个问题的「状态」有三个，第一个是天数，第二个是允许交易的最大次数，第三个是当前的持有状态（即之前说的 restrest 的状态，
 * 我们不妨用 11 表示持有，00 表示没有持有）。然后我们用一个三维数组就可以装下这几种状态的全部组合：
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * max(   选择 rest  ,           选择 sell      )
 * <p>
 * 解释：今天我没有持有股票，有两种可能：
 * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
 * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
 * <p>
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 * max(   选择 rest  ,           选择 buy         )
 * <p>
 * 解释：今天我持有着股票，有两种可能：
 * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
 * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
 * <p>
 * 这个解释应该很清楚了，如果 buy，就要从利润中减去 prices[i]prices[i]，如果 sell，就要给利润增加 prices[i]prices[i]。
 * 今天的最大利润就是这两种可能选择中较大的那个。而且注意 kk 的限制，我们在选择 buy 的时候，把 k 减小了 1，很好理解吧，
 * 当然你也可以在 sell 的时候减 1，一样的，现在，我们已经完成了动态规划中最困难的一步：状态转移方程。如果之前的内容你都可以理解，
 * 那么你已经可以秒杀所有问题了，只要套这个框架就行了。不过还差最后一点点，就是定义 base case，即最简单的情况。
 * <p>
 * dp[-1][k][0] = 0
 * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
 * dp[-1][k][1] = -infinity
 * 解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
 * dp[i][0][0] = 0
 * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
 * dp[i][0][1] = -infinity
 * 解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
 *
 * @author shine
 * @version 1.0
 * @date 2019/10/30 17:33
 */
public class Test123 {
	int maxProfit(int[] prices) {
		int maxK = 2;
		int length = prices.length;
		int[][][] dp = new int[length][maxK+1][2]; //表示第i天交易k次持有或不持有股票的利润
		for (int i = 0; i < length; i++) {
			for (int k = maxK; k >= 1; k--) {
				if (i - 1 == -1) {
					/*处理 base case */
					dp[i][k][0] = Math.max(0,Integer.MIN_VALUE+prices[i]);
					dp[i][k][1] = Math.max(Integer.MIN_VALUE,0-prices[i]);
				} else {
					dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i-1][k][1] + prices[i]);
					dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
				}
			}
		}
		return dp[length-1][maxK][0];
	}


	int maxProfit2(int[] prices){
		int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
		int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
		for (int price : prices) {
			dp_i10 = Math.max(dp_i10, dp_i11 + price);
			dp_i11 = Math.max(dp_i11, -price);
			dp_i20 = Math.max(dp_i20, dp_i21 + price);
			dp_i21 = Math.max(dp_i21, dp_i10 - price);
			//dp_i10 = Math.max(dp_i10, dp_i11 + price);
			//dp_i11 = Math.max(dp_i11, -price);
		}
		return dp_i20;
	}

	@Test
	public void test01(){
		int[] prices = {3,3,5,0,0,3,1,4};
		System.out.println(maxProfit2(prices));
	}
}
