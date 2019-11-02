package com.shine.other.leetcode;

/**
 * description: 求平方根
 * 分析：1、因为 y=x^2单调递增，可以使用二分查找
 * 		 2、使用牛顿迭代法
 * @author shine
 * @date 2019/11/2 11:35
 * @version 1.0
 */
public class Test69 {

	public Double mySqrt(int c) {
		if (c < 0) {
			return Double.NaN;
		}
		double e = 1e-10;
		double x = c;
		double y = (x + c / x) / 2;
		while (Math.abs(x - y) > e) {
			x = y;
			y = (x + c / x) / 2;
		}
		return x;
	}

}
