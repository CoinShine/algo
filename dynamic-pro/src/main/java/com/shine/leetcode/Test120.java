package com.shine.leetcode;

import org.junit.Test;

import java.util.List;

/**
 * description: 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 使用动态规划解决问题，求三角形的路劲和，从上到下遍历和从下到上遍历
 * 结果相同，但从下到上遍历时，指向考虑下方元素和右下方元素，情况比较简单
 * 1.设置三角形二维数组dp[][]为最优解三角形，dp[i][j]代表了从底到上走到第i行第j列
 * 时的最优解
 * 2.从三角形底部到上方进行动态规划 a、边界条件 底面的最优解就是三角形的最后一行
 *   b、dp[i][j]可以由下方和右下方元素得到，dp[i][j] = min(dp[i+1][j],dp[i+1][j+1]) + triangle[i][j]
 * 3.返回dp[0][0]
 * @author shine
 * @date 2019/9/27 10:24
 * @version 1.0
 */
public class Test120 {
	public int minimumTotal(List<List<Integer>> triangle) {
		if(triangle.size()==0) return 0;
		int size = triangle.size(); // 因为是三角形，所以下边应该比上边长
		List<Integer> low = triangle.get(size - 1);
		int[][] dp = new int[size][low.size()]; // 记录最优解二维数组，初始化都为0
		int[] bottom = dp[size - 1];
		for (int i = 0 ;i < low.size();i++){
			bottom[i]=low.get(i); // 将三角形的最后一行赋值
		}
		for (int i = size-2; i >=0 ; i--) {
			for (int j = 0; j <= i; j++) { // 第i行 有i个元素
				dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j);
			}
		}
		return dp[0][0];
	}

	@Test
	public void test01(){
		List<Integer> a = List.of(2);
		List<Integer> b = List.of(3,4);
		List<Integer> c = List.of(6,5,7);
		List<Integer> d = List.of(4,1,8,3);
		List<List<Integer>> triangle = List.of(a,b,c,d);
		int i = minimumTotal(triangle);
		System.out.println(i);

	}
}
