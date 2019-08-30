package com.shine.string.leetcode;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


/**
 * DESCRIPTION:求一个字符串中最长回文串子串
 * 采用中心扩展法 时间复杂度为O(n²)，空间复杂度为O(1)
 * 如果回文串元素个数为奇数时，比如 “abcba”，可以找到中间元素c
 * 如果回文串元素个数为偶数时，比如 “abba”，则没有中间元素
 *
 * @author shine
 * @create 2019-04-24 17:45
 */
public class Test05 {

	//回文串长度
	private int maxLen = 0;
	//回文串
	private String palindrome = "";

	/**
	 * 返回最长回文串
	 *
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		if (StringUtils.isEmpty(s) || s.length()>1000) {
			return "";
		}
		int length = s.length();
		for (int i = 0; i < length; i++) {
			//当回文串为基数时 回文串关于i位对称
			findLongestPalindrome(s, i,i);
			//当回文串为偶数时 第i位和第i+1位相同
			findLongestPalindrome(s, i, i+1);
		}
		return palindrome;

	}


	//查找最长串
	public void findLongestPalindrome(String s, int low, int high) {
		while (low >= 0 && high <= s.length() - 1) {
			if (s.charAt(low) == s.charAt(high)) {
				if (high - low + 1 > maxLen) {
					maxLen = high - low + 1;
					palindrome = s.substring(low, high + 1);
				}
				low--;
				high++;
			} else {
				break;
			}
		}
	}

	@Test
	public void testPalindrome(){
		String s = "google";
		String str = longestPalindrome(s);
		System.out.println(str);
	}
}

