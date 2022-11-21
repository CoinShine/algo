package com.shine.string.leetcode;

import org.junit.Test;

/**
 * description: 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1
 * 使用KMP算法
 * @author shine
 * @date 2019/11/14 23:48
 * @version 1.0
 */
public class Test28 {

	/**
	 * 暴力法 O(m×n)
	 * @param haystack
	 * @param needle
	 * @return
	 */
	int strStr0(String haystack, String needle) {
		for (int i = 0; ; i++) {
			for (int j = 0; ; j++) {
				if (j == needle.length()) return i;
				if (i + j == haystack.length()) return -1;
				if (needle.charAt(j) != haystack.charAt(i + j)) break;
			}
		}
	}

	/**
	 * O(m + n)
	 * @param haystack
	 * @param needle
	 * @return
	 */
	int strStr(String haystack, String needle) {
		int m = haystack.length();
		int n = needle.length();

		if (n == 0) {
			return 0;
		}

		int[] lps = getLPS(needle);

		int i = 0, j = 0;

		while (i < m) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				i++; j++;

				if (j == n) {
					return i - n;
				}
			} else if (j > 0) {
				j = lps[j - 1];
			} else {
				i++;
			}
		}

		return -1;
	}


	int[] getLPS(String str) {
		// 初始化一个 lps 数组用来保存最终的结果
		int[] lps = new int[str.length()];

		// lps 的第一个值一定是 0，即长度为 1 的字符串的最长公共前缀后缀的长度为 0，直接从第二个位置遍历。并且，初始化当前最长的 lps 长度为 0，用 len 变量记录下
		int i = 1, len = 0;

		// 指针 i 遍历整个输入字符串
		while (i < str.length()) {
			// 若 i 指针能延续前缀和后缀，则更新 lps 值为 len+1
			if (str.charAt(i) == str.charAt(len)) {
				lps[i++] = ++len;
				// 否则，判断 len 是否大于 0，尝试第二长的前缀和后缀，是否能继续延续下去/ 继续比较 needle[i] 和  needle[len]
			} else if (len > 0) {
				len = lps[len - 1]; // TODO 先死记硬背住
				// 所有的前缀和后缀都不符合，则当前的 lps 为 0，i++
			} else {
				i++;
			}
		}

		return lps;

	}
	@Test
	public void test01(){
		getLPS("ADCADBADCABC");
		//System.out.println(i);
	}
}
