package com.shine.bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * description:分发糖果
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 		相邻的孩子中，评分高的孩子必须获得更多的糖果。
 *		那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 *
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 *
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 *
 * 分析：
 * 思路一:
 * 我们先找从左到右满足最少的糖果，再找从右到左的，最后取两边都满足的值(就是最大值)。
 *
 * 思路二：
 * 思路二:
 * 只需扫一遍数组。
 *
 * 如何判断 i 位置需要多少糖果，我们需要处理有三种情况:
 * 	1.ratings[i - 1] == ratings[i]，那么我们只需要 1 糖果
 * 	2.ratings[i - 1] < ratings[i]，那么我们只需要比前一个多一块糖果
 * 	3.ratings[i - 1] > ratings[i]，那么我们不知道如何判断了，但是，如果知道递减的个数，我们就能判断最少的糖果了
 * @author shine
 * @date 2019/11/16 12:08
 * @version 1.0
 */
public class Test135 {
	public int candy(int[] ratings) {
		if (ratings == null || ratings.length == 0) return 0;
		int n = ratings.length;
		int[] candy_nums = new int[n];
		Arrays.fill(candy_nums, 1);  // 首先填充1，满足条件1
		for (int i = 1; i < n; i++) { // 找从左到右满足条件的
			if (ratings[i] > ratings[i - 1]) candy_nums[i] = candy_nums[i - 1] + 1; //保证从左到右的最少个数
		}
		for (int i = n - 1; i > 0; i--) { // 找从右到左满足条件的(同时要符合从左到右)
			if (ratings[i - 1] > ratings[i]) candy_nums[i - 1] = Math.max(candy_nums[i - 1], candy_nums[i] + 1);  // 保证从左到右也满足, 同时也满足从右到左
		}
		int res = 0;
		for (int i = 0; i < n; i++) res += candy_nums[i];
		return res;
	}

	public int candy2(int[] ratings) {
		int res = 1;
		int pre = 1;
		int des_num = 0; // 递减序列个数
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] >= ratings[i - 1]) {
				if (des_num > 0) {
					res += ((1 + des_num) * des_num) / 2;
					if (des_num >= pre) res += (des_num - pre + 1);
					pre = 1;
					des_num = 0;
				}
				pre = ratings[i - 1] == ratings[i] ? 1 : pre + 1; // 如果前一个和后一个相等 后一个置为1，如果后一个大于前一个，后一个置为pre+1
				res += pre;
			} else des_num++;
		}
		if (des_num > 0) {
			res += ((1 + des_num) * des_num) / 2;  // 如果全是递减的，计算递减的需要多少糖果
			if (des_num >= pre) res += (des_num - pre + 1); // 如果递减序列数量大于等于原来的峰值，这时应该在原来pre补多余的值
		}
		return res;
	}

	@Test
	public void test01(){
		int[] ratings ={1,2,3,5,4,3,2,1};
		candy2(ratings);
	}
}
