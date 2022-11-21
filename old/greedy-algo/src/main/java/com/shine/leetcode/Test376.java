package com.shine.leetcode;

/**
 * description: 摇摆序列
 * 使用状态机的思想 三个状态 Begin Up Down
 * 当前是UP态 下一个数字大于当前数字则不加
 * 如果当前是DOWN态，下一个数字小于当前数字则不加
 * 贪心的思想就是说 UP态中记录最大的数，DOWN态中记录最小的数
 *
 * @author shine
 * @version 1.0
 * @date 9/10/2019 4:38 PM
 */
public class Test376 {

	private static final int BEGIN = 0;
	private static final int UP = 1;
	private static final int DOWN = 2;
	public int wiggleMaxLength(int[] nums) {
		if (nums.length < 2) return nums.length; //如果长度小于2直接返回
		int state = BEGIN;
		int maxLen = 1;// 因为最少有一个数，所以从第二个数开始判断
		for (int i = 1; i < nums.length; i++) {
			switch (state) {
				case BEGIN:
					if (nums[i] > nums[i - 1]) {
						maxLen++;
						state = UP;
					} else if (nums[i] < nums[i - 1]) {
						maxLen++;
						state = DOWN;
					}
					break;
				case UP:
					if (nums[i] < nums[i - 1]) {
						maxLen++;
						state = DOWN;
					}
					break;
				case DOWN:
					if(nums[i]>nums[i-1]){
						state = UP;
						maxLen++;
					}
					break;
			}
		}
			return maxLen;
	}
}
