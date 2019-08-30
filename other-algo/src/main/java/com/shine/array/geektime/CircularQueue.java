package com.shine.array.geektime;

/**
 * description: 使用数组实现循环队列
 * @author shine
 * @date 2019/08/29 15:31
 * @version 1.0
 */
public class CircularQueue {
	private String[] items;
	private int n;
	private int head = 0;
	private int tail = 0;

	public CircularQueue(int size){
		this.items = new String[size];
		n = size;
	}

	public boolean enqueue(String item){
		//如果队列满了返回false
		if((tail+1)%n==head) return false;
		items[tail]=item;
		tail = (tail+1)%n;
		return true;
	}

	public String dequeue(){
		if(tail==head){
			return null;
		}
		String item = items[head];
		head = (head+1)%n;
		return item;
	}

}
