package com.shine.leetcode;

import org.junit.Test;

/**
 * DESCRIPTION:求第n个丑数
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 使用三指针法，另res[0]=1,每次乘以2，3，5,每次取出最小的一个数
 * 然后指针向后移动
 *
 * @author shine
 * @create 2019-08-19 23:39
 */
public class Test264 {
	public int nthUglyNumber(int n) {

		int[] res = new int[n];
		res[0]=1;
		int i2=0,i3=0,i5=0;
		for (int i = 1; i < n ; i++) {
			int min = Math.min(res[i2] * 2, Math.min(res[i3] * 3, res[i5] * 5));
			if(min==res[i2] * 2)
				i2++;
			if(min == res[i3]*3)
				i3++;
			if(min == res[i5]*5)
				i5++;
			res[i]=min;
		}
		return res[n-1];

	}
	@Test
	public void test01(){
		int i = nthUglyNumber(10);
		System.out.println(i);
	}
}
