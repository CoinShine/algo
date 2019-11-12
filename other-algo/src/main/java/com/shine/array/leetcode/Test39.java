package com.shine.array.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:组合总和  结合40题 经典的回溯题目
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 使用递归回溯剪枝
 *
 * @author Shine
 * @create 2019/11/12 9:42
 */
public class Test39 {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> re = new ArrayList<>();
		DFS(candidates,target,0,new ArrayList<>(),re);
		return re;
	}

	private void DFS(int[] candidates,int target,int start,List<Integer> solution,List<List<Integer>> result){
		if(target<0) return;
		if(target == 0){
			result.add(new ArrayList<>(solution));
			return;
		}
		// 因为每个元素可以选多次，所以这里使用循环
		for (int i = start; i <candidates.length ; i++) {
			solution.add(candidates[i]);
			DFS(candidates,target-candidates[i],i,solution,result);
			solution.remove(solution.size()-1);  // 回退到之前的状态
		}
	}


	@Test
	public void test01(){
		int[] candidates={2,3,6,7};
		System.out.println(combinationSum(candidates,7).toString());
	}
}
