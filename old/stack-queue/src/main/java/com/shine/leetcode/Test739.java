package com.shine.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * description:每日温度
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数
 * 分析：1、最直观的做法就是针对每个温度值向后进行依次搜索，找到比当前温度更高的值，这样的计算复杂度就是 O(n2)
 * 但是，在这样的搜索过程中，产生了很多重复的对比。例如，从 25 度开始往后面寻找一个比 25 度更高的温度的过程中，经历了
 * 21 度、19 度和 22 度，而这是一个温度由低到高的过程，也就是说在这个过程中已经找到了 19 度以及 21 度的答案，它就是 22 度
 * 2.可以运用一个堆栈 stack 来快速地知道需要经过多少天就能等到温度升高。从头到尾扫描一遍给定的数组 T，如果当天的温度比堆栈
 * stack 顶端所记录的那天温度还要高，那么就能得到结果
 * @author shine
 * @date 2019/11/6 23:46
 * @version 1.0
 */
public class Test739 {

	/**
	 * 我们需要找到比当前 T[i] 温度更高的位置，那么必须要记录哪些信息？
	 * 我们试着找到 T[0] 过后温度升高的位置。如果知道 T[10]=50，则 T[20]=50 是无效信息，
	 * 因为 T[i] 在 T[20] 以前已经到达了 50。如果 t[20]=100 将是有用的信息，因为如果 t[0]=80，
	 * 那么 T[20] 将有可能是它的下一个温度升高的位置，而 T[10] 则不可能是。
	 * 因此，我们需要记住一个索引的列表，索引代表的温度严格递增。我们可以利用栈来实现这样的效果
	 *
	 * 算法：
	 *
	 * 我们用栈记录索引，满足 T[stack[-1]] < T[stack[-2]] < ...，其中 stack[-1] 是栈的顶部，
	 * stack[-2] 是从顶部开始的第二个元素，依此类推；我们将在处理每个 T[i] 时保持 stack[-1] > stack[-2] > ...。
	 * 我们通过当前温度和栈顶索引所代表的温度比较来找到温度升高的位置。
	 * 举个例子：我们反向遍历处理 t=[73，74，75，71，69，72，76，73] ，通过看栈元素的变化来理解是如何工作的。
	 * 为了清楚 stack 只包含索引 i，但是将把 T[i] 的值写在旁边的括号中，例如 0 (73)。
	 * 当 i = 7，stack = [7 (73)]。ans[i] = 0。
	 * 当 i = 6，stack = [6 (76)]。ans[i] = 0。
	 * 当 i = 5，stack = [5 (72), 6 (76)]。ans[i] = 1。
	 * 当 i = 4，stack = [4 (69), 5 (72), 6 (76)]。ans[i] = 1。
	 * 当 i = 3，stack = [3 (71), 5 (72), 6 (76)]。ans[i] = 2。
	 * 当 i = 2，stack = [2 (75), 6 (76)]。ans[i] = 4。
	 * 当 i = 1，stack = [1 (74), 2 (75), 6 (76)]。ans[i] = 1。
	 * 当 i = 0，stack = [0 (73), 1 (74), 2 (75), 6 (76)]。ans[i] = 1
	 *
	 * @param T
	 * @return
	 */
	public int[] dailyTemperatures(int[] T) {
		int[] ans = new int[T.length];
		Stack<Integer> stack = new Stack<>();
		for (int i = T.length - 1; i >= 0; --i) {
			while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
			ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
			stack.push(i);
		}
		return ans;
	}


	public int[] dailyTemperatures1(int[] T) {
		int[] result = new int[T.length];
		if(T.length == 0) return new int[]{};
		Stack<Integer> stack = new Stack<>(); // 存放每天的下标
		stack.push(0);
		for (int i = 1; i < T.length ; i++) {
			while (!stack.isEmpty() && T[stack.peek()]< T[i]){
				Integer pop = stack.pop();
				result[pop] = i-pop;
			}
			stack.push(i);
		}
		while (!stack.isEmpty()){
			result[stack.pop()] =0;
		}
		return result;
	}

	@Test
	public void test01(){
		int[] T = {23, 25, 21, 19, 22, 26, 23};
		int[] temperatures = dailyTemperatures(T);
		System.out.println(Arrays.toString(temperatures));
	}
}
