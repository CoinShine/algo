package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 若第i个状态dp[i]代表以第i个元素结尾的最长上升子序列的长度
 * dp[i-1]为以第i-1个元素结尾的最长子序列的长度
 * nums[i]是dp[i]所对应的最长上升子序列中最大的元素 dp[0]=1
 * j<i且nums[j] < nums[i],初始化最长子序列长度为LIS=1
 * 则有dp[i] = max(dp[j])+1,返回dp中最大的
 * @author shine
 * @date 2019/9/27 14:55
 * @version 1.0
 */
public class Test300 {
	/**
	 * 时间复杂度O(n^2)
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS(int[] nums) {
		if(nums.length==0) return 0;
		int[] dp = new int[nums.length];
		dp[0] = 1;
		int LIS = 1; // 初始化最长子序列的长度为1
		for (int i=1;i<nums.length;i++){
			for (int j=0;j<i;j++){
				if(nums[i]>nums[j] && dp[j]+1>dp[i]){
					dp[i] = dp[j]+1;
				}
			}
			dp[i] = dp[i] == 0 ? 1 : dp[i]; // 如果不是上升序列则记为1
			if (dp[i] > LIS) {
				LIS = dp[i]; // 记录最长的序列
			}
		}
		return LIS;
	}

	/**
	 * 使用栈实现 stack[i] 表示长度为i+1的上升序列的最后一个元素的最小可能取值
	 * 若要组成i+2个上升子序列，则需要一个大于stack[i]的元素，最终栈的大小即为
	 * 子序列的长度
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS02(int[] nums) {
		if(nums.length == 0) return 0;
		List<Integer> list = new ArrayList<>();
		list.add(nums[0]);
		for (int i = 1; i < nums.length; i++) { // 如果新元素比栈顶元素小 添加
			if(nums[i]>list.get(list.size()-1))
				list.add(nums[i]);
			else{
				for (int j = 0; j < list.size(); j++) { // 新元素比栈顶元素大，则从栈低遍历，当栈中元素大于等于新元素时，使用新元素替换，跳出循环
					if(nums[i]<=list.get(j)){
						list.set(j,nums[i]);
						break;
					}
				}
			}
		}
		return list.size();
	}


	/**
	 * 优化 O(nlogn)
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS03(int[] nums) {
		if(nums.length == 0) return 0;
		List<Integer> list = new ArrayList<>();
		list.add(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if(nums[i]>list.get(list.size()-1))
				list.add(nums[i]);
			else{
				int pos = binary_search(list, nums[i]);
				list.set(pos,nums[i]);
			}
		}
		return list.size();
	}

	/**
	 * 使用二分查找要插入的位置
	 * @param nums
	 * @param target
	 * @return
	 */
	private int binary_search(List<Integer> nums,int target){
		int begin = 0;
		int end = nums.size()-1;
		int mid=0;
		while (begin<=end){
		mid = (begin+end)/2;
			if(target == nums.get(mid)){
				return mid;
			}else if(target<nums.get(mid)){
				end = mid-1;
			}else{
				begin = mid+1;
			}
		}
		return target > nums.get(mid) ? mid + 1 : mid;
	}


	@Test
	public void test01(){
		//int[] nums = {1,3,2,3,1,4};
		int[] nums = {4,10,4,3,8,9};
		System.out.println(lengthOfLIS03(nums));
	}
}
