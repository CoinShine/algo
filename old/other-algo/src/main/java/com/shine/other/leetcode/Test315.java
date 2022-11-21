package com.shine.other.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:计算右侧小于当前元素的个数
 * 采用分治和归并排序计算
 *
 * @author Shine
 * @create 9/16/2019 4:59 PM
 */
public class Test315 {

	public List<Integer> countSmaller(int[] nums) {
		List<Integer> re = new ArrayList<>();
		List<Integer> val = new ArrayList<>();
		List<Integer> index = new ArrayList<>();
		int[] count=new int[nums.length];
		// 绑定nums[i]和i  初始化count数组
		for (int i=0;i<nums.length;i++){
			count[i] = 0;
			val.add(nums[i]);
			index.add(i);
		}
		merge_sort(val,index,count);
		//将count数组转换为结果
		for (int i : count) {
			re.add(i);
		}
		return re;
	}

	/**
	 * 归并排序
	 * @param val
	 * @param index
	 * @param count
	 */
	private void merge_sort(List<Integer> val,List<Integer> index, int[] count) {
		if(val.size()<2) return;  // 分治 到最小单元即可返回
		int mid = val.size()/2; // 归并中位数
		List<Integer> val1 = new ArrayList<>(); // 左侧子序列
		List<Integer> index1 = new ArrayList<>();
		List<Integer> val2 = new ArrayList<>(); // 右侧子序列
		List<Integer> index2 = new ArrayList<>();
		// 初始化左右序列
		for(int i=0;i<mid;i++){
			val1.add(val.get(i));
			index1.add(index.get(i));
		}
		for(int j=mid;j<val.size();j++){
			val2.add(val.get(j));
			index2.add(index.get(j));
		}
		merge_sort(val1,index1,count);// 递归调用
		merge_sort(val2,index2,count);
		val.clear();// 清空原集合，存入排序之后的结果
		index.clear();
		merge_sort_two(val,index,val1,index1,val2,index2,count); // 将两个小序列合成一个大序列
	}

	private void merge_sort_two(List<Integer> val,List<Integer> index,List<Integer> val1,List<Integer> index1,List<Integer> val2,List<Integer> index2, int[] count) {
		int i=0;
		int j=0;
		while(i<val1.size()&&j<val2.size()){ // 如果两个子序列都没结束
			if(val1.get(i)<=val2.get(j)){  // 如果左侧小于等于右侧 将左侧元素放入结果，然后计数 右侧走过的index
				count[index1.get(i)]+=j;
				val.add(val1.get(i));
				index.add(index1.get(i));
				i++;
			}else{
				val.add(val2.get(j));
				index.add(index2.get(j));
				j++;
			}
		}
		for(;i<val1.size();i++){ // 说明左侧比右侧长，count需要计数
			count[index1.get(i)]+=j;
			val.add(val1.get(i));
			index.add(index1.get(i));
		}
		for(;j<val2.size();j++){ // 说明右侧比左侧长，count无需计数
			val.add(val2.get(j));
			index.add(index2.get(j));
		}
	}

	@Test
	public void test01(){
		int[] nums={26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
		List<Integer> integers = countSmaller(nums);
		System.out.println(integers.toString());
	}
}
