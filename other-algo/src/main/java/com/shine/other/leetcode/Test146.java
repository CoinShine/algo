package com.shine.other.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

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

	public Test146(int capacity) {
		super(capacity, 0.75F, true);
		this.capacity = capacity;
	}

	public int get(int key) {
		return super.getOrDefault(key, -1);
	}

	public void put(int key, int value) {
		super.put(key, value);
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
		return size() > capacity;
	}

	//// 哈希表 + 双向链表
	//class DLinkedNode {
	//	int key;
	//	int value;
	//	DLinkedNode prev;
	//	DLinkedNode next;
	//}
	//
	//private void addNode(DLinkedNode node) {
	//	/**
	//	 * Always add the new node right after head.
	//	 */
	//	node.prev = head;
	//	node.next = head.next;
	//
	//	head.next.prev = node;
	//	head.next = node;
	//}
	//
	//private void removeNode(DLinkedNode node){
	//	/**
	//	 * Remove an existing node from the linked list.
	//	 */
	//	DLinkedNode prev = node.prev;
	//	DLinkedNode next = node.next;
	//
	//	prev.next = next;
	//	next.prev = prev;
	//}
	//
	//private void moveToHead(DLinkedNode node){
	//	/**
	//	 * Move certain node in between to the head.
	//	 */
	//	removeNode(node);
	//	addNode(node);
	//}
	//
	//private DLinkedNode popTail() {
	//	/**
	//	 * Pop the current tail.
	//	 */
	//	DLinkedNode res = tail.prev;
	//	removeNode(res);
	//	return res;
	//}
	//
	//private Hashtable<Integer, DLinkedNode> cache =
	//		new Hashtable<Integer, DLinkedNode>();
	//private int size;
	//private int capacity;
	//private DLinkedNode head, tail;
	//
	//public LRUCache(int capacity) {
	//	this.size = 0;
	//	this.capacity = capacity;
	//
	//	head = new DLinkedNode();
	//	// head.prev = null;
	//
	//	tail = new DLinkedNode();
	//	// tail.next = null;
	//
	//	head.next = tail;
	//	tail.prev = head;
	//}
	//
	//public int get(int key) {
	//	DLinkedNode node = cache.get(key);
	//	if (node == null) return -1;
	//
	//	// move the accessed node to the head;
	//	moveToHead(node);
	//
	//	return node.value;
	//}
	//
	//public void put(int key, int value) {
	//	DLinkedNode node = cache.get(key);
	//
	//	if(node == null) {
	//		DLinkedNode newNode = new DLinkedNode();
	//		newNode.key = key;
	//		newNode.value = value;
	//
	//		cache.put(key, newNode);
	//		addNode(newNode);
	//
	//		++size;
	//
	//		if(size > capacity) {
	//			// pop the tail
	//			DLinkedNode tail = popTail();
	//			cache.remove(tail.key);
	//			--size;
	//		}
	//	} else {
	//		// update the value.
	//		node.value = value;
	//		moveToHead(node);
	//	}
	//}
}
/**
 * LRUCache 对象会以如下语句构造和调用:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
