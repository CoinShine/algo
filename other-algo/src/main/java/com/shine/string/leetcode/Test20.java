package com.shine.string.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * description: 判断字符串是否合法
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
			if(map.containsValue(s.charAt(i))){
				stack.push(s.charAt(i));
			}else{
				if(stack.empty() || !map.get(s.charAt(i)).equals(stack.pop())){
					return false;
				}
			}
		}
		return stack.empty();
	}

	@Test
	public void test01(){
		String s = "(";
		boolean valid = isValid(s);
		System.out.println(valid);
	}
}
