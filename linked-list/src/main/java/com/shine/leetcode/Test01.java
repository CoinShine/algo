package com.shine.leetcode;

import com.shine.pojo.ListNode;

import java.util.Scanner;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
 * 并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Test01 {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int num1 = getNum(l1);
		int num2 = getNum(l2);
		int sum = num1 + num2;
		//个位是最高位
		ListNode sum1 = new ListNode(sum%10);
		return castToList(sum, sum1);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入第一个整数：");
		int num1 = scanner.nextInt();
		System.out.println("请输入第二个整数：");
		int num2 = scanner.nextInt();
		//将数字反转
		ListNode l1 = castToList(castNum(num1), new ListNode(castNum(num1) % 10));
		ListNode l2 = castToList(castNum(num2), new ListNode(castNum(num2)%10));
		ListNode listNode1 = addTwoNumbers(l1, l2);
		System.out.println("结果为:"+listNode1);
	}

	/**
	 * 获得链表的数字
	 *
	 * @param listNode
	 * @return
	 */
	private static int getNum(ListNode listNode) {
		if (listNode.next != null) {
			int val = (int)listNode.val;
			int last = getNum(listNode.next);
			return val + 10 * last;

		}
		return (int)listNode.val;
	}

	/**
	 * 将数字转换为反向链表
	 * @return
	 */
	private static ListNode castToList(int num,ListNode listNode) {
		if(num / 10 > 0){
			listNode.next = new ListNode(num/10%10);
			castToList(num / 10,listNode.next);
			return listNode;
		}
		return listNode;
	}

	/**
	 * 将数字反转
	 */
	private static int castNum(int num){
		int temp = 0;
		int m = 0;

		while(num > 0) {
			m = m * 10 + num % 10;
			if(temp != m/10) {
				return 0;
			}
			temp = m;
			num = num/10;
		}

		return m;
	}

	public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		// l1或者l2为空时，直接返回另一者。
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		/*
		 * 最开始：rs和out共用一个地址， 过程：不断操作rs，rs每次都是一个新的结点。在修改链表时，只修改rs这条链，
		 * 最终：将链表结果传给out，out返回head则自动返回所有。 如果不声明rs，直接不断改变out，结果将不会返回一长链。
		 */
		ListNode out = new ListNode(0);
		// 初始化时，l1和l2的头结点分别赋值给p，q。后期不断修改rs
		ListNode p = l1, q = l2, rs = out;
		// 进位 该位上和
		int carry = 0, sum = 0;
		while (p != null || q != null) {
			// l1和l2长度不一时，遍历p和q，可以使用三元运算符返回各自的x和y。
			int x = (p != null) ? (int)p.val : 0;
			int y = (q != null) ? (int)q.val : 0;
			sum = x + y + carry;
			if (sum < 10) {
				//  8时，没有进位，不加0的话carry被认为1
				carry = 0;
				// rs存储值
				rs.next = new ListNode(sum % 10);
				rs = rs.next;
			} else {
				// 进位最多为1，9+9+1=19
				carry = 1;
				rs.next = new ListNode(sum % 10);
				rs = rs.next;
			}
			// 继续下一个，一个节点存储一位数字。
			if (p != null) {
				p = p.next;
			}
			if (q != null) {
				q = q.next;
			}
		}
		// 运行到最高位时，如果有进位，需要在加一个节点
		if (carry > 0)
			rs.next = new ListNode(carry);
		return out.next;
	}
}
