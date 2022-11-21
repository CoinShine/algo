package com.shine.geektime;

/**
 * description:冒泡排序
 * 在进行新一轮的比较中，判断一下在上一轮比较的过程中有没有发生两两交换，
 * 如果一次交换都没有发生，就证明其实数组已经排好序了
 * 时间复杂度：最好O（N） 最坏O(N^2) 平均O(N^2)
 * 空间复杂度：O(1)
 * @author shine
 * @date 2019/11/11 19:08
 * @version 1.0
 */
public class BubbleSort {
	public void sort(int[] nums) {
		//定义一个布尔变量 hasChange，用来标记每轮遍历中是否发生了交换
		boolean hasChange = true;

		//每轮遍历开始，将 hasChange 设置为 false
		for (int i = 0; i < nums.length - 1 && hasChange; i++) {
			hasChange = false;

			//进行两两比较，如果发现当前的数比下一个数还大，那么就交换这两个数，同时记录一下有交换发生
			for (int j = 0; j < nums.length - 1 - i; j++) {
				if (nums[j] > nums[j + 1]) {
					swap(nums, j, j + 1);
					hasChange = true;
				}
			}
		}
	}
	private void swap(int[] nums,int i,int j){
		int num = nums[i];
		nums[i] = nums[j];
		nums[j] = num;
	}
}
