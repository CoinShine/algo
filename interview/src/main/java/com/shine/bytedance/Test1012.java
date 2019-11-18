package com.shine.bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * description:至少有 1 位重复的数字的个数
 * 给定正整数 N，返回小于等于 N 且具有至少 1 位重复数字的正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：20
 * 输出：1
 * 解释：具有至少 1 位重复数字的正数（<= 20）只有 11
 *
 *
 * 假设我们有一个 N=8765，那么有以下几种情况进行分类讨论：
 *
 * 1. 数字的位数小于 N 的位数，假设为三位数 XXX，那么如何求解三位数中没有重复数字的个数呢？
 * 显然这是个排列组合问题，最高位的数字从 1-9 中选择一个数字填入，则为 C(9, 1)。原先个位数和十位数共可以选择十个数字（0-9），但是因为百位数已经选了一个
 * 数字了，所以就只剩下 9 个数字可选，那么就共有 A(9, 2)种情况，结合乘法原理，三位数的情况下共有 C(9, 1) * A(9, 2) 种情况。两位数和一位数同理。
 *
 * 2. 数字的位数等于 N 的位数，我们就有如下几种情况：
 * 1XXX-7XXX
 * 80XX-86XX
 * 870X-875X
 * 8760-8765
 * 观察得知，X 前面的那个数字都不可能超过 N 的当前位。
 * 第一种情况，每轮我们可以在 9 个数字中选择 3 个进行排列，为 A(9, 3)
 * 第二种情况，为 A(8, 2)
 * 第三种情况，为 A(7, 1)
 * 最后一种情况，为 A(6, 0)
 * 归纳一下，我们可以得到，当 0 <= i < n （n 为 N 的位数）时，我们有每一轮的情况为 A(9- i, n - i - 1)
 * @author shine
 * @version 1.0
 * @date 2019/11/16 14:54
 */
public class Test1012 {
	public int numDupDigitsAtMostN(int N) {
		// Transform N + 1 to arrayList
		ArrayList<Integer> L = new ArrayList<>();
		for (int x = N + 1; x > 0; x /= 10)
			L.add(0, x % 10);

		// Count the number with digits < N
		int res = 0, n = L.size();
		for (int i = 1; i < n; ++i)
			res += 9 * A(9, i - 1);

		// Count the number with same prefix
		HashSet<Integer> seen = new HashSet<>();
		for (int i = 0; i < n; ++i) {
			for (int j = i > 0 ? 0 : 1; j < L.get(i); ++j)  // 当i=0时是最高位，最高位从1开始，非最高位从0开始，且不能超过当前位
				if (!seen.contains(j))
					res += A(9 - i, n - i - 1);
			if (seen.contains(L.get(i))) break;  // 遍历的时候跳过已经有的数字
			seen.add(L.get(i)); // 如果从高到低位出现过就加到set中
		}
		return N - res;
	}


	public int A(int m, int n) {
		return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
	}

	@Test
	public void test01() {
		int i = numDupDigitsAtMostN(256);
		System.out.println(i);
		//for (int j = 1; j <=1 ; j++) {
		//	System.out.println("==="+j);
		//
		//}
	}
}
