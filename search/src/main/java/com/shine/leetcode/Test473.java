package com.shine.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * description: 火柴摆正方形
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，
 * 请找出一种能使用所有火柴拼成一个正方形的方法。
 * 不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 * <p>
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。
 * 输出即为是否能用所有的火柴拼成正方形。
 * 可以使用位运算法和递归回溯法
 *
 * @author shine
 * @version 1.0
 * @date 2019-09-25 21:56
 */
public class Test473 {
	/**
	 * 位运算法  【1，1，2，4，3，1，3，1】 位运算先组合四分之一长度 然后两两相与为0，则能组合成两个边
	 * 将上边两两相或得到的结果，在进行两两相与，如果有为0的说明可以组成正方形
	 *
	 * @param nums
	 * @return
	 */
	public boolean makesquare(int[] nums) {
		if (nums.length < 4) return false; // 如果火柴数量小于4，那么不能摆成正方形
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if (sum % 4 != 0) return false;
		int target = sum / 4;
		List<Integer> quarter = new ArrayList<>();
		List<Integer> half = new ArrayList<>();
		int size = 1 << nums.length;
		for (int i = 0; i < size; i++) {
			sum = 0;
			for (int j = 0; j < nums.length; j++) {
				if ((i & (1 << j)) != 0) { // 说明数组中第j位放入
					sum += nums[j];
				}
			}
			if (target == sum) {
				quarter.add(i);
			}
		}

		for (int i = 0; i < quarter.size(); i++) {
			for (int j = i + 1; j < quarter.size(); j++) {
				if ((quarter.get(i) & quarter.get(j)) == 0) {
					half.add((quarter.get(i) | quarter.get(j)));
				}
			}
		}
		for (int i = 0; i < half.size(); i++) {
			for (int j = i + 1; j < half.size(); j++) {
				if ((half.get(i) & half.get(j)) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 递归回溯法
	 * 1.n个火柴杆对4取余等于0
	 * 2.每个边放置的火柴长度和不能大于总长的四分之一
	 * 3.将火柴从长到短排序，可以减少回溯次数
	 *
	 * @param nums
	 * @return
	 */
	public boolean makesquare2(Integer[] nums) {
		if (nums.length < 4) return false; // 如果火柴数量小于4，那么不能摆成正方形
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if (sum % 4 != 0) return false;
		int target = sum / 4;
		Arrays.sort(nums, Collections.reverseOrder());
		return generate(0,nums,target,new int[4]);

	}

	private boolean generate(int i, Integer[] nums, int target, int[] bucket) { // bucket表示四个边的桶
		if (i == nums.length)
			return bucket[0] == target && bucket[1] == target && bucket[2] == target && bucket[3] == target;
		for (int j = 0; j < 4; j++) {
			if(bucket[j]+nums[i]>target){
				continue;
			}
			bucket[j]+=nums[i];
			if(generate(i+1,nums,target,bucket)){
				return true;
			}
			bucket[j]-=nums[i];
		}
		return false;
	}

	@Test
	public void test01(){
		int[] nums = {1,1,2,2,2};
		boolean b = makesquare(nums);
		System.out.println(b);
	}

}
