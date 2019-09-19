package com.shine.leetcode;

import org.junit.Test;

/**
 * description:假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。你的算法时间复杂度必须是 O(log n) 级别。
 * 画图分析 判断mid和begin 如果mid<begin 则 旋转部分在前半部分
 * 如果mid>begin 则旋转部分在后半部分
 * @author shine
 * @date 2019/9/18 15:18
 * @version 1.0
 */
public class Test33 {
	public int search(int[] nums, int target) {
		int low = 0;
		int high = nums.length - 1;
		while(low<=high){
			int mid = low+((high-low)>>1);
			if(target == nums[mid]){
				return mid;
			}else if(nums[mid] > target){ // 判断旋转数组的情况
				if(nums[mid] > nums[low]){ // 说明旋转部分在后半部分
					if(target>=nums[low]){
						high = mid-1;
					}else{
						low = mid+1;
					}
				}else if (nums[mid] < nums[low]){ // 说明旋转部分在前半部分
						high = mid-1;
				}else{
					low = mid+1;
				}

			}else{
				if(nums[mid] > nums[low]){ // 说明旋转部分在后半部分
					low = mid+1;
				}else if (nums[mid] < nums[low]){ // 说明旋转部分在前半部分
					if(nums[high]>=target){
						low=mid + 1;
					}else{
						high = mid-1;
					}
				}else{
					low = mid+1;
				}
			}
		}
		return -1;
	}
	@Test
	public void test01(){
		int[] nums =new int[]{6,7,0,1,2,3,4,5};
		int search = search(nums, 8);
		System.out.println(search);
	}
}
