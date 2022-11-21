package com.shine.leetcode;

/**
 * description: 跳跃到末尾至少几次
 * 当前节点中不能在向后走时再跳跃
 * 记录当前节点能走的各步中最大的位置
 * @author shine
 * @date 2019-09-10 23:36
 * @version 1.0
 */
public class Test45 {
	public int jump(int[] nums) {
		if(nums.length<2) return 0;
		int current_max_len = nums[0]; // 记录当前节点可以跳跃的最大值
		int traversal_max_len = nums[0]; // 记录遍历过程中能跳跃的最大位置，当走到不能前进时，将当前最大节点更新
		int minTime = 1; // 最小次数
		for (int i = 0; i < nums.length ; i++) {
			if(i > current_max_len){
				minTime++;
				current_max_len = traversal_max_len;
			}
			if(traversal_max_len < nums[i]+i){
				traversal_max_len = nums[i]+i;
			}
		}
		return minTime;
	}
}
