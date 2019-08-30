package com.shine.string.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:Z形变化
 *
 * @author shine
 * @create 2019-04-26 10:00
 */
public class Test06 {
	/**
	 * 方法一
	 *
	 * @param s
	 * @param rowsNum
	 * @return
	 */
	public String convert(String s, int rowsNum) {
		if (rowsNum == 1) {
			return s;
		}
		List<StringBuilder> rows = new ArrayList<>();
		//定义标识判断是否向下
		for (int i = 0; i < Math.min(s.length(), rowsNum); i++) {
			StringBuilder sb = new StringBuilder();
			rows.add(sb);
		}
		boolean isDown = false;
		int currentRow = 0;
		for (char c : s.toCharArray()) {
			rows.get(currentRow).append(c);
			if (currentRow == 0 || currentRow == rowsNum - 1)
				isDown = !isDown;
			currentRow += isDown ? 1 : -1;
		}

		StringBuilder res = new StringBuilder();
		for (StringBuilder row : rows) {
			res.append(row);
		}
		return res.toString();
	}


	/**
	 * 方法二 等差数列
	 *
	 * @param s
	 * @param rowsNum
	 * @return
	 */
	public String convert2(String s, int rowsNum) {
		if (rowsNum == 1) {
			return s;
		}
		//公差
		int d = 2 * rowsNum - 2;
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < rowsNum; i++) {
			//第一个等差数列的首位
			int first1 = i;
			//第二个等差数列的首位
			int first2 = d - i;
			for (int j = 0; ; j++) {
				//如果是第一行和最后一行,只有一个等差数列
				if (j % 2 == 0) {
					if (first1 >= s.length() - 1) break;
					res.append(s.charAt(first1));
					first1 += d;
				} else {
					if (i != 0 && i != rowsNum - 1) {
						if (first2 >= s.length() - 1) break;
						res.append(s.charAt(first2));
						first2 += d;
					}
				}
			}
		}
		return res.toString();
	}

	@Test
	public void testConvert() {
		String s = "LEETCODEISHIRING";
		//LCIRETOESIIGEDHN
		System.out.println(convert2(s, 3));
	}
}
