package com.shine.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * description: 使用队列实现一个栈
 * @author shine
 * @date 2019-09-04 23:58
 * @version 1.0
 */
public class Test225 {
	Queue<Integer> tempQueue = new LinkedList<>();
	Queue<Integer> dataQueue = new LinkedList<>();
	public Test225() {

	}

	/** Push element x onto stack. */
	public void push(int x) {
		tempQueue.add(x);
		while(!dataQueue.isEmpty()){
			tempQueue.add(dataQueue.remove());
		}
		dataQueue = tempQueue;
		tempQueue = new LinkedList<>();
	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
		Integer peek=Integer.MIN_VALUE;
		if(!dataQueue.isEmpty()){
			 peek = dataQueue.peek();
			dataQueue.remove();
		}
		return peek;
	}

	/** Get the top element. */
	public int top() {
		if(!dataQueue.isEmpty()){
			return dataQueue.peek();
		}
		return Integer.MIN_VALUE;
	}

	/** Returns whether the stack is empty. */
	public boolean empty() {
		return dataQueue.isEmpty();
	}


	@Test
	public void test01(){
		Test225 stack = new Test225();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		int pop1 = stack.pop();
		int pop2 = stack.pop();
		int pop3 = stack.pop();
		System.out.println(pop3);
	}
}
