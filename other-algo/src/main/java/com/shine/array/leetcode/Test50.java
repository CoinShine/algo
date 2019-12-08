package com.shine.array.leetcode;

import org.junit.Test;

/**
 * description:Pow(x, n) 实现 pow(x, n) ，即计算 x 的 n 次幂函数
 * 递归分治法
 * 分析：分为递归法和非递归法，面试中推荐使用非递归的方法
 * @author shine
 * @date 2019-10-23 22:35
 * @version 1.0
 */
public class Test50 {
	public double myPow(double x, int n) {
		if(n==0) return 1;
		if(n<0) return 1/myPow(x,-n);
		if(n%2!=0){ // 说明是奇数
			return x*myPow(x*x,n/2);
		}else {
			return myPow(x*x,n/2);
		}
	}


	public double myPow2(double x, int n) {
		long n1 = n;
		if(n1<0){
			x=1/x;
			n1=-n1;
		}
		double pow =1;
		while(n1>0){
			if((n1&1)==1){ // 说明最低位为1
				pow *=x;
			}
			x*=x;
			n1=n1>>1;
		}
		return pow;
	}
	@Test
	public void test01(){
		double v = myPow(2,
				-2147483648);
		System.out.println(v);
	}
}
