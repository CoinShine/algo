package com.shine.geektime;

import java.util.Arrays;

/**
 * description: 快排
 *
 * @author shine
 * @version 1.0
 * @date 2019/08/29 15:27
 */
public class QuickSort {

	// 快速排序，a是数组，n表示数组的大小
	public static void quickSort(int[] a, int n) {
		quickSortInternally(a, 0, n - 1);
	}

	// 快速排序递归函数，p,r为下标
	private static void quickSortInternally(int[] a, int l, int r) {
		if (l >= r) return;
		int q = partition(a, l, r); // 获取分区点 一次分区后 左侧小于基准数 右侧大于基准数
		quickSortInternally(a, l, q - 1); // 分治
		quickSortInternally(a, q + 1, r);
	}
	/**
	 * 挖坑填坑
	 *
	 * @param a
	 * @param l
	 * @param r
	 * @return
	 */
	private static int partition(int[] a, int l, int r) {
		int i = l, j = r;
		int x = a[i]; // 基准数，可以选择头尾中三数取中
		while (i < j) {
			while (i < j && a[j] >= x){
				j--; // 从右侧遍历填坑
			}
			if (i < j){
				a[i++] = a[j];
			}
			while (i < j && a[i] < x) {
				i++; //从左侧遍历填坑
			}
			if (i < j){
				a[j--] = a[i];
			}
		}
		// 退出时i==j
		a[i] = x;
		return i;
	}

	public static void main(String[] args) {
		int[] a = {23, 46, 0, 8, 11, 18};
		//int[] b = {6, 6, 6, 6, 6, 6};
		quickSort(a, 6);
		System.out.println(Arrays.toString(a));
	}

}
