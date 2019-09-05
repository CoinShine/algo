package com.shine.leetcode;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * description: 查找未排序的数组中第K大的元素
 * 调研：优先级队列（二叉堆）是最大值（最小值）先出的完全二叉树
 * 使用K大小的最小堆，时间复杂度为n logK
 * @author shine
 * @date 2019-09-05 23:49
 * @version 1.0
 */
public class Test215 {
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> minQueue = new PriorityQueue<>();//  小顶堆，默认容量为11
		for (int num : nums) {
			minQueue.add(num);
			if (minQueue.size() > k) minQueue.poll();
		}
		return minQueue.peek();
	}

	@Test
	public void test01(){
		PriorityQueue<Integer> queue = new PriorityQueue<>((i1,i2)-> i2-i1); //大顶堆，实现了自定义的comparator接口
		queue.offer(10);
		queue.add(5);
		queue.add(7);
		queue.offer(15);
		queue.offer(8);
		queue.poll();
		Object peek = queue.peek();
		System.out.println(peek);
	}
}
