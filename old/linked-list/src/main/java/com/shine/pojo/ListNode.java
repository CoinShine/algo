package com.shine.pojo;

/**
 * DESCRIPTION:链表
 *
 * @author shine
 * @create 2019-04-07 20:18
 */
public class ListNode<T> {
	public T val;
	public ListNode next;
	public ListNode(T x) {
		val = x;
	}

	@Override
	public String toString() {
		return "ListNode{" +
				"val=" + val +
				", next=" + next +
				'}';
	}
}
