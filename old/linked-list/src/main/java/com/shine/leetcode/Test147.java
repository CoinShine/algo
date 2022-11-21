package com.shine.leetcode;

/**
 * description:对链表进行插入排序
 *
 * @author shine
 * @version 1.0
 * @date 2019/11/11 22:36
 */
public class Test147 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	class Solution {
		public ListNode insertionSortList(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}

			// 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
			// 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
			// 每次迭代完成，从插入元素为止，该链表可以被认为已经部分排序
			// 重复直到所有输入数据插入完为止

			// 1.遍历并与前面已经有序的序列向前逐一比较排序，找到合适为止插入

			// 定义三个指针 pre, cur, lat
			//pre    cur    lat
			// h  ->  4  ->  2  ->  5  ->  3  ->  null

			// 创建 h 节点，用于遍历链表
			ListNode h = new ListNode(-1);
			h.next = head;
			ListNode pre = h;
			ListNode cur = head;
			ListNode lat;

			while (cur != null) {
				lat = cur.next; // 记录下一个要插入排序的值

				if (lat != null && lat.val < cur.val) { // 只有 cur.next 比 cur 小才需要向前寻找插入点
					// 寻找插入点，从 pre 开始遍历 （每次都是头节点 h 开始向后遍历，因为单向链表是无法从后往前遍）
					while (pre.next != null && pre.next.val < lat.val) { // 如果当前节点的值小于要插入排序的值
						pre = pre.next; // 继续向后移动
					}

					// 找到要插入的位置，此时 pre 节点后面的位置就是 lat 要插入的位置
					// 交换 pre 跟 lat 节点需要一个 temp 节点来临时保存下插入位置 node 后 next
					ListNode tmp = pre.next;
					// 在 pre 节点后面插入
					pre.next = lat;
					// 此时 cur 节点还是 pre 所在的节点，所以其 next 要指向插入节点 lat 指向的 next
					cur.next = lat.next;
					// 插入let节点后，把记录的原先插入位置后续的 next 节点传给它
					lat.next = tmp;
					// 由于每次都是从前往后找插入位置，但是单向链表是无法从后往前遍历，所以需要每次插入完成后要让 pre 复位
					pre = h;
				} else {
					// 都这直接把 cur 指针指向到下一个
					cur = lat;
				}
			}

			return h.next;
		}
	}
}
