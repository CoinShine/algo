package com.shine.leetcode;

import com.shine.pojo.ListNode;

/**
 * description:
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 * <p>
 * 分析：对于成环问题，可以想象在操场跑圈，从同一起点跑，跑的快的同学会和跑的慢的同学再次相交
 * 定义快慢指针，慢指针每次走一步，快指针每次走两步，如果快指针和慢指针可以相遇说明有环，两指针
 * 相遇时，快指针可能走了N个环的距离，快指针走的总路程是慢指针的二倍，
 * 此时从head节点和meet节点，两指针同时出发，步数为1，则相遇节点即交点
 *
 * @author shine
 * @version 1.0
 * @date 2019/08/30 16:48
 */
public class Test142 {
	public ListNode detectCycle(ListNode head) {
		ListNode slow = head; //定义慢指针
		ListNode fast = head; //定义快指针
		ListNode meet = null; //定义相交节点

		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast == null) {
				return null;
			}
			fast = fast.next;
			if (slow.equals(fast)) {
				//说明有环
				meet = fast;
				break;
			}
		}
		//说明没有交点
		if(meet==null){
			return null;
		}
		//两指针分别从head和meet遍历
		while (true) {
			if(head == meet){
				return head;
			}
			head = head.next;
			meet = meet.next;
		}
	}
}
