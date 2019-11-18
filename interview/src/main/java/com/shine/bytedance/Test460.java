package com.shine.bytedance;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * description: LFU缓存
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 *
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 *
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 *
 * 示例：
 *
 * LFUCache cache = new LFUCache(2);// capacity (缓存容量)
 *
 *		cache.put(1,1);
 *		cache.put(2,2);
 *		cache.get(1);       // 返回 1
 *		cache.put(3,3);    // 去除 key 2
 *		cache.get(2);       // 返回 -1 (未找到key 2)
 *		cache.get(3);       // 返回 3
 *		cache.put(4,4);    // 去除 key 1
 *		cache.get(1);       // 返回 -1 (未找到 key 1)
 *		cache.get(3);       // 返回 3
 *		cache.get(4);       // 返回 4
 *
 *
 * @author shine
 * @date 2019/11/16 16:15
 * @version 1.0
 */
public class Test460 {
	private int min;

	private final int capacity;
	private final HashMap<Integer, Integer> keyToVal;
	private final HashMap<Integer, Integer> keyToCount;
	private final HashMap<Integer, LinkedHashSet<Integer>> countToLRUKeys;

	public Test460(int capacity) {
		this.min = -1;
		this.capacity = capacity;
		this.keyToVal = new HashMap<>();
		this.keyToCount = new HashMap<>();
		this.countToLRUKeys = new HashMap<>();
	}

	public int get(int key) {
		if (!keyToVal.containsKey(key)) return -1;

		int count = keyToCount.get(key);
		countToLRUKeys.get(count).remove(key); // remove key from current count (since we will inc count)
		if (count == min && countToLRUKeys.get(count).size() == 0) min++; // nothing in the current min bucket

		putCount(key, count + 1);
		return keyToVal.get(key);
	}

	public void put(int key, int value) {
		if (capacity <= 0) return;

		if (keyToVal.containsKey(key)) {
			keyToVal.put(key, value); // update key's value
			get(key); // update key's count
			return;
		}

		if (keyToVal.size() >= capacity)
			evict(countToLRUKeys.get(min).iterator().next()); // evict LRU from this min count bucket

		min = 1;
		putCount(key, min); // adding new key and count
		keyToVal.put(key, value); // adding new key and value
	}

	private void evict(int key) {
		countToLRUKeys.get(min).remove(key);
		keyToVal.remove(key);
	}

	private void putCount(int key, int count) {
		keyToCount.put(key, count);
		countToLRUKeys.computeIfAbsent(count, ignore -> new LinkedHashSet<>());
		countToLRUKeys.get(count).add(key);
	}
}
