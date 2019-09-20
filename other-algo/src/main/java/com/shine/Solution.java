package com.shine;

import org.junit.Test;

/**
 * DESCRIPTION:
 *
 * @author Shine
 * @create 2019/9/17 10:01
 */
public class Solution {

	@Test
	public void test01(){
		int n = 100;
		int[] res = new int[n];
		res[0] = 1;
		int i2 = 0,i3=0,i5=0; //定义2 3 5三个指针
		for (int i = 1; i < n; i++) {
			int min = Math.min(res[i2]*2,Math.min(res[i3]*3,res[i5]*5));
			if(min == res[i2]*2) i2++;
			if(min == res[i3]*3) i3++;
			if(min == res[i5]*5) i5++;
			res[i] = min;
		}
		System.out.println(res[n-1]);


	}
}
