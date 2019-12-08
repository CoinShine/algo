package com.shine.other.leetcode;

import java.util.LinkedHashMap;

/**
 * description: LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 解析：
 * 方法一：
 * 获取键 / 检查键是否存在
 * 设置键
 * 删除最先插入的键
 * 前两个操作可以用标准的哈希表在 O(1) 时间内完成
 * 有一种叫做有序字典的数据结构，综合了哈希表和链表，在 Python 中为 OrderedDict，在 Java 中为 LinkedHashMap。
 * @author shine
 * @date 2019/11/5 10:46
 * @version 1.0
 */
public class Test146 extends LinkedHashMap<Integer, Integer> {
	private int capacity;

	//public Test146(int capacity) {
	//	super(capacity, 0.75F, true);
	//	this.capacity = capacity;
	//}
	//
	//public int get(int key) {
	//	return super.getOrDefault(key, -1);
	//}
	//
	//public void put(int key, int value) {
	//	super.put(key, value);
	//}
	//
	//@Override
	//protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
	//	return size() > capacity;
	//}



	private ListNode head;
	public Test146(int capacity) {
		this.capacity = capacity;
		head = new ListNode();
	}

	public int get(int key) {
		ListNode p = head;
		ListNode temp = head;
		ListNode pre;
		while (p.next!=null){
			pre = p;
			p = p.next;
			if(key == p.key) {
				pre.next = p.next;
				p.next = temp.next;
				temp.next = p;
				return p.val;
			}
		}
		return -1;
	}

	public void put(int key, int value) {
		ListNode temp = head;
		ListNode pre=head;
		ListNode p = head;
		int size =0;
		while (p.next!=null){
			size++;
			pre = p;
			p = p.next;
			if(key == p.key) {
				pre.next = p.next;
				p.next = temp.next;
				temp.next = p;
				p.val=value;
				return;
			}
		}
		if(size>=capacity){
			pre.next = null;
		}
		ListNode node = new ListNode();
		node.key=key;
		node.val=value;
		node.next = temp.next;
		temp.next = node;
	}

	class ListNode{
		int key;
		int val;
		ListNode next;
	}

	public static void main(String[] args){
		Test146 test146 = new Test146(2);
		test146.put(1,10);
		test146.put(2,20);
		System.out.println(test146.get(1));
		test146.put(3,30);
		System.out.println(test146.get(2));
		test146.put(4,40);
		System.out.println(test146.get(1));
		System.out.println(test146.get(3));
		System.out.println(test146.get(4));
	}
}
/**
 * LRUCache 对象会以如下语句构造和调用:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

