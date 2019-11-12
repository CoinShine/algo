package com.shine.geektime;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * DESCRIPTION:用非递归的方法去实现快速排序
 *
 * @author Shine
 * @create 2019/11/12 18:46
 */
public class QuickSort2 {
	//每次只需将数组中的某个起始点和终点，即一个范围，压入堆栈中，
	// 压入 30 个范围的大小约为 30×2×4=240 字节
	class Range {
		public int low;
		public int high;

		public Range(int low, int high) {
			this.low = low;
			this.high = high;
		}
	}

	// 不使用递归写法，压入堆栈的还包括程序中的其他变量等，假设需要 100 字节，总共需要 30×100=3K 字节
	void quickSort(int[] nums) {
		Stack<Range> stack = new Stack<>();

		Range range = new Range(0, nums.length - 1);
		stack.push(range);

		while (!stack.isEmpty()) {
			range = stack.pop();

			int pivot = partition(nums, range.low, range.high);

			if (pivot - 1 > range.low) {
				stack.push(new Range(range.low, pivot - 1));
			}

			if (pivot + 1 < range.high) {
				stack.push(new Range(pivot + 1, range.high));
			}
		}
	}

	// 快速排序对内存的开销非常小
	int partition(int[] nums, int low, int high) {
		int pivot = randRange(low, high), i = low;
		swap(nums, pivot, high);

		for (int j = low; j < high; j++) {
			if (nums[j] <= nums[high]) {
				swap(nums, i++, j);
			}
		}

		swap(nums, i, high);

		return i;
	}

	private int randRange(int low, int high) {
		return (high-low)/2+low;
	}

	private void swap(int[] nums, int pivot, int high) {
		int temp = nums[high];
		nums[high] = nums[pivot];
		nums[pivot] = temp;
	}


	@Test
	public void test01(){
		int[] nums = {2, 5, 3, 1, 6, 8, 9, 7, 4};
		quickSort(nums);
		System.out.println(Arrays.toString(nums));

	}
}
