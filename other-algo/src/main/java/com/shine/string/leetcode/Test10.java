package com.shine.string.leetcode;

import org.junit.Test;

/**
 * description:正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 分析：遇到"."可以跳过，遇到"*" 则需要和前边的字符看成一个整体，可以是0个、1个、2个...无穷多个前边的字符
 * 重点是把这种回溯的思想掌握好。对于这道题，可以采用递归的写法，也可以采用动态规划的写法
 *
 * 递归法一：1、用两个指针 i 和 j 分别指向字符串 s 和 p 的第一个字符，当我们发现它们指向的字符相同时，我们同时往前一步移动指针 i 和 j。
 * 		    2、将函数定义为 isMatch(String s, int i, String p, int j) ，通过传入i和j判断是否匹配（对于普通字符和点）
 * 			3、来看看当遇到星匹配符的情况，举例说明如下。要不断地用 a* 去表示一个空字符串，一个 a，两个 a，一直到多个 a……
 * 		    4、当 a* 表示空字符串的时候，i 和 j 应该如何调整呢？此时 i 保持不变，j+2，递归调用函数的时候，变成 isMatch(s, i, p, j + 2)，如果不匹配则回溯
 * 		    5、用 a* 去表示一个 a，i 指向的字符与 a 匹配，那么 i+1。指针 j，已经完成了用 a* 去表示一个 a 的任务，接下来要指向 b，调用的时候应该是 isMatch(s, i + 1, p, j + 2)
 * 		    一直尝试
 *
 * 递归法二：1、p 字符串的最后一个字符 d 必须要和 s 字符串的最后一个字符相同，才能使 p 有可能与 s 匹配，那么当它们都相同的时候，问题规模也缩小。
 * 			2、p 字符串的最后一个字符不是 '*'，而是点号。它可以匹配 s 字符串里的任意一个字符，且它是最后一个，所以对应的就是 s 字符串里的 c，很明显互相匹配，继续缩小问题规模。
 * 			3、同样，b 不是 '*'，比较它与 s 字符串的最后一个字符是否相同，是，则继续缩小问题规模。
 * 			4、遇到 '*'，'*'可以表示一个空字符串，与前一个字符表示空字符串的时候，将问题变成了判断两个字符串是否匹配，其中，s 等于 aaa，而 p 是空字符串，很明显不能匹配。
 * 			5、用 a* 去表示一个 a。
 *			6、p 的最后一个还是 '*'，用同样的策略。
 * 			7、继续用 a* 去表示一个 a。
 * 			8、用 a* 去表示空字符串。
 * 			9、最后 s 和 p 都变成了空字符串，互相匹配。
 * 动态规划法：递归二的方法比较好理解，但是容易造成重叠计算。为了避免重叠计算，可以用动态规划，自底向上地实现刚才的策略
 * @author shine
 * @date 2019/11/13 18:04
 * @version 1.0
 */
public class Test10 {

	/**
	 * 递归一方法 从前往后
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		if (s == null || p == null) {
			return false;
		}

		return isMatch(s, 0, p, 0);
	}

	private boolean isMatch(String s, int i, String p, int j) {
		int m = s.length();
		int n = p.length();

		// 看看pattern和字符串是否都扫描完毕
		if (j == n) {
			return i == m;
		}
		// next char is not '*': 必须满足当前字符并递归到下一层
		if (j == n - 1 || p.charAt(j + 1) != '*') {
			return (i < m) && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) && isMatch(s, i + 1, p, j + 1);
		}

		// next char is '*', 如果有连续的s[i]出现并且都等于p[j]，一直尝试下去
		if (j < n - 1 && p.charAt(j + 1) == '*') {
			while ((i < m) && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))) {
				if (isMatch(s, i, p, j + 2)) {
					return true;
				}
				i++;
			}
		}
		// 接着继续下去
		return isMatch(s, i, p, j + 2);
	}


	/**
	 * 递归二方法 从后往前
	 * @param s
	 * @param p
	 * @return
	 */
	boolean isMatch1(String s, String p) {
		if (s == null || p == null) return false;

		return isMatch1(s, s.length(), p, p.length());
	}

	/**
	 * 1.递归函数的输入参数有四个，分别是字符串 s，当前字符串 s 的下标，字符串 p，以及字符串 p 的当前下标。由主函数可以看到，两个字符串的下标都是从最后一位开始。
	 * 2.若 p 字符串为空，并且如果 s 字符串也为空，就表示匹配。
	 * 3.当 p 字符串不为空，而 s 字符串为空，如上例所示，当 s 为空字符串，而 p 等于 a*，此时只要 p 总是由 '*'组合构成，一定能满足匹配，否则不行。
	 * 4.若 p 的当前字符不是 '*'，判断当前的两个字符是否相等，如果相等，就递归地看前面的字符。
	 * 5.否则，如果 p 的当前字符是 '*'：
	 * 		用 '*' 组合表示空字符串，看看是否能匹配；
	 * 		用 '*' 组合表示一个字符，看看能否匹配
	 * @param s
	 * @param i
	 * @param p
	 * @param j
	 * @return
	 */
	boolean isMatch1(String s, int i, String p, int j) {
		if (j == 0) return i == 0;
		if (i == 0) {
			return j > 1 && p.charAt(j - 1) == '*' && isMatch(s, i, p, j - 2);
		}

		if (p.charAt(j - 1) != '*') {
			return isMatch(s.charAt(i - 1), p.charAt(j - 1)) &&
					isMatch(s, i - 1, p, j - 1);
		}

		return  isMatch(s, i, p, j - 2) || isMatch(s, i - 1, p, j) &&
				isMatch(s.charAt(i - 1), p.charAt(j - 2));
	}

	boolean isMatch(char a, char b) {
		return a == b || b == '.';
	}

	/**
	 * 使用动态规划方法，自底向上
 	 * @param s
	 * @param p
	 * @return
	 */
	// 分别用 m 和 n 表示 s 字符串和 p 字符串的长度
	boolean isMatch2(String s, String p) {
		int m = s.length(), n = p.length();

		// 定义一个二维布尔矩阵 dp
		boolean[][] dp = new boolean[m + 1][n + 1];

		// 当两个字符串的长度都为 0，也就是空字符串的时候，它们互相匹配
		dp[0][0] = true;

		for (int j = 1; j <= n; j++) {
			dp[0][j] = j > 1 && p.charAt(j - 1) == '*' && dp[0][j - 2];
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// p 的当前字符不是 '*'，判断当前的两个字符是否相等，如果相等，就看 dp[i-1][j-1] 的值，因为它保存了前一个匹配的结果
				if (p.charAt(j - 1) != '*') {
					dp[i][j] = dp[i - 1][j - 1] &&
							isMatch2(s.charAt(i - 1), p.charAt(j - 1));
				} else {
					dp[i][j] = dp[i][j - 2] || dp[i - 1][j] &&
							isMatch2(s.charAt(i - 1), p.charAt(j - 2));
				}
			}
		}

		return dp[m][n];
	}

	boolean isMatch2(char a, char b) {
		return a == b || b == '.';

	}


	@Test
	public void test01(){
		String s="aaaabcd";
		String p="a*b.d";
		isMatch2(s,p);
	}
}
