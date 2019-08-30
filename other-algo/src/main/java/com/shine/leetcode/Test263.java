package com.shine.leetcode;


/**
 * description:
 * 编写一个程序判断给定的数是否为丑数。
 *
 * 丑数就是只包含质因数 2, 3, 5 的正整数
 * @author shine
 * @date 2019/08/29 16:12
 * @version 1.0
 */
public class Test263 {
	public boolean isUgly(int num) {
		if (num <= 0) return false;
		while (num != 1) {
			if (num % 5 == 0) {
				num = num / 5;
			} else if (num % 3 == 0) {
				num = num / 3;
			} else if (num%2==0) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
}
