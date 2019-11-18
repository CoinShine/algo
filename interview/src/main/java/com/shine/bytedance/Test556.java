package com.shine.bytedance;

/**
 * description:下一个更大元素 III
 * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
 *
 * 示例 1:
 * 输入: 12
 * 输出: 21
 *
 * 示例 2:
 * 输入: 21
 * 输出: -1
 *
 * 这种方法中，我们同样将给定数字 nn 当做字符串数组 aa，首先我们观察到任意降序的序列，不会有更大的排列出现。
 * 比方说，下面数列就没有下一排列：
 *	[9, 5, 4, 3, 1]
 * 我们需要从右往左找到第一对连续的数字 a[i] 和 a[i-1] 满足 a[i-1] < a[i]。到当前位置位置位置，a[i-1] 右边的数字
 * 没办法产生一个更大的排列，因为右边的数字是降序的。所以我们需要重新排布 a[i-1] 到最右边的数字来得到下一个排列。
 *
 * 那么怎样排布能得到下一个更大的数字呢？我们想得到恰好大于当前数字的下一个排列，所以我们需要用恰好大于 a[i-1] 的数字去替换掉 a[i-1]，
 * 比方说我们让这个数字为 a[j]。
 *
 * 我们将 a[i-1] 和 a[j]交换，我们现在在下标为i-1的地方得到了正确的数字，但当前的结果还不是一个正确的排列。我们需要用从 i-1
 * 开始到最右边数字剩下来的数字升序排列，来得到它们中的最小排列。
 *
 * 我们注意到在从右往左找到第一对 a[i-1] < a[i] 的连续数字前， a[i-1] 右边的数字都是降序排列的，所以交换 a[i-1] 和 a[j] 不会改变下标从 i
 * 开始到最后的顺序。所以我们在交换了 a[i-1] 和 a[j] 以后，只需要反转下标从 ii 开始到最后的数字，就可以得到下一个字典序最小的排列
 *
 * 复杂度分析:
 * 	时间复杂度：O(n)O。最坏情况下，只会扫描整个数组两遍，这里 nn 是给定数字的位数。
 * 	空间复杂度：O(n)。使用了大小为 nn 的数组 aa ，其中 nn 是给定数字的位数
 * @author shine
 * @date 2019/11/17 12:10
 * @version 1.0
 */
public class Test556 {
	public int nextGreaterElement(int n) {
		char[] a = ("" + n).toCharArray();
		int i = a.length - 2;
		while (i >= 0 && a[i + 1] <= a[i]) {
			i--;
		}
		if (i < 0)
			return -1;
		int j = a.length - 1;
		while (j >= 0 && a[j] <= a[i]) {
			j--;
		}
		swap(a, i, j);
		reverse(a, i + 1);
		try {
			return Integer.parseInt(new String(a));
		} catch (Exception e) {
			return -1;
		}
	}
	private void reverse(char[] a, int start) {
		int i = start, j = a.length - 1;
		while (i < j) {
			swap(a, i, j);
			i++;
			j--;
		}
	}
	private void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
