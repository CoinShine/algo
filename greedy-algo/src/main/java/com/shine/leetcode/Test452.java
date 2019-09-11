package com.shine.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * description:用最少数量的箭引爆气球
 * 气球在坐标平面内
 * 分析：1.对于某个气球，至少需要一个弓箭引爆
 * 2.在引爆这个气球的同时，尽可能多的引爆其他气球(贪心！！！)
 * 3.首先对气球的开始端从小到大排序，遍历气球数组，同时维护一个射击区间，
 * 在满足引爆当前气球的情况下，尽可能多的引爆其他气球，每多引爆一个
 * 新的气球，更新一次射击区间(只考虑当前区间尾部即可)
 * 4.如果新的气球无法引爆了，则需要增加一个弓箭手去引爆新的气球，然后
 * 继续遍历气球
 *
 * @author shine
 * @version 1.0
 * @date 9/11/2019 10:47 AM
 */
public class Test452 {

	public int findMinArrowShots(int[][] points) {
		if (points.length == 0) return 0; // 如果数组没有数据直接返回
		Arrays.sort(points, (point1, point2) -> {
			if (point1 != point2) {
				return point1[0] - point2[0];
			}
			return point1[1] - point2[1];
		}); // 将气球的开始坐标按照从小到大排序，如果开始坐标相同，比较结束坐标

		int shootNum = 1; // 初始弓箭的数量
		int endShoot = points[0][1]; // 初始射击区间的尾部
		for (int i = 0; i < points.length; i++) {
			if (points[i][0] <= endShoot) {  // 如果新气球的开始小于等于当前射击区间的尾部，说明可以把新气球引爆
				if (points[i][1] < endShoot) { // 如果新气球的结束小于当前区间的尾部，则更新当前射击区间
					endShoot = points[i][1];
				}
			} else { // 如果新气球的开始大于当前区间，则需要新增弓箭，同时将当前区间更新的新气球的区间，
				// 此处只更新尾部即可（因为以上只根据尾部判断），为了方便理解可以同时定义头部和尾部
				shootNum++;
				endShoot = points[i][1];
			}
		}
		return shootNum;
	}

	@Test
	public void test01() {
		int[][] points = {{1, 16}, {2, 8}, {1, 6}, {7, 12}};

		int[] point = points[0];
		int i = point[0];
		Arrays.sort(points, (point1, point2) -> {
			if (point1[0] != point2[0]) {
				return point1[0] - point2[0];
			}
			return point1[1] - point2[1];
		});


		System.out.println(points);
	}
}
