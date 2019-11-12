package com.shine.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * DESCRIPTION:会议室 II
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 *
 * 贪心算法
 * 会议按照起始时间顺序进行；
 * 要给新的即将开始的会议找会议室时，先看当前有无空会议室；
 * 有则在空会议室开会，无则开设一间新会议室
 * @author Shine
 * @create 2019/11/12 12:39
 */
public class Test253 {
	int minMeetingRooms(int[][] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;

		// 将输入的一系列会议按照会议的起始时间排序。
		Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

		// 用一个最小堆来维护目前开辟的所有会议室，最小堆里的会议室按照会议的结束时间排序。
		PriorityQueue<int[]> heap = new PriorityQueue<>(intervals.length, Comparator.comparingInt(a -> a[1]));

		// 让第一个会议在第一个会议室里举行。
		heap.offer(intervals[0]);

		for (int i = 1; i < intervals.length; i++) {
			// 从第二个会议开始，对于每个会议，我们都从最小堆里取出一个会议室，那么这个会议室里的会议一定是最早结束的。
			int[] interval = heap.poll();

			assert interval != null;
			if (intervals[i][0] >= interval[1]) {
				// 若当前要开的会议可以等会议室被腾出才开始，那么就可以重复利用这个会议室。
				interval[1] = intervals[i][1];
			} else {
				// 否则，开一个新的会议室。
				heap.offer(intervals[i]);
			}

			// 把旧的会议室也放入到最小堆里。
			heap.offer(interval);
		}
		// 最小堆里的会议室个数就是要求的答案，即最少的会议个数。
		return heap.size();
	}
}
