package com.shine.array.geektime;

/**
 * description: 使用数组实现栈
 * @author shine
 * @date 2019/08/29 15:31
 * @version 1.0
 */
public class ArrayStack {
	private String[] items;//数组
	private int count;//栈中元素个数
	private int n;//栈的大小

	public ArrayStack(int n) {
		this.items = new String[n];
		this.count = 0;
		this.n = n;
	}

	/**
	 * 入栈操作
	 *
	 * @return
	 */
	public boolean push(String item) {
		if (count == n) {
			return false;
		}
		items[count] = item;
		++count;
		return true;
	}

	/**
	 * 出栈操作
	 */
	public String pop() {
		if (count == 0) {
			return null;
		}
		//取出count-1个元素
		String item = items[count - 1];
		--count;
		return item;
	}
}
