package com.shine.leetcode;

import com.shine.pojo.ListNode;

/**
 * description: 两两交换链表
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * @author shine
 * @date 2019/09/03 19:13
 * @version 1.0
 */
public class Test24 {
	public ListNode swapPairs(ListNode head) {
		ListNode pre = new ListNode(0);
		pre.next = head;
		ListNode temp = pre;
		while(temp.next!=null && temp.next.next!=null){
			ListNode start = temp.next;
			ListNode end =temp.next.next;
			temp.next = end;
			start.next = end.next;
			end.next = start;
			temp = start;
		}
		return pre.next;
	}

	/**
	 * 递归实现 交换相邻节点
	 * @param head
	 * @return
	 */
	public ListNode swapPairs2(ListNode head) {
		if(head==null || head.next==null){
			return head;
		}
		ListNode next = head.next;
		head.next = swapPairs(next.next);
		next.next=head;
		return next;
	}
}
