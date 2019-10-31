package com.shine.array.leetcode;

import org.junit.Test;

/**
 * description:买卖股票的最佳时机 II
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 分析：同一天可以多次买卖 方法一：对每个元素都有买和卖两种情况 时间复杂度为 O(2^N)  方法二：贪心算法 只要后一天比前一天大就买入卖出
 * @author shine
 * @date 2019/10/29 17:03
 * @version 1.0
 */
public class Test122 {
	public int maxProfit(int[] prices) {
		int length = prices.length;
		int maxProfit = 0;
		for (int i = 0; i < length-1; i++) {
			if(prices[i+1]-prices[i]>0){
				maxProfit+=prices[i+1]-prices[i];
			}
		}
		return maxProfit;
	}

	@Test
	public void test01(){
		int[] prices = {7,1,5,3,6,4};
		int i = maxProfit(prices);
		System.out.println(i);
	}
}
