package com.shine.leetcode;

/**
 * description: 跳跃游戏
 * 求从第i个位置最远可以跳到的第index[i]的位置
 * index[i] = num[i]+i
 * 比如在第i个位置，可以跳m步，则选择1到m步中能跳最远的位置
 * @author shine
 * @date 9/10/2019 7:24 PM
 * @version 1.0
 */
public class Test55 {
	public boolean canJump(int[] nums) {
		// 定义每次跳跃可以跳跃最远位置的数组
		int[] index = new int[nums.length];
		for (int i = 0; i <nums.length ; i++) {
			index[i]=i+nums[i];
		}

		int jump = 0;// 定义跳跃的位置
		int max_index = 0; // 记录最远可以跳跃的位置

		while (jump < index.length && jump<= max_index){ // 判断角标
			// 选出当前位置能跳跃的位置中可以达到最远的位置
			if(max_index<index[jump]){
				max_index = index[jump];
			}
			jump++;
		}
		return jump >= index.length;
	}
}
