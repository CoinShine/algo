package com.shine.bytedance;

/**
 * description:重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * <p>
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * @author shine
 * @version 1.0
 * @date 2019/11/17 12:22
 */
public class Test143 {

	public void reorderList(ListNode head) {
		recursive(head, head);
	}
	public ListNode recursive(ListNode p, ListNode q) {
		if(q == null) return p;

		ListNode cur,next;
		if(q.next == null) cur = p;
		else cur = recursive(p.next, q.next.next);
		next = cur.next;
		if(p.next == cur || p == cur) cur.next = null;
		else {
			cur.next = p.next;
			p.next = cur;
		}
		return next;
	}



	public static void reorderList1(ListNode head) {
		/*快指针*/
		ListNode fast = head;
		/*慢指针*/
		ListNode slow = head;
		/*中间节点的前一个节点*/
		ListNode pre = null;
		/*找出中间节点以及其它节点*/
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			pre = slow;
			slow = slow.next;
		}
		if (slow != null && pre != null) {
			/*把链表从中间节点分开*/
			pre.next = null;
			/*预留遍历循环中的前一个节点*/
			ListNode p = null;
			/*把中间后面部分的节点翻转*/
			while (slow != null) {
				/*先把当前节点的后一个节点存起来*/
				ListNode next = slow.next;
				/*当前节点的下一个节点指向预留遍历循环中的前一个节点*/
				slow.next = p;
				/*预留循环的前一个节点*/
				p = slow;
				/*slow指向自己的下一个节点*/
				slow = next;
			}
			/*定义一个指针重新从头指针开始*/
			ListNode restart = head;
			/*依次将中间后面部分的节点放到前面节点后面*/
			while (restart != null) {
				/*预留后半段的下一个节点*/
				ListNode pNextTemp = p.next;
				/*预留前半段的下一个节点*/
				ListNode rNextTemp = restart.next;
				/*如果前半部分遍历时下一个节点不为空，则可以赋值给后半部分遍历时的下一个节点*/
				if (restart.next != null) {
					p.next = restart.next;
				}
				/*前半部分下个节点指向后半部分的节点*/
				restart.next = p;
				/*后半部分的指向自己的下一个*/
				p = pNextTemp;
				/*前半部分的指向自己的下一个*/
				restart = rNextTemp;
			}
		}
		/*总体思想是：1.找出链表的中间节点；2.将链表截断，后半部分的链表翻转；3.将前半段与翻转的后半段按题目要求翻转*/
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
