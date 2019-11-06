package com.shine.leetcode;

import com.shine.pojo.ListNode;
import org.junit.Test;

/**
 * description: K个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author shine
 * @version 1.0
 * @date 2019/11/6 16:24
 */
public class Test25 {
	public ListNode reverseKGroup(ListNode head, int k) {

		ListNode temp = new ListNode(0);
		temp.next = head;

		ListNode pre = temp;
		ListNode end = temp;

		while (end.next != null) {
			for (int i = 0; i < k && end != null; i++) end = end.next;
			if (end == null) break;
			ListNode start = pre.next;
			ListNode next = end.next;
			end.next = null;
			pre.next = reverse(start);
			start.next = next;
			pre = start;

			end = pre;
		}
		return temp.next;
	}

	/**
	 * 翻转长度为K的链表
	 * @param head
	 * @return
	 */
	private ListNode reverse(ListNode head) {
		ListNode pre = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = pre;
			pre = curr;
			curr = next;
		}
		return pre;
	}


	public ListNode reverseKGroup2(ListNode head, int k) {
		int m = k;
		ListNode tempEnd = head;
		while(--m>0){
			if(tempEnd==null) break;
			tempEnd=tempEnd.next;
		}
		if(tempEnd == null) return head; // k大于链表长度时，不翻转
		ListNode head_pre=head,pNext_pre=tempEnd.next;
		ListNode reverseHead = reverseKGroup2(pNext_pre, k); // 获取后续翻转的头结点
		tempEnd.next=null;// 断开当前和后续的连接
		head = reverse(head); //获得当前翻转的头结点
		head_pre.next = reverseHead; // 将当前翻转的尾结点和后续翻转的头结点连接
		return head;
	}
	@Test
	public void test01() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(4);
		listNode.next.next.next.next = new ListNode(5);

		ListNode reverseKGroup = reverseKGroup2(listNode, 2);
		System.out.println(reverseKGroup.toString());
	}
}
