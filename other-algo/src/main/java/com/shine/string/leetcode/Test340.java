package com.shine.string.leetcode;

import java.util.HashMap;

/**
 * description:至多包含 K 个不同字符的最长子串
 * 这是第三题的变形
 *
 *用两个快慢指针：i 和 j，i 是慢指针，j 是快指针。一开始，两个指针都指向字符串的开头。另外，还需要一个哈希表来统计每个字符出现的个数，
 * 同时用来统计不同字符的个数
 *
 * 初始化一个哈希表 map，用来统计所出现了的不同字符。
 *
 * 用 max 变量记录最长的子串，其中子串最多包含 k 个不同的字符。
 *
 * 用快慢指针遍历字符串。
 *
 * 将快指针指向的字符加入到 map 中，统计字符出现的次数。
 *
 * 如果发现 map 的大小超过了 k，那么就得开始不断地将慢指针所指向的字符从 map 里清除掉。
 *
 * 首先获取当前慢指针指向的字符。
 *
 * 将它在map中的计数减一。
 *
 * 一旦它的统计次数变成了 0，就可以把它从 map 中删掉了。
 *
 * 接下来，慢指针继续往前走。
 *
 * 当 map 的大小恢复正常了，统计一下当前子串的长度。
 *
 * 最后返回最大的子串长度。
 * 快慢指针遍历字符串一遍，时间复杂度为 O(n)。
 * 运用了一个 map来作统计，空间复杂度为 O(n)
 * @author shine
 * @date 2019/11/14 12:31
 * @version 1.0
 */
public class Test340 {
	int lengthOfLongestSubstringKDistinct(String s, int k) {
		HashMap<Character, Integer> map = new HashMap<>();
		int max = 0;

		for (int i = 0, j = 0; j < s.length(); j++) {
			char cj = s.charAt(j);

			// Step 1. count the character
			map.put(cj, map.getOrDefault(cj, 0) + 1);

			// Step 2. clean up the map if condition doesn't match
			while (map.size() > k) {
				char ci = s.charAt(i);

				map.put(ci, map.get(ci) - 1);

				if (map.get(ci) == 0) {
					map.remove(ci); // that character count has become 0
				}
				i++;
			}

			// Step 3. condition matched, now update the result
			max = Math.max(max, j - i + 1);
		}

		return max;
	}
}
