package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * description:滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 使用双端队列Deque的思想，最左侧一直保留滑动窗口中最大的元素，当向右滑动的过程中，
 * 出现比最左侧大的元素，则右移
 * @author shine
 * @date 2019-10-09 23:30
 * @version 1.0
 */
public class Test239 {
	public int[] maxSlidingWindow(int[] nums, int k) {
		if(nums.length == 0) return nums;
		Deque<Integer> deque = new ArrayDeque<>();// 设置滑动窗口的大小，记录nums的角标
		int size = Math.max(nums.length - k, 0);
		int[] res = new int[size+1]; // 结果集
		for (int i = 0; i < nums.length; i++) {
			if(i>=k && deque.getFirst() <=i-k){
				deque.removeFirst();
			}
			while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) // 添加新元素，如果新元素比之前窗口左边的大，则移除左边元素
				deque.removeLast();
			deque.add(i);
			if(i>=k-1)
				res[i-k+1] = nums[deque.getFirst()];
		}
		return res;
	}

	@Test
	public void test01(){
		int[] nums = {1,3,1,2,0,5};
		int[] ints = maxSlidingWindow(nums, 3);
		System.out.println(Arrays.toString(ints));

	}
}
