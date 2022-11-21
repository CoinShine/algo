package com.shine.leetcode;

import com.shine.pojo.ListNode;
import org.junit.Before;
import org.junit.Test;

/**
 * description: 编写一个程序，找到两个单链表相交的起始节点。
 * 判断两个链表是否相交，首先可以确定交点后的数据都一致，
 * 所以只需要分别求出两个链表L1，L2的长度，分别为m和n，此时假设m>n
 * 此时链表L1 先走m-n步到a节点，然后定义两个指针同时从L1的a节点和L2
 * 的头节点遍历，判断引用是否相同，如果相同，则有交点，如果不同则没有
 * 交点
 *
 * @author shine
 * @version 1.0
 * @date 2019/08/30 15:38
 */
public class Test160 {
	ListNode headA1;
	ListNode headB1;

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int lengthA = getLength(headA);
		int lengthB = getLength(headB);
		if (lengthA > lengthB) {
			headA = longListNodeForward(lengthA, lengthB, headA);
		} else {
			headB = longListNodeForward(lengthB, lengthA, headB);
		}
		//步进
		while (headA != null && headB != null) {
			if (headA.equals(headB)) {
				return headA;
			}
			headA = headA.next;
			headB = headB.next;
		}
		return null;
	}

	/**
	 * 求两个链表的交点（巧妙解法）
	 * @param headA
	 * @param headB
	 * @return
	 */
	public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
		if(headA==null && headB == null) return null;
		ListNode PA = headA;
		ListNode PB = headB;
		while (PA!=PB){
			PA = PA==null ? headB: PA.next;
			PB = PB==null ? headA: PB.next;
		}
		return PB;
	}

	/**
	 * 获取链表的长度
	 *
	 * @param head
	 * @return
	 */
	private int getLength(ListNode head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}

	/**
	 * 长链表先步进m-n步
	 *
	 * @param ListLong
	 * @param ListShort
	 * @param longHead
	 * @return
	 */
	private ListNode longListNodeForward(int ListLong, int ListShort, ListNode longHead) {
		int step = ListLong - ListShort;
		while (step-- > 0) {
			longHead = longHead.next;
		}
		return longHead;
	}

	@Test
	public void test01() {
		ListNode meetNode = getIntersectionNode(headA1, headB1);
		System.out.println(meetNode);
	}

	@SuppressWarnings("unchecked")
	@Before
	public void init() {
		//构造AB链表
		headA1 = new ListNode(1);
		ListNode headA2 = new ListNode(2);
		ListNode headA3 = new ListNode(3);
		ListNode headA4 = new ListNode(4);
		ListNode headA5 = new ListNode(5);
		ListNode headA6 = new ListNode(6);
		ListNode headA7 = new ListNode(7);
		headA1.next = headA2;
		headA2.next = headA3;
		headA3.next = headA4;
		headA4.next = headA5;
		headA5.next = headA6;
		headA6.next = headA7;

		headB1 = new ListNode(10);
		ListNode headB2 = new ListNode(9);
		headB1.next = headB2;
		//headB2.next=headA5;
	}
}
