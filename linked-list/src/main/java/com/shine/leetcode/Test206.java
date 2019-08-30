package com.shine.leetcode;

import com.shine.pojo.ListNode;
import org.junit.Test;

/**
 * DESCRIPTION:
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author shine
 * @create 2019-08-27 23:39
 */
public class Test206 {

	public ListNode reverseList(ListNode head) {
		ListNode pre=null;
		ListNode current = head;
		// 每次循环将当前节点指向前一个节点，然后当前节点和前节点后移
		while(current!=null){
			ListNode temp = current.next; //定义临时节点
			current.next = pre; //将当前节点指向它前面的节点
			pre=current; //前指针后移
			current=temp; // 当前指针后移
		}
		return pre;
	}


	//递归实现
	private ListNode reverse(ListNode pre,ListNode current){
		if(current==null) return pre;
		ListNode next = current.next;
		current.next = pre;
		return reverse(current,next);
	}

	private ListNode init() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(4);
		listNode.next.next.next.next = new ListNode(5);
		return listNode;
	}

	@Test
	public void ts(){
		ListNode li = init();
		//ListNode listNode = reverseList(li);
		ListNode reverse = reverse(null, li);
		System.out.println(reverse);
	}
}
