package com.shine.hash.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description: 三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。w
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * 分析：方法一 暴力法 同两数求和 可得时间复杂度为O(N^3)
 * 		 方法二 借用hash表 实现 时间复杂度为O(N^2) 空间复杂度为O(N)
 * 		 方法三 不借用其他结构实现 先对数组排序，使用两头夹的思想，a+b+c = 0
 * 		 c = -(a+b) 首先遍历 得到a，然后在剩余元素中 双头夹 b从a的下一个位置开始
 * 		 c从末尾开始，如果a+b+c>0 说明c太大，反之说明b太小，当bc相遇时停止 时间复杂度O(N*N)
 * @author shine
 * @date 2019/10/22 11:14
 * @version 1.0
 */
public class Test15 {
	public List<List<Integer>> threeSum(int[] nums) {
		Set<List<Integer>> re = new HashSet<>();
		Arrays.sort(nums); // 排序后可以方便去除重复的序列
		int length = nums.length;
		for (int i = 0; i < length-1; i++) {
			int a = nums[i];
			if(i>1&&nums[i]==nums[i-1]){
				 continue;
			}
			Set<Integer> set = new HashSet<>();
			for (int j = i+1; j < length; j++) {
				int b = nums[j];
				if(!set.contains(b)){
					set.add(-a-b);
				}else{
					List<Integer> list = new ArrayList<>();
					list.add(a);
					list.add(b);
					list.add(-a-b);
					re.add(list);
				}
			}
		}
		return new ArrayList<>(re);
	}


	/**
	 * 使用两指针夹的方法
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum2(int[] nums) {
		Set<List<Integer>> re = new HashSet<>();
		Arrays.sort(nums);
		int length = nums.length;
		for (int i = 0; i < length-2; i++) {
			if(i>1&&nums[i]==nums[i-1]){ // 去重
				continue;
			}
			int a = nums[i];
			int m=i+1, n = length-1;
			while (m < n){
				if(a+nums[m]+nums[n] > 0 ){ // 说明n太大
					n--;
				}else if(a+nums[m]+nums[n] < 0){ // 说明m太小
					m++;
				}else{
					re.add(List.of(a,nums[m],nums[n]));
					m++;
					n--;
				}
			}

		}
		return new ArrayList<>(re);
	}

	@Test
	public void test01(){
		int[] nums ={-1,0,1,2,-1,-4};
		List<List<Integer>> lists = threeSum(nums);
		System.out.println(lists.toString());
	}
}
