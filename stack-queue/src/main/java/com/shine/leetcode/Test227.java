package com.shine.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * DESCRIPTION:基本计算器 II
 *
 * 一般需要符号栈、数据栈，两个。但是，看到网上一个写的不错的算法，只用了一个数据栈。
 * 符号栈用一个变量sign代替了，只存储上一个符号，主要思想如下：
 *
 * 1.将减法转化为加法（取相反数）
 *
 * 2.由于乘除法优先级高，直接计算
 *
 * 3.整数不仅一位，会>10
 *
 * 4.表达式中没有括号
 * 加减乘除空格的ASCII码都小于'0'，ASCII对照表如下：http://tool.oschina.net/commons?type=4
 *
 * 先做减法，避免int溢出
 * @author Shine
 * @create 2019/11/7 17:00
 */
public class Test227 {
	public int calculate(String s) {
		int res = 0, d = 0;
		char sign = '+';
		Stack<Integer> nums = new Stack<>();
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) >= '0') {
				d = d * 10 - '0' + s.charAt(i);
			}
			if ((s.charAt(i) < '0' && s.charAt(i) != ' ') || i == s.length() - 1) {
				if (sign == '+') {
					nums.push(d);
				} else if (sign == '-') {
					nums.push(-d);
				} else if (sign == '*' || sign == '/') {
					int tmp = sign == '*' ? nums.peek() * d : nums.peek() / d;
					nums.pop();
					nums.push(tmp);
				}
				sign = s.charAt(i); //保存当前符号
				d = 0;
			}
		}
		for (; !nums.empty(); nums.pop()) {
			res += nums.peek();
		}
		return res;
	}

	@Test
	public void test01() {
		//String s = "100000000/1/2/3/4/5/6/7/8/9/10";
		//String s = "3-2-1";
		String s = "21*3+4";
		int calculate = calculate(s);
		System.out.println(calculate);
	}
}
