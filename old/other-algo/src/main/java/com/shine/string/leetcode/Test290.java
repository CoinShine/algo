package com.shine.string.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * description:给定一种规律 pattern 和一个字符串 str ，
 * 判断 str 是否遵循相同的规律。
 * 例如：
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * @author shine
 * @date 2019/9/22 18:00
 * @version 1.0
 */
public class Test290 {
	public boolean wordPattern(String pattern, String str) {
		String[] s = str.split(" ");
		if(pattern.length()!= s.length) return false;
		Map<Character,String> map = new HashMap<>(); // 映射字符和字符串
		for (int i = 0; i < pattern.length(); i++) {
			if(!map.containsKey(pattern.charAt(i))&&!map.containsValue(s[i])) map.put(pattern.charAt(i),s[i]); // 如果都没出现过，则加入到map中
			else if(map.containsKey(pattern.charAt(i))&&map.get(pattern.charAt(i)).equals(s[i])) continue; // 如果key出现过，则对于的val应该等于s[j]
			else return false;
		}
		return true;
	}

	@Test
	public void test01(){
		String pattern = "abba";
		String str = "dog cat cat fish";
		boolean b = wordPattern(pattern, str);
		System.out.println(b);
	}
}
