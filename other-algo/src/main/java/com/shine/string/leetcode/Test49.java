package com.shine.string.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * description: 字母异位词分组 所有输入均为小写
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 *
 * @author shine
 * @version 1.0
 * @date 2019/9/22 20:09
 */
public class Test49 {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new TreeMap<>();
		for (int i = 0; i < strs.length; i++) {
			String temp = strs[i];
			String sort = sortStr(strs[i]);
			if (!map.containsKey(sort)) {
				List<String> list = new ArrayList<>();
				list.add(temp);
				map.put(sort, list);
			} else {
				List<String> list = map.get(sort);
				list.add(temp);
				map.put(sort, list);
			}
		}
		return new ArrayList<>(map.values()); //直接将结果转换返回
	}

	/**
	 * 对字符串进行排序
	 * @param str
	 * @return
	 */
	private String sortStr(String str) {
		char[] chars = str.toCharArray(); // 将字符串转换为字符数组
		Arrays.sort(chars); // 直接对字符数组进行排序
		return String.valueOf(chars); // 将字符数组转为字符串
	}

	@Test
	public void test01() {
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		List<List<String>> lists = groupAnagrams(strs);
		System.out.println(lists.toString());

	}
}
