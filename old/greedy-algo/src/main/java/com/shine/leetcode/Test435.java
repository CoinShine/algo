package com.shine.leetcode;

import java.util.Arrays;

/**
 * description: 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 解题分析：一、暴力法，首先将区间按开始时间从小到大排序，然后列举出所有的组合，最后每种组合分别判断各个区间有没有互相重叠
 * 时间复杂度 排序需要O(nlogn) 求子序列需要 Cn0+……+Cnn=2^n,对每种组合进行判断是否重叠，k 个区间，需要 O(k) 的时间复杂度
 * Cn0 x 0 + Cn1×1 + Cn2×2 + … + Cnk * k + … + Cnn×n = n×2^(n-1)
 * 二、贪心法 要尽可能少得删除区间，那么当遇到了重叠的时候，应该把区间跨度大，即结束比较晚的那个区间删除。因为如果不删除它，
 * 它会和剩下的其他区间发生重叠的可能性非常大
 * @author shine
 * @date 2019/11/13 11:24
 * @version 1.0
 */
public class Test435 {
	int eraseOverlapIntervals(int[][] intervals) {
		if (intervals.length == 0) return 0;

		// 将所有区间按照起始时间排序
		Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

		// 用一个变量 end 记录当前的最小结束时间点，以及一个 count 变量记录到目前为止删除掉了多少区间
		int end = intervals[0][1], count = 0;

		// 从第二个区间开始，判断一下当前区间和前一个区间的结束时间
		for (int i = 1; i < intervals.length; i++) {
			// 当前区间和前一个区间有重叠，即当前区间的起始时间小于上一个区间的结束时间，end记录下两个结束时间的最小值，把结束时间晚的区间删除，计数加1。
			if (intervals[i][0] < end) {
				end = Math.min(end, intervals[i][1]);
				count++;
			} else {
				end = intervals[i][1];
			}
		}

		// 如果没有发生重叠，根据贪婪法，更新 end 变量为当前区间的结束时间
		return count;

	}
}
