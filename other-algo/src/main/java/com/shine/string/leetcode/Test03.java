package com.shine.string.leetcode;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * DESCRIPTION:查找不重复的最长字符串
 *
 * @author shine
 * @create 2019-04-07 22:13
 */
public class Test03 {
	public int lengthOfLongestSubstring(String s) {
		char[] chars = s.toCharArray();
		Set<Character> set = new HashSet<>();
		int begin = 0; //窗口的头指针
		int result = 0;
		int[] char_map = new int[128];
		for (int i = 0; i < chars.length; i++) {
			char_map[chars[i]]++;
			if (char_map[chars[i]] == 1) { // 说明字符没有出现过
				set.add(chars[i]);
				if (set.size() > result) result = set.size();
			} else { // 将重复字符移除
				while (begin < i && char_map[chars[i]] > 1) { // abcdec  相当于遇到第二个c跳到d重新开始
					char_map[chars[begin]]--;
					begin++;
				}
				set.clear();
				for (int j = begin; j <= i; j++) {
					set.add(chars[j]);
				}
			}

		}
		return result;
	}

	@Test
	public void testNum() {
		System.out.println(lengthOfLongestSubstring("a"));
	}
}
