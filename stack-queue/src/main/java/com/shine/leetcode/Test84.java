package com.shine.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * description: 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 * 在这种方法中，我们维护一个栈。一开始，我们把 -1 放进栈的顶部来表示开始。
 * 初始化时，按照从左到右的顺序，我们不断将柱子的序号放进栈中，直到遇到相邻柱子呈下降关系，也就是 a[i-1] > a[i] 。
 * 现在，我们开始将栈中的序号弹出，直到遇到 stack[j]满足a[stack[j]]≤a[i] 。每次我们弹出下标时，我们用弹出元素作为高
 * 形成的最大面积矩形的宽是当前元素与 stack[top-1]之间的那些柱子。也就是当我们弹出 stack[top]时，记当前元素在原数组中
 * 的下标为 i ，当前弹出元素为高的最大矩形面积为：
 * (i−stack[top−1]−1)×a[stack[top]].
 *
 * 更进一步，当我们到达数组的尾部时，我们将栈中剩余元素全部弹出栈。在弹出每一个元素时，我们用下面的式子来求面积：
 * (stack[top]−stack[top−1]-1)×a[stack[top]]，其中，stack[top]表示刚刚被弹出的元素。因此，
 * 我们可以通过每次比较新计算的矩形面积来获得最大的矩形面积
 *
 * @author shine
 * @date 2019/11/7 10:15
 * @version 1.0
 */
public class Test84 {
	public int largestRectangleArea(int[] heights) {
			Stack < Integer > stack = new Stack < > ();
			stack.push(-1);
			int maxArea = 0;
			for (int i = 0; i < heights.length; ++i) {
				while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
					maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
				stack.push(i);
			}
			while (stack.peek() != -1)
				maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() -1));
			return maxArea;
		}


	@Test
	public void test01(){
		int[] heights = {6, 7, 5, 2, 4, 5, 9, 3};
		System.out.println(largestRectangleArea(heights));
	}
}
