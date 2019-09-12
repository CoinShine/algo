package com.shine.array.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description: 组合数之和
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 在递归过程中将 大于目标集合剪掉
 *
 * @author shine
 * @version 1.0
 * @date 9/12/2019 5:23 PM
 */
public class Test40 {

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		Set<List<Integer>> result = new HashSet<>();
		List<Integer> item = new ArrayList<>();
		generate(0, candidates, item, result, target,0);
		return new ArrayList<>(result);
	}

	public void generate(int i, int[] nums, List<Integer> item, Set<List<Integer>> result, int target,int sum) {
		if (i >= nums.length || sum>target ) return;
		sum+=nums[i];
		item.add(nums[i]);
		if(sum==target){
			result.add(new ArrayList<>(item));
		}
		generate(i + 1, nums, item, result, target,sum);
		item.remove(item.size() - 1);  // 回溯
		sum-=nums[i];
		generate(i + 1, nums, item, result, target,sum);
	}

	@Test
	public void test01(){
		int[] canditions = {10,1,2,7,6,1,5};
		List<List<Integer>> lists = combinationSum2(canditions, 8);
		System.out.println(lists.toString());
	}
}
