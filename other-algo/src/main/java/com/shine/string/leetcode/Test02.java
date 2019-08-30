package com.shine.string.leetcode;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:查找不重复的最长字符串
 *
 * @author shine
 * @create 2019-04-07 22:13
 */
public class Test02 {
	public int lengthOfLongestSubstring(String s) {
		char[] chars = s.toCharArray();
		int length = 0;
		List<Integer> list = new ArrayList<>(1);
		list.add(0);
		for (int i = 0; i < s.length(); i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(chars[i]);
			for (int j = i + 1; j < s.length(); j++) {
				if (sb.toString().contains(String.valueOf(chars[j]))) {
					break;
				}
				sb.append(chars[j]);
			}
			length = sb.toString().length();
			if(list.get(0)<length){
				list.remove(0);
				list.add(length);
			}
		}
		return list.get(0);
	}

	@Test
	public void testNum() {
		System.out.println(lengthOfLongestSubstring("adgjhgiiieertyplm"));
	}
}
