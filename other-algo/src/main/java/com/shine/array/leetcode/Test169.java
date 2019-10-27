package com.shine.array.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 求众数
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 分析：方法一 两层循环 第一层遍历，第二层循环计数 时间复杂度 O(N^2)
 * 		方法二 使用Map 时间复杂度O(N) 空间复杂度O(N)
 * 		方法三 使用排序 然后计数 最快 时间复杂度 O(N·logN)
 * 		方法四 使用分治思想 先求左侧count最大值，再求右侧count最大值，返回较大者，时间复杂度 O(N·LogN)
 * @author shine
 * @date 2019-10-27 22:46
 * @version 1.0
 */
public class Test169 {
	public int majorityElement(int[] nums) {
		Map<Integer,Integer> numMap = new HashMap<>();
		int length = nums.length;
		for (int num : nums) {
			if (!numMap.containsKey(num)) {
				numMap.put(num, 1);
			}
			else {
				numMap.put(num, numMap.get(num)+1);
			}
		}
		int count=0;
		int key = 0;
		for (Map.Entry<Integer, Integer> entry : numMap.entrySet()) {
			if(entry.getValue()>count){
				count = entry.getValue();
				key = entry.getKey();
			}
		}
		return key;
	}

	@Test
	public void test01(){
		int[] nums = {3,2,3};
		int i = majorityElement(nums);
		System.out.println(i);
	}
}
