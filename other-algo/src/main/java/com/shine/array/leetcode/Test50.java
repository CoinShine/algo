package com.shine.array.leetcode;

import org.junit.Test;

/**
 * description:Pow(x, n)
 * 递归分治法
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
		if(n<0){
			x=1/x;
			n=-n;
		}
		int pow =1;
		while(n>0){
			if((n&1)==1){ // 说明最低位为1
				pow *=x;
			}
			x*=x;
			n=n>>1;
		}
		return pow;
	}
	@Test
	public void test01(){
		double v = myPow2(2, 8);
		System.out.println(v);
	}
}
