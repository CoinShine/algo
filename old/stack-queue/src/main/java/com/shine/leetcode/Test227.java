package com.shine.leetcode;

import org.junit.Test;

import java.util.LinkedList;
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
			if (s.charAt(i) >= '0') { //加减乘除和空格ASCII码都小于'0'
				d = d * 10 - '0' + s.charAt(i);//进位(先减法)
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


	public double calculate1(String exp) {
		return calRst(backExp(exp));
	}
	// 计算后缀表达式
	public LinkedList<String> backExp(String exp) {

		Stack<String> operateStack = new Stack<>();
		LinkedList<String> backExp = new LinkedList<>();
		for (int i = 0; i < exp.length(); i++) {
			// 1.遇到了数字
			if (Character.isDigit(exp.charAt(i))) {
				// 注意多位数的获取
				int k = i + 1;
				for (; k < exp.length() && Character.isDigit(exp.charAt(k)); k++) {

				}
				backExp.add(exp.substring(i, k));
				i = k - 1;// 更新 i
				continue;
			}
			// 2.遇到了乘除运算符
			if (exp.charAt(i) == '/' || exp.charAt(i) == '*') {
				while (!operateStack.isEmpty() && (operateStack.lastElement().equals("/") || operateStack.lastElement().equals("*"))) {
					backExp.add(operateStack.pop()); // 弹出优先级相同或以上的栈内运算符
				}
				operateStack.add(String.valueOf(exp.charAt(i))); // 运算符入栈
				continue;
			}
			// 3.遇到了加减运算符
			if (exp.charAt(i) == '+' || exp.charAt(i) == '-') {
				while (!operateStack.isEmpty()) {
					backExp.add(operateStack.pop()); // 弹出优先级相同或以上的栈内运算符
				}
				operateStack.add(String.valueOf(exp.charAt(i))); // 运算符入栈
			}

		}
		// 4.最后弹出栈内所有元素到表达式
		while (!operateStack.isEmpty()) {
			backExp.add(operateStack.pop());
		}
		return backExp;
	}

	// 计算最终的结果
	public  double  calRst(LinkedList<String> tempBackExp) {
		Stack<Double> calStk = new Stack<>();
		for (String c : tempBackExp) {
			// 1.数字，入栈
			if (isNumeric(c)) {
				calStk.push(Double.valueOf(c)); // string to int
			}
			// 2.非数字，则为符号，出栈两个元素计算出结果然后再入栈该计算值
			else {
				double a = calStk.pop();
				double b = calStk.pop();
				switch (c) {
					case "+":
						calStk.push(b + a);
						break;
					case "-":
						calStk.push(b - a);
						break;
					case "*":
						calStk.push(b * a);
						break;
					case "/":
						calStk.push(b / a);
						break;
				}
			}
		}
		return calStk.pop();
	}

	private  boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
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
