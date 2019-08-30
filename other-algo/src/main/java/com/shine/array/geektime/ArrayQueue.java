package com.shine.array.geektime;

/**
 * description: 数组实现队列
 * @author shine
 * @date 2019/08/29 15:32
 * @version 1.0
 */
public class ArrayQueue {
	private String[] items;
	private int n;
	private int head = 0;
	private int tail = 0;

	public ArrayQueue(int size){
		this.items = new String[size];
		n = size;
	}

	public boolean enqueue(String item){
		if(tail == n) {
			if(head == 0){
				return false;
			}
			for(int i = head;i<tail;i++){
				items[i-head]=items[i];
			}
			//重新设置head和tail
			tail -= head;
			head =0;
		}
		items[tail] = item;
		tail++;
		return true;
	}


	public String dequeue(){
		if(head == tail){
			return null;
		}
		return items[head++];
	}
}
