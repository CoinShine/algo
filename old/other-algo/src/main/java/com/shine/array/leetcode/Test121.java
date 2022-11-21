package com.shine.array.leetcode;

import org.junit.Test;

/**
 * description: 买股票的最佳时间
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *	分析： 1.暴力法 要求j>i  求max(num[j]-num[i]) 时间复杂度为O(N^2)
 *		   2.找到最小的谷之后最大的峰 时间复杂度为O(N)
 * @author shine
 * @date 2019/10/29 16:47
 * @version 1.0
 */
public class Test121 {
	public int maxProfit(int[] prices) {
		int min = Integer.MAX_VALUE; // 最小的谷值
		int maxProfit = 0; // 最大利润
		for (int price : prices) {
			if (price < min) {
				min = price;
			} else if (price - min > maxProfit) {
				maxProfit = price - min;
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
