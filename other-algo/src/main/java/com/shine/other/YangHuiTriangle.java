package com.shine.other;

import java.util.Scanner;

/**
 * DESCRIPTION:等腰三角形打印
 *
 * @author shine
 * @create 2019-06-22 18:20
 */
public class YangHuiTriangle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入杨辉三角的行数:");
		int n = scanner.nextInt();

		int[][] arrays = new int[n][];
		for (int i = 0; i < arrays.length; i++) {
			//左边打印空格，打印杨辉三角形
			for (int k = 0; k <= n - i; k++) {
				System.out.print("  ");
			}
			// 给每个二维数组元素赋值一维数组
			arrays[i] = new int[i + 1];
			//遍历一维数组
			for (int j = 0; j < arrays[i].length; j++) {
				//第一个元素和最后一个元素的值都是1
				if (j == 0 || j == arrays[i].length - 1) {
					arrays[i][j] = 1;
				} else {
					//当前一维数组的索引n元素的值，等于前一个数组索引n-1，加上索引n的值
					arrays[i][j] = arrays[i - 1][j - 1] + arrays[i - 1][j];
				}
				//格式化输出元素值
				System.out.printf("%4d", arrays[i][j]);
			}
			//换行
			System.out.println();
		}
	}
}

