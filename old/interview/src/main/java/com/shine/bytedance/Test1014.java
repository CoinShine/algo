package com.shine.bytedance;

/**
 * description:最佳观光组合
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 * 示例：
 *
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *
 * 已知题目要求 res = A[i] + A[j] + i - j （i < j） 的最大值，
 * 而对于输入中的每一个 A[j] 来说， 它的值 A[j] 和它的下标 j 都是固定的，
 * 所以 A[j] - j 的值也是固定的。
 * 因此，对于每个 A[j] 而言， 想要求 res 的最大值，也就是要求 A[i] + i （i < j） 的最大值，
 * 所以不妨用一个变量 pre_max 记录当前元素 A[j] 之前的 A[i] + i 的最大值，
 * 这样对于每个 A[j] 来说，都有 最大得分 = pre_max + A[j] - j，
 * 再从所有 A[j] 的最大得分里挑出最大值返回即可
 *
 * 时间复杂度 O(N)O(N)
 * 空间复杂度 O(1)O(1)
 * @author shine
 * @date 2019/11/16 22:20
 * @version 1.0
 */
public class Test1014 {
	public int maxScoreSightseeingPair(int[] A) {
		int res = 0;
		int pre_max = A[0]; // 初始化pre_max A[0]+0
		for (int i = 1; i < A.length; i++) {
			res = Math.max(res,pre_max+A[i]-i); // 刷新res,max+A[j]-j
			pre_max = Math.max(pre_max,A[i]+i); //  刷新pre_max， 得到更大的A[i] + i
		}
		return res;
	}
}
