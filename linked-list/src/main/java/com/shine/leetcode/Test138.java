package com.shine.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 复杂链表复制
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。
 * 分析：如果只是简单链表的复制，实现起来很简单，直接遍历复制即可
 * 问题的难点在于复制随机指针，分为两步
 * 1.原链表中随机指针指向那个节点
 * 2.新链表中随机指针指向节点的地址
 * 采用两个map保存映射关系 oldMap<node,index>  newMap<index,node>
 * @author shine
 * @date 2019/09/01 17:05
 * @version 1.0
 */
public class Test138 {
	public Node copyRandomList(Node head) {
		Map<Node,Integer> oldMap  = new HashMap<>();
		Map<Integer,Node> newMap  = new HashMap<>();
		Node position = head;
		int index = 0;
		//第一次遍历分别将两个map赋值
		while(position!=null){
			oldMap.put(position, index);
			newMap.put(index, new Node(position.val,null,null));
			position= position.next;
			index++;
		}
		position = head;
		index = 0;
		Node newNodeHead = newMap.get(0);
		Node newNode = newNodeHead;
		while (position!=null){
			//遍历原始链表
			newNodeHead.next = newMap.get(++index); //从新map中取下一个节点
			Node random = position.random; //随机节点
			newNodeHead.random=newMap.get(oldMap.get(random)); //获得随机节点在新map中的位置
			newNodeHead = newNodeHead.next; //新链表指针向后移动 新链表和原链表长度一样 就不需要判断为空了
			position=position.next; //原链表指针后移
		}
		return newNode;
	}

	/**
	 * 定义链表节点
	 */
	class Node {
		public int val;
		public Node next;
		public Node random;

		public Node() {}

		public Node(int _val,Node _next,Node _random) {
			val = _val;
			next = _next;
			random = _random;
		}
	}


	@Test
	public void test01(){
		Node oldNode2=new Node(2,null,null);
		oldNode2.random = oldNode2;
		Node oldNode1 = new Node(1,oldNode2,oldNode2);
		copyRandomList(oldNode1);
	}
}
