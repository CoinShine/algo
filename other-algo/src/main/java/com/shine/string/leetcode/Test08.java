package com.shine.string.leetcode;

/**
 * DESCRIPTION:请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * <p>
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * <p>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。如果数值超过这个范围，返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
 *
 * @author shine
 * @create 2019-05-05 10:50
 */
public class Test08 {
	public int myAtoi(String str) {
		String s = str.trim();
		if (s.length() == 0)
			return 0;
		if (!(s.charAt(0) == '+' || s.charAt(0) == '-' || Character.isDigit(s.charAt(0))))
			return 0;
		long res = 0;
		boolean flag = false;
		if (s.charAt(0) == '-') {
			flag = true;
			s = s.substring(1);
		} else if (s.charAt(0) == '+') s = s.substring(1);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!Character.isDigit(c)) break;
			res = res * 10 + (c - '0');
			if (res > Integer.MAX_VALUE) break;
		}
		if (res > Integer.MAX_VALUE) return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		if (res == 0) return 0;
		return flag ? -(int) res : (int) res;
	}
}
