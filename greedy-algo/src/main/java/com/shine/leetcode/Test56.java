package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 解题思路：首先将问题化简成只有两个区间a,b 假设a的开始时间早于b，那么ab有以下情况
 * 1.a、b没有重叠  2.a、b有重叠，且b的结束时间晚于a的结束时间 3.a、b有重叠且b的结束时间早于a的结束时间
 * 很显然，2，3就是我们想要的结果 则融合的区间的结束时间为 两个区间的较大者
 * 这里使用贪心算法：
 * 先将所有的区间按照起始时间的先后顺序排序，从头到尾扫描一遍
 * 定义两个变量 previous 和 current，分别表示前一个区间和当前的区间
 * 如果没有融合，那么当前区间就变成了新的前一个区间，下一个区间成为新的当前区间
 * 如果发生了融合，更新前一个区间的结束时间。
 * 复杂度分析：
 * 时间复杂度 O(nlog(n))，因为一开始要对数组进行排序。
 * 空间复杂度为 O(n)，因为用了一个额外的 result 数组来保存结果。
 * 注意：和区间相关的问题，有非常多的变化，融合区间可以说是最基本也是最常考的一个
 * @author shine
 * @date 2019/11/13 10:55
 * @version 1.0
 */
public class Test56 {
	public int[][] merge(int[][] intervals) {
		// 将所有的区间按照起始时间的先后顺序排序
		Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

		// 定义一个 previous 变量，初始化为 null
		int[] previous = null;
		// 定义一个 result 变量，用来保存最终的区间结果
		List<int[]> result = new ArrayList<>();

		// 从头开始遍历给定的所有区间
		for (int[] current : intervals) {
			// 如果这是第一个区间，或者当前区间和前一个区间没有重叠，那么将当前区间加入到结果中
			if (previous == null || current[0] > previous[1]) {
				result.add(previous = current);
			} else { // 否则，两个区间发生了重叠，更新前一个区间的结束时间
				previous[1] = Math.max(previous[1], current[1]);
			}
		}

		return result.toArray(new int[result.size()][]);
	}

	@Test
	public void test01(){
		int[][] nums = {{1,3},{2,6},{8,10},{15,18}};
		int[][] merge = merge(nums);
		System.out.println(1);
	}
}
