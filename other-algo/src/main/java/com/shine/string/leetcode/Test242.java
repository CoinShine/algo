package com.shine.string.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * description:有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 分析：方法一 对每个单词按照字母表顺序排序，时间复杂度为O(nlogn)
 * 		方法二 使用hash表记录字符出现的次数，时间复杂度为O(n)
 * @author shine
 * @date 2019-10-22 00:06
 * @version 1.0
 */
public class Test242 {
	public boolean isAnagram(String s, String t) {
		int[] char_map1 = new int[26]; // 定义字符数组
		int[] char_map2 = new int[26];
		for (int i = 0; i < s.length(); i++) {
			char_map1[s.charAt(i)-'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			char_map2[t.charAt(i)-'a']++;
		}
		return Arrays.equals(char_map1,char_map2);
	}
	@Test
	public void test01(){
		System.out.println(isAnagram("anagram","nagaram"));
	}
}
