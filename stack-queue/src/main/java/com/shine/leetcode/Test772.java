package com.shine.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * description:基本计算器 III
 * 在调用递归的时候去找到匹配的括号，这里有一个小技巧，使用一个全局的pointer，所有的递归调用的函数共享一个pointer。 递归是确保了括号的顺序性。确保内层括号先于外层括号去计算。
 * 递归返回的结果是每一个括号内的计算结果。抛去这些括号不考虑。 而我们自己创建的栈就是去保证/*的运算规则 先于+-号的计算。 这里就和basic caculator II 是一致的了。
 * 遇到负号存负值 ，正号存正值。只有乘除的时候去算(栈压缩，确保了乘除先算))， 最后把所有站内结果相加即可（加减后算）。
 * @author shine
 * @date 2019/11/7 17:55
 * @version 1.0
 */
public class Test772 {
	private static Set<Character> operations;
	private Integer idx=-1;

	static {
		operations = new HashSet<>();
		operations.add('+');
		operations.add('-');
		operations.add('*');
		operations.add('/');
	}

	private int calculate(String s) {
		Deque<Integer> stack = new ArrayDeque<>();

		int temp = 0;
		Character sign = '+';
		stack.push(temp);
		while (++idx < s.length()) {
			char ch = s.charAt(idx);
			if (ch == ' ') continue;

			if (ch == '(') {
				int innerVal = this.calculate(s);
				this.evaluate(stack, sign, innerVal);
			} else if (ch == ')') {
				break;
			} else if (operations.contains(ch)) {
				sign = ch;
			} else {
				temp = 0;
				while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
					temp = temp * 10 + s.charAt(idx) - '0';
					idx++;
				}
				idx -= 1;
				this.evaluate(stack, sign, temp);
			}
		}

		int res = 0;
		for (int num : stack) res += num;

		return res;

		// 可以用stream api操作。
		//return stack.stream().reduce(0,(x,y)->x+y);
	}

	private void evaluate(Deque<Integer> stack, Character sign, Integer temp) {
		if (sign == '+') {
			stack.push(temp);
		} else if (sign == '-') {
			stack.push(0 - temp);
		} else if (sign == '*') {
			stack.push(stack.pop() * temp);
		} else if (sign == '/') {
			stack.push(stack.pop() / temp);
		} else {
			throw new IllegalArgumentException("invalid operations");
		}

	}
}
