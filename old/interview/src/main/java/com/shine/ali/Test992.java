package com.shine.ali;

import java.util.HashMap;
import java.util.Map;

/**
 * description:K 个不同整数的子数组
 * 	给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * 	返回 A 中好子数组的数目。
 *
 * 	示例 1：
 * 		输出：A = [1,2,1,2,3], K = 2
 * 		输入：7
 * 		解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 *
 * 	示例 2：
 * 		输入：A = [1,2,1,3,4], K = 3
 * 		输出：3
 * 		解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 * @author shine
 * @date 2019/11/19 10:05
 * @version 1.0
 */
public class Test992 {

	/**
	 * [1,2,1]中以window[right]结尾的子数组还有[2,1]
	 * [1,2,1,2]中以window[right]结尾的子数组还有[2,1,2],[1,2]
	 * 注意，不能直接right - left
	 * 请看这个例子：
	 *     输入：A = [2,2,1,2,2,2,5], K = 2
	 *     如果这时候，window为[2,2,1,2,2],以window[right]结尾的子数组[2,2]是不符合要求的。
	 *
	 * 维护两个滑动窗口
	 * window1区间: (left1, right), 需要满足window1恰好有K个不同整数。
	 * window2区间: (left2, right), 需要满足window2恰好有K-1个不同整数。
	 * 这样就能保证：[left1, left2]这个区间中，以left2为结尾的子数组都能满足恰好有K个不同整数。
	 * 还是这个例子：
	 * 大window[2,2,1,2,2],
	 * window1[2,2,1,2,2], left1 = 0, right = 4,
	 * window2[2,2], left2 = 3, right = 4
	 * 以window[left2]结尾的并且符合要求的子数组：
	 * [2,2,1,2,2], [2,1,2,2], [1,2,2] 此时就是left2-left1=3
	 *
	 * @param A
	 * @param K
	 * @return
	 */
	public int subarraysWithKDistinct(int[] A, int K) {
		Window window1 = new Window();
		Window window2 = new Window();
		int ans = 0, left1 = 0, left2 = 0;
		for (int right = 0; right < A.length; right++) {
			int x = A[right];
			window1.add(x);
			window2.add(x);
			// 让window1左指针移动,直到等于K
			while (window1.different() > K) {
				window1.remove(A[left1]);
				left1++;
			}
			// 让window2左指针移动,直到恰好小于K
			while (window2.different() >= K) {
				window2.remove(A[left2]);
				left2++;
			}
			// window2恰好少一个，window1恰好等于K
			// 这时所有以left2为结尾的子数组，都是符合要求的
			ans += left2 - left1;
		}
		return ans;
	}
}

class Window {
	private Map<Integer, Integer> count;
	private int nonzero;

	Window() {
		count = new HashMap<>();
		nonzero = 0;
	}

	void add(int x) {
		count.put(x, count.getOrDefault(x, 0) + 1);
		if (count.get(x) == 1)
			nonzero++;
	}

	void remove(int x) {
		count.put(x, count.get(x) - 1);
		if (count.get(x) == 0)
			nonzero--;
	}

	int different() {
		return nonzero;
	}
}
