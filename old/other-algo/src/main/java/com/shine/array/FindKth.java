package com.shine.array;

import org.junit.Test;

/**
 * DESCRIPTION:使用快排求未排序数组的第k大的数
 * 时间复杂度为什么是 O(n)。分析如下。
 *
 * 为了方便推算，假设每次都选择中间的那个数作为基准值。
 *
 * 设函数的时间执行函数为 T(n)，第一次运行的时候，把基准值和所有的 n 个元素进行比较，然后将输入规模减半并递归，所以 T(n) = T(n/2) + n。
 *
 * 当规模减半后，新的基准值只和 n/2 个元素进行比较，因此 T(n/2) = T(n/4) + n/2。
 *
 * 以此类推：
 * T(n/4) = T(n/8) + n/4
 * …
 * T(2) = T(1) + 2
 * T(1) = 1
 *
 * 将上面的公式逐个代入后得到 T(n) = 1 + 2 + … + n/8 + n/4 + n/2 + n = 2×n，所以  O(T(n)) = O(n)。
 * @author Shine
 * @create 2019/11/12 18:29
 */
public class FindKth {
	public int findKthLargest(int[] nums, int k) {
		return quickSelect(nums, 0, nums.length - 1, k);
	}

	// 随机取一个基准值，这里取最后一个数作为基准值
	int quickSelect(int[] nums, int low, int high, int k) {
		int pivot = low;

		// 比基准值小的数放左边，把比基准值大的数放右边
		for (int j = low; j < high; j++) {
			if (nums[j] <= nums[high]) {
				swap(nums, pivot++, j);
			}
		}
		swap(nums, pivot, high);

		// 判断基准值的位置是不是第 k 大的元素
		int count = high - pivot + 1;
		// 如果是，就返回结果。
		if (count == k) return nums[pivot];
		// 如果发现基准值小了，继续往右边搜索
		if (count > k) return quickSelect(nums, pivot + 1, high, k);
		// 如果发现基准值大了，就往左边搜索
		return quickSelect(nums, low, pivot - 1, k - count);

	}

	private void swap(int[] nums, int pivot, int high) {
		int temp = nums[high];
		nums[high] = nums[pivot];
		nums[pivot] = temp;
	}

	@Test
	public void test01(){
		int[] nums ={2, 5, 3, 1, 6, 8, 9, 7, 4};
		System.out.println(findKthLargest(nums,5));

	}
}
