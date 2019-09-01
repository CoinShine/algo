package com.shine.leetcode;


/**
 * description: 排序链表的合并
 * 将两个有序链表合并为一个新的有序链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 分析：定义一个临时头节点，然后定义两个指针，分别移动，取出元素比较
 *
 * @author shine
 * @version 1.0
 * @date 2019/09/01 18:15
 */
public class Test21 {

	@SuppressWarnings("unchecked")
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode tempNode = new ListNode(0);
		ListNode position = tempNode;
		while (l1 != null && l2 != null) {
			if (l1.val > l2.val) {
				position.next = l2;
				l2 = l2.next;//指针向后移动
			} else {
				position.next = l1;
				l1 = l1.next;
			}
			position=position.next;
		}

		if(l1!=null){
			//说明l1还有剩余元素
			position.next = l1;
		}
		if(l2!=null){
			position.next =l2;
		}
		return tempNode.next;
	}

	class ListNode<T> {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
