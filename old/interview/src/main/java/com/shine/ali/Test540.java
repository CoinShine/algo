package com.shine.ali;

/**
 * description:有序数组中的单一元素
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 * 示例 1:
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [3,3,7,7,10,11,11]
 * 输出: 10
 * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
 *
 * @author shine
 * @version 1.0
 * @date 2019/11/19 11:44
 */
public class Test540 {
	public int singleNonDuplicate(int[] nums) {
		int l = 0, r = nums.length - 1;
		while (l < r) {
			int h = (r + l) / 2;
			if ((h & 1)  == 1) {
				if (nums[h] == nums[h + 1]) r = h - 1;
				else l = h + 1;
			} else {
				if (nums[h] == nums[h + 1]) l = h + 2;
				else r = h;
			}
		}
		return nums[l];
	}
}
