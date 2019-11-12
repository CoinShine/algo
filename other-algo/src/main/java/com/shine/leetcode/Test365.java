package com.shine.leetcode;

import org.junit.Test;

/**
 * DESCRIPTION: 水壶问题
 *
 * @author Shine
 * @create 2019/11/12 11:48
 */
public class Test365 {
	public boolean canMeasureWater(int x, int y, int z) {
		if(x+y==z) return true;
		if(z>x+y||z< 0) return false;
		int gcd = getGCD(x, y);
		return z%gcd==0;
	}

	/**
	 * 更相减损术，a和b数相差太大，可能导致递归的时间越来越长
	 * 两个正整数a和b（a>b），它们的最大公约数等于a-b的差值c和较小数b的最大公约数
	 * @param a
	 * @param b
	 * @return
	 */
	private int getGCD(int a,int b){
		if(a==0) return b;
		if(b==0) return a;
		int max= Math.max(a, b);
		int min= Math.min(a, b);
		int c=max-min;
		if(c==0){
			return min;
		}
		return getGCD(c,min);
	}
	@Test
	public void test01(){
		System.out.println(canMeasureWater(2,6,5));
	}
}
