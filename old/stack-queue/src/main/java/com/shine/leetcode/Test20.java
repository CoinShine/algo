package com.shine.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * description: 判断字符串是否合法
 * 利用一个栈，不断地往里压左括号，一旦遇上了一个右括号，我们就把栈顶的左括号弹出来，
 * 表示这是一个合法的组合，以此类推，直到最后判断栈里还有没有左括号剩余。
 * @author shine
 * @date 2019/09/03 20:08
 * @version 1.0
 */
public class Test20 {

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		Map<Character,Character> map = new HashMap<>();
		map.put(')','(');
		map.put(']','[');
		map.put('}','{');

		int length = s.length();
		for (int i = 0; i < length; i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				char topElement = stack.empty() ? '#' : stack.pop();
				if (topElement != map.get(c)) {
					return false;
				}
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty();
	}

	@Test
	public void test01(){
		String s = "abc()";
		boolean valid = isValid(s);
		System.out.println(valid);
	}
}
