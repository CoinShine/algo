package com.shine.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * description: 用栈实现队列
 * @author shine
 * @date 2019/09/05 15:14
 * @version 1.0
 */
public class Test232 {

	private Stack<Integer> tempStack = new Stack<>();
	private Stack<Integer> dataStack = new Stack<>();

	/** Push element x to the back of queue. */
	public void push(int x) {
		dataStack.push(x);
	}

	/** Removes the element from in front of queue and returns that element. */
	public int pop() {
		if(tempStack.isEmpty()){
			while (!dataStack.isEmpty()) {
				tempStack.push(dataStack.pop());
			}
		}
		return tempStack.pop();
	}

	/** Get the front element. */
	public int peek() {
		if(tempStack.isEmpty()){
			while (!dataStack.isEmpty()) {
				tempStack.push(dataStack.pop());
			}
		}
		return tempStack.peek();
	}

	/** Returns whether the queue is empty. */
	public boolean empty() {
		return dataStack.isEmpty() && tempStack.isEmpty();
	}

	@Test
	public void test01(){
		Test232 myQueue = new Test232();
		myQueue.push(1);
		myQueue.push(2);
		int peek = myQueue.peek();
		int pop = myQueue.pop();
		myQueue.push(3);
		int pop2 = myQueue.pop();
		int peek2 = myQueue.peek();

		System.out.println("peek:"+pop +" peek2:"+pop2);
	}
}
