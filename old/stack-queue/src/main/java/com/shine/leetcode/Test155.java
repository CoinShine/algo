package com.shine.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * description:设计一个支持 push，pop，top 操作，
 * 并能在常数时间内检索到最小元素的栈
 * 分析：使用额外的栈存储栈中每个状态的最小值
 * @author shine
 * @date 2019/09/05 16:48
 * @version 1.0
 */
public class Test155 {

	private Stack<Integer> dataStack = new Stack<>(); // 定义存储数据的栈
	private Stack<Integer> minStack = new Stack<>(); // 定义每个转态的最小值栈
	public void push(int x) {
		if(!dataStack.isEmpty()){
			if(minStack.peek()<x) minStack.push(minStack.peek());
			else minStack.push(x);
		}else minStack.push(x);
		dataStack.push(x);
	}

	public void pop() {
		minStack.pop();
		dataStack.pop();
	}

	public int top() {
		return dataStack.peek();
	}

	public int getMin() {
		return minStack.peek();
	}

	@Test
	public void test01(){
		Test155 myStack = new Test155();
		myStack.push(-2);
		myStack.push(3);
		myStack.push(-5);
		int min1 = myStack.getMin();
		myStack.pop();
		int min2 = myStack.getMin();
		System.out.println("mini1:"+min1 +" min2:"+min2);

	}
}
