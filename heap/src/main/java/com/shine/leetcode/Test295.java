package com.shine.leetcode;

import java.util.PriorityQueue;

/**
 * description: 求动态数组的中位数，可以动态的添加元素和返回中位数
 * 需要保证最大堆和最小堆的元素个数相差不超过一个
 * @author shine
 * @date 2019-09-06 00:28
 * @version 1.0
 */
public class Test295 {

	PriorityQueue<Integer> minQueue = new PriorityQueue<>(); //定义最小堆 优先级队列
	PriorityQueue<Integer> maxQueue = new PriorityQueue<>((i1,i2)->i2-i1); //定义最大堆

	public void addNum(int num) {
		if(maxQueue.isEmpty()){
			maxQueue.add(num);
			return;
		}
		if(maxQueue.size()== minQueue.size()){
			if(maxQueue.peek() > num)
				maxQueue.add(num);
			else
				minQueue.add(num);
		}else if(maxQueue.size()>minQueue.size()){ // 大顶堆元素多
			if(num > maxQueue.peek()){
				minQueue.add(num);
			}else{
				minQueue.add(maxQueue.poll());  // 大顶堆向小顶堆调整元素
				maxQueue.add(num);
			}
		}else {
			if(num < minQueue.peek())
				maxQueue.add(num);
			else{
				maxQueue.add(minQueue.poll());
				minQueue.add(num);
			}
		}

	}

	public double findMedian() {
		if(minQueue.size() == maxQueue.size()){
			return (minQueue.peek()+maxQueue.peek())/2.0;
		}else if(maxQueue.size()>minQueue.size()){
			return maxQueue.peek();
		}else
			return minQueue.peek();
	}
}
