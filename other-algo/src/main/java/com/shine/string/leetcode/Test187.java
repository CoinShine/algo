package com.shine.string.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:编写一个函数来查找 DNA
 * 分子中所有出现超多一次的10个字母长的序列（子串）
 * @author shine
 * @date 2019/9/23 15:52
 * @version 1.0
 */
public class Test187 {
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> re = new ArrayList<>();
		Map<String,Integer> wordMap = new HashMap<>();
		int length = s.length();
		for (int i = 0; i < length; i++) {
			int end = Math.min(i + 10, length);
			String word = s.substring(i, end);
			wordMap.merge(word, 1, Integer::sum); // 为空时put value为1，不为空时value加1
		}
		for (Map.Entry<String, Integer> entry : wordMap.entrySet())
			if(entry.getValue()>1) re.add(entry.getKey());
		return re;
	}
	@Test
	public void test01(){
		String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		List<String> list = findRepeatedDnaSequences(s);
		System.out.println(list.toString());
	}
}
