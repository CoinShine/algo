package com.shine.leetcode;

import org.junit.Test;

/**
 * DESCRIPTION: 水壶问题
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 *
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 *
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 裴蜀定理：
 * 若a,b是整数,且gcd(a,b)=d，那么对于任意的整数x,y,ax+by都一定是d的倍数，特别地，一定存在整数x,y，使ax+by=d成立。
 *
 * 本题先判断所需要的水量是否大于两个桶的容量之和，如果不大于，判断所需要的水量是否是两个桶容量的最大公约数的倍数，根据裴蜀定理可以证明：
 *
 * 如果所需要的水量是两个水壶容量的最大公约数的倍数，且水量不大于两个水壶的容量之和，那么必然可以用这两个水壶操作得到所需要的水量
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
