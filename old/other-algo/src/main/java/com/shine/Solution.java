package com.shine;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION:
 *
 * @author Shine
 * @create 2019/9/17 10:01
 */
public class Solution {

	@Test
	public void test01(){
		int[] nums = {1,1,2,-1};
		int[] ints = twoSum(nums, 5);
		System.out.println(Arrays.toString(ints));


	}

	public int[] twoSum(int[] nums, int target) {
		int length = nums.length;
		Map<Integer,Integer> map = new HashMap<>();
		for (int i = 0; i < length; i++) {
			int num1 = nums[i];
			int num2 = target - num1;
			if(map.containsKey(num2)){
				return new int[]{i,map.get(num2)};
			}
			map.put(num1,i);
		}
		return new int[]{};
	}


	int maxProfit(int[] prices) {
		int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
		int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
		for (int price : prices) {
			dp_i20 = Math.max(dp_i20, dp_i21 + price);
			dp_i21 = Math.max(dp_i21, dp_i10 - price);
			dp_i10 = Math.max(dp_i10, dp_i11 + price);
			dp_i11 = Math.max(dp_i11, -price);
		}
		return dp_i20;
	}

	@Test
	public void test02(){
		String str = "google";
		char[] chars = str.toCharArray();
		int i = 0,j=chars.length-1;
		while(i<j){
			char temp = chars[i];
			chars[i] = chars[j];
			chars[j] = temp;
			i++;
			j--;
		}
		System.out.println(new String(chars));
	}
}
