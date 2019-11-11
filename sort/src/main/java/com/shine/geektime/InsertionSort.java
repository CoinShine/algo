package com.shine.geektime;

/**
 * DESCRIPTION: 插入排序
 * 不断地将尚未排好序的数插入到已经排好序的部分
 * 在冒泡排序中，经过每一轮的排序处理后，数组后端的数是排好序的；
 * 而对于插入排序来说，经过每一轮的排序处理后，数组前端的数都是排好序的
 * 时间复杂度和空间复杂度同冒泡排序
 * @author Shine
 * @create 2019/11/11 19:16
 */
public class InsertionSort {

	public void sort(int[] nums) {
		// 将数组的第一个元素当作已经排好序的，从第二个元素，即 i 从 1 开始遍历数组
		for (int i = 1, j, current; i < nums.length; i++) {
			// 外围循环开始，把当前 i 指向的值用 current 保存
			current = nums[i];

			// 指针 j 内循环，和 current 值比较，若 j 所指向的值比 current 值大，则该数右移一位
			for (j = i - 1; j >= 0 && nums[j] > current; j--) {
				nums[j + 1] = nums[j];
			}

			// 内循环结束，j+1 所指向的位置就是 current 值插入的位置
			nums[j + 1] = current;
		}
	}
}
