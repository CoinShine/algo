package com.shine.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * description: 验证出栈序列是否合法
 * 每个数字从1到n入栈，入栈后即可出栈，也可以停留
 * 等后边数字入栈出栈后在出栈，判断出栈顺序是否合法
 * 比如 入栈为 1 2 3 4 5  出栈为 3 2 5 4 1 合法
 * 入栈为 1 2 3 4 5 出栈为 3 1 2 4 5 则不合法
 *
 * @author shine
 * @version 1.0
 * @date 2019/09/05 17:59
 */
public class Test946 {
	private Stack<Integer> stack = new Stack<>();

	public boolean validateStackSequences(int[] pushed, int[] popped) {
		if (pushed.length != popped.length) return false;
		int length = popped.length;
		int j = 0;
		for (int i = 0; i < length; i++) {
			stack.push(pushed[i]);//元素入栈
			while(!stack.isEmpty() && stack.peek() == popped[j]){
				j++;
				stack.pop();
			}
		}
		return stack.isEmpty();
	}

	@Test
	public void test01() {
		int[] pushed = {1, 2, 3, 4, 5};
		int[] popped = {3, 1, 2, 4, 5};
		boolean b = validateStackSequences(pushed, popped);
		System.out.println(b);
	}
}
