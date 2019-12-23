package com.shine.geektime;


import org.junit.Test;

import java.util.Arrays;

/**
 * description: 归并排序
 * @author shine
 * @date 2019/08/29 15:26
 * @version 1.0
 */
public class MergeSort {


	// 归并排序算法, a是数组，n表示数组大小
	public  int[] mergeSort(int[] a) {
		return mergeSortInternally(a, 0, a.length-1);
	}

	// 递归调用函数
	private  int[] mergeSortInternally(int[] a, int low, int high) {
		// 递归终止条件
		if (low == high) return new int[]{a[low]};
		// 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
		int mid = low + ((high - low) >> 1);
		// 分治递归
		int[] left = mergeSortInternally(a, low, mid);
		int[] right = mergeSortInternally(a, mid + 1, high);
		// 将A[p...q]和A[q+1...r]合并为A[p...r]
		return merge(left,right);
	}

	private int[] merge(int[] left,int[] right) {
		int i = 0,j=0;
		int l1 = left.length;
		int l2 = right.length;
		int k = 0; // 初始化变量i, j, k
		int[] tmp = new int[l1+l2]; // 申请一个大小跟a[p...r]一样的临时数组
		while (i < l1 && j < l2) {
			if (left[i] <= right[j]) {
				tmp[k++] = left[i++]; // i++等于i:=i+1
			} else {
				tmp[k++] = right[j++];
			}
		}

		while (i<l1){
			tmp[k++] = left[i++];
		}
		while (j<l2){
			tmp[k++] = right[j++];
		}
		return tmp;
	}

	@Test
	public void test01(){
		int[] nums = {8,9,1,10,5,3,2,4,7,6};
		int[] a = {1,3,5,6};
		int[] b = {2,4};
		int[] ints = mergeSort(nums);
		System.out.println(Arrays.toString(ints));
	}
}
