package com.shine.other;

import java.util.Scanner;

/**
 * DESCRIPTION:递归实现杨辉三角
 *
 * @author shine
 * @create 2019-06-22 19:45
 */
public class YangHuiSanJiao {
	public static void main(String[] args) {
		System.out.println("请输入要打印的行数:");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		for (int i = 0; i <n ; i++) {
			for (int j = 0; j <= n-i ; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j <= i ; j++) {
				System.out.print(digui(i,j)+" ");
			}
			System.out.println();
		}
	}

	private static int digui(int i,int j){
		if(j==0||j==i)
			return 1;
		else
			return digui(i-1,j)+digui(i-1,j-1);
	}
}
