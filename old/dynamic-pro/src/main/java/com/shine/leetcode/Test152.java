package com.shine.leetcode;

import org.junit.Test;

/**
 * description: 乘积最大子序列
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * 分析：1.暴力法 通过DFS遍历，每个数字有两种情况，乘和不乘，乘的话记录sum，不乘从sum从1开始（为了满足连续）,最后返回最大值 O(2^N)
 * 		 2.记忆化使用动态规划
 * 		 3.判断负数的个数 偶数个且没有0 乘一起最大，奇数个且没有0 分别算两边的乘积 取最大值即可，如果有0存在 则将0分成左右两部分
 *
 * @author shine
 * @date 2019/11/4 14:55
 * @version 1.0
 */
public class Test152 {

	public int maxProduct(int[] nums) {
		//int dp[] = new int[nums.length+1];  // dp[i] 表示以i个元素结尾的子序列的最大值或最小值
		// 使用二维dp
		int max = Integer.MIN_VALUE;
		int iMin = 1,iMax=1;
		for (int i = 0; i < nums.length; i++) {
			if(nums[i]<0){ // 当遇到负数时，交换最大值和最小值
				int temp = iMax;
				iMax = iMin;
				iMin = temp;
			}
			iMax = Math.max(iMax*nums[i],nums[i]);
			iMin = Math.min(iMin*nums[i],nums[i]);
			max = Math.max(max,iMax);

		}
		return max;
	}


	/**
	 * 递归
	 * @param nums
	 * @return
	 */
	public int maxProduct1(int[] nums) {
		//List<Integer> result = new ArrayList<>();
		return DFS1(0, nums, 1);
		//System.out.println(i);
		//System.out.println(result.toString());
		//Collections.sort(result);
		//return result.get(result.size()-1);
	}
	private int DFS1(int index, int[] nums, int sum) {
		if(index>=nums.length) return sum;
		sum *= nums[index];
		//result.add(sum);
		int multi = DFS1(index + 1, nums, sum);
		int noMulti = DFS1(index + 1, nums, 1);
		return Math.max(multi,noMulti);
	}

	public int maxProduct2(int[] nums) {
		int max = nums[0];
		int index = 1; //index 控制几个连续的数相乘

		while(index <= nums.length){

			for(int i=0;i<=nums.length-index;i++){
				int product = 1;
				for(int j=0;j<index;j++){
					product *= nums[i+j]; // i+j 表示循环后续的数相乘
				}

				if(product > max)
					max = product;
			}
			index ++;
		}
		return max;
	}

	@Test
	public void test01(){
		int[] nums={2,3,-4,-5};
		int i = maxProduct(nums);
		System.out.println(i);
	}
}
