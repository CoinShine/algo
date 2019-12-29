package com.shine.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * DESCRIPTION:字符串解码
 * 使用辅助栈
 * @author Shine
 * @create 2019/12/30 0:44
 */
public class Test394 {
	public String decodeString(String s) {
		StringBuilder res = new StringBuilder();
		int multi = 0;
		Stack <Integer> stack_multi = new Stack<>();
		Stack<String> stack_res = new Stack<>();
		for(Character c : s.toCharArray()) {
			if(c == '[') {
				stack_multi.push(multi);
				stack_res.push(res.toString());
				multi = 0;
				res = new StringBuilder();
			}
			else if(c == ']') {
				StringBuilder tmp = new StringBuilder();
				int cur_multi = stack_multi.pop();
				for(int i = 0; i < cur_multi; i++) tmp.append(res);
				res = new StringBuilder(stack_res.pop() + tmp);
			}
			else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
			else res.append(c);
		}
		return res.toString();
	}

	@Test
	public void test01(){
		String s = "3[a2[c]]";
		System.out.println(decodeString(s));
	}
}
