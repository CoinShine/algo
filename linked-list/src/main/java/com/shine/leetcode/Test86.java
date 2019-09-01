package com.shine.leetcode;



/**
 * description:分割链表
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 分析：定义连个临时节点 lessHead 和 moreHead
 * 遍历链表，小于x的放lessHead后，然后指针向后移动 lessHeadPos
 * 大于x的放moreHead后，然后指针向后移动 moreHeadPos
 * 最后 将 lessHeadPos的next指向 moreHead的next
 * moreHeadPos的next指向null
 * @author shine
 * @date 2019/09/01 16:26
 * @version 1.0
 */
public class Test86 {

	@SuppressWarnings("unchecked")
	public ListNode partition(ListNode head, int x) {
		//定义两个临时节点(哨兵)
		ListNode lessHead = new ListNode(0);
		ListNode moreHead = new ListNode(0);
		//定义指针节点
		ListNode lessHeadPos = lessHead;
		ListNode moreHeadPos = moreHead;
		while(head!=null){
			if(head.val < x){
				lessHeadPos.next = head;
				//向后移动一位
				lessHeadPos = head;
			}else{
				moreHeadPos.next = head;
				//向后移动一位
				moreHeadPos = head;
			}
			head = head.next;
		}

		moreHeadPos.next = null;
		lessHeadPos.next = moreHead.next;
		return lessHead.next;
	}

	class ListNode<T> {
		 int val;
		 ListNode next;
		 ListNode(int x) {
			val = x;
		}
	}
}
