package com.shine.leetcode;

/**
 * description: K个链表合并
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 分析：采用分治思想 时间复杂度为 2n*k/2+4n*k/4+...+2^logk*n * k/2^logk
 * =O(nk×log(k))
 * @author shine
 * @version 1.0
 * @date 2019/09/01 19:52
 */
public class Test23 {
	public ListNode mergeKLists(ListNode[] lists) {
		int length = lists.length;
		if(length==0) return null;
		return merge(lists,0,length-1);
	}

	private ListNode merge(ListNode[] listNodes,int left,int right){
		if(left==right) return listNodes[left];
		int mid = left+((right-left)>>1);
		ListNode leftNode = merge(listNodes,left,mid);
		ListNode rightNode = merge(listNodes,mid+1,right);
		return mergeTwoList(leftNode,rightNode);
	}

	private ListNode mergeTwoList(ListNode l1, ListNode l2) {
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

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
