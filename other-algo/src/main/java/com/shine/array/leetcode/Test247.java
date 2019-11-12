package com.shine.array.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DESCRIPTION:中心对称数 II
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 找到所有长度为 n 的中心对称数。
 *
 * 示例 :
 *
 * 输入:  n = 2
 * 输出: ["11","69","88","96"]
 *
 * 当 n=0 的时候，应该输出空字符串：“ ”。
 *
 * 当 n=1 的时候，也就是长度为 1 的中心对称数有：0，1，8。
 *
 * 当 n=2 的时候，长度为 2 的中心对称数有：11， 69，88，96。注意：00 并不是一个合法的结果。
 *
 * 当 n=3 的时候，只需要在长度为 1 的合法中心对称数的基础上，不断地在两边添加 11，69，88，96 就可以了。
 *
 * [101, 609, 808, 906,     111, 619, 818, 916,     181, 689, 888, 986]
 * 随着 n 不断地增长，我们只需要在长度为 n-2 的中心对称数两边添加 11，69，88，96 即可
 * @author Shine
 * @create 2019/11/11 23:36
 */
public class Test247 {
	public List<String> findStrobogrammatic(int n) {
		return helper(n,n);
	}
	List<String> helper(int n, int m) {
		// 第一步：判断输入或者状态是否非法？
		if (n < 0 || m < 0 || n > m) {
			throw new IllegalArgumentException("invalid input");
		}

		// 第二步：判读递归是否应当结束?
		if (n == 0) return new ArrayList<String>(Arrays.asList(""));
		if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));

		// 第三步：缩小问题规模
		List<String> list = helper(n - 2, m);

		// 第四步: 整合结果
		List<String> res = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);

			if (n != m) res.add("0" + s + "0");

			res.add("1" + s + "1");
			res.add("6" + s + "9");
			res.add("8" + s + "8");
			res.add("9" + s + "6");
		}

		return res;
	}
}
