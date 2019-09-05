package com.shine.leetcode;

import java.util.Stack;

/**
 * description:简单的计数器实现
 * 使用有限状态自动机进行处理
 *
 * @author shine
 * @version 1.0
 * @date 2019/09/05 19:31
 */
public class Test224 {
	private static final int BEGIN_STATE = 0;
	private static final int NUMBER_STATE = 1;
	private static final int OPERATION_STATE = 2;

	public int calculate(String s) {
		Stack<Integer> number_stack = new Stack<>();
		Stack<Character> operation_stack = new Stack<>();
		int flag = 0;//0表示可以计算 1表示不可以计算
		int state = BEGIN_STATE;
		int number = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') continue;
			switch (state) {
				case BEGIN_STATE:
					if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
						state = NUMBER_STATE;
					} else {
						state = OPERATION_STATE;
					}
					i--; //回退一步
					break;
				case NUMBER_STATE:
					if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
						number = number * 10 + s.charAt(i) - '0';
					} else {
						number_stack.push(number);
						if (flag == 1) {
							compute(number_stack, operation_stack);
						}
						number = 0;
						i--;
						state = OPERATION_STATE;
					}
					break;
				case OPERATION_STATE:
					if (s.charAt(i) == '+' || s.charAt(i) == '-') {
						operation_stack.push(s.charAt(i));
						flag = 1;
					} else if (s.charAt(i) == '(') {
						state = NUMBER_STATE;
						flag = 0;
					} else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
						state = NUMBER_STATE;
						i--;
					} else if (s.charAt(i) == ')') {
						compute(number_stack, operation_stack);
					}
					break;
			}
		}
		if (number != 0) {
			number_stack.push(number);
			compute(number_stack, operation_stack);
		}
		if (number == 0 && number_stack.isEmpty()) {
			return 0;
		}
		return number_stack.peek();
	}

	private void compute(Stack<Integer> number_stack, Stack<Character> operation_stack) {
		if (number_stack.size() < 2) {
			return;
		}
		int num2 = number_stack.pop();
		int num1 = number_stack.pop();
		if (operation_stack.peek() == '+') {
			number_stack.push(num1+num2);
		}else if(operation_stack.peek() == '-'){
			number_stack.push(num1-num2);
		}
		operation_stack.pop();
	}
}

