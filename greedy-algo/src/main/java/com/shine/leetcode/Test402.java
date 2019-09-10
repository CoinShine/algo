package com.shine.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * description: 移除K个数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 分析：去掉k位，组成有C(k,n)中，考虑用贪心算法或动态规划
 * 从最高位往低位遍历，如果高位大于低位，则可以移除
 * 可以借助栈，将保留下来的最高位存起来
 *
 * @author shine
 * @version 1.0
 * @date 9/10/2019 5:11 PM
 */
public class Test402 {

	public String removeKdigits(String num, int k) {
		StringBuilder sb = new StringBuilder();// 定义结果
		Stack<Character> stack = new Stack<>(); //存储结果
		if ("".equals(num)) return "0";
		int length = num.length();
		for (int i = 0; i < length; i++) {
			char c = num.charAt(i);
			while (k > 0 && !stack.empty() && c < stack.peek()) {
				// 移除栈顶元素
				stack.pop();
				k--;
			}
			if (!stack.empty() || c != '0') { //关于0的处理
				stack.push(c);
			}
		}
		// 如果遍历完，k仍然大于0，则继续移除
		while (k > 0 && !stack.isEmpty()) {
			stack.pop();
			k--;
		}
		//处理结果
		while (!stack.isEmpty()) {
			sb.insert(0, stack.pop());
		}
		if(sb.toString().equals("")){
			return "0";
		}
		return sb.toString();
	}


	@Test
	public void test01() {
		String s = "100200";
		//char c = s.charAt(0);
		//char c1 = s.charAt(1);
		//boolean b = c > c1;
		//System.out.println(b);
		//StringBuilder sb = new StringBuilder();
		//sb.append(c);
		//sb.append(c1);
		//System.out.println(sb.toString());
		int k = 1;
		String s1 = removeKdigits(s, k);
		System.out.println(s1);
	}
}