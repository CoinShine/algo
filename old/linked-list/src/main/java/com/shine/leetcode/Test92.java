package com.shine.leetcode;

import com.shine.pojo.ListNode;
import org.junit.Test;

/**
 * DESCRIPTION:反转链表
 *反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * @author shine
 * @create 2019-08-29 16:17
 */
public class Test92 {
	public ListNode reverseBetween(ListNode head, int m, int n) {

		int len = n - m + 1; //计算结点需要逆置的个数
		ListNode preHead = null; // 初始化需要逆置结点的前驱
		ListNode result = head; //当m！=1时，最终的头结点
		while(head!=null&&--m >0){
			preHead =head;
			head=head.next;
		}
		ListNode modifyListTail = head; // 逆置后链表的尾部
		if(modifyListTail==null){
			return result;
		}
		ListNode newHead = null;

		while(head!=null && len>0){
			ListNode next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
			len--;
		}
		System.out.println("------");
		modifyListTail.next = head; // 链接逆置后的表尾与逆置段的后一个节点
		if(preHead!=null){
			preHead.next =newHead; //将逆置链表的前驱和逆置后的头结点相连
		}else{
			result=newHead; //preHead为空，怎逆置后的结果的头结点即为头结点
		}
		return  result;
	}

	@Test
	public void test01(){
		ListNode init = init();
		ListNode listNode = reverseBetween(init, 2, 4);
		System.out.println(listNode);
	}

	@SuppressWarnings("unchecked")
	private ListNode init() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(4);
		listNode.next.next.next.next = new ListNode(5);
		return listNode;
	}
}
