package com.shine.hash.leetcode;

import org.junit.Test;

/**
 * description: 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * 分析：思想：将输入的字符串，转成char数组，转成int数组。采用分治思想，每一位的相乘。
 *
 * 公式：AB*CD  =  AC (BC+ AD) BD,然后   从后到前满十进位。
 * @author shine
 * @date 2019/10/22 16:31
 * @version 1.0
 */
public class Test43 {
	public String multiply(String num1, String num2) {
		char[] chars1 = num1.toCharArray(); // 首先将字符串转换为字符数组
		char[] chars2 = num2.toCharArray();

		int[] result = new int[chars1.length+chars2.length]; // 定义结果数组
		int[] nums1 = new int[chars1.length]; // 定义int数组
		int[] nums2 = new int[chars2.length];
		for (int i = 0; i < chars1.length; i++) {
			nums1[i]=chars1[i]-'0';
		}

		for (int i = 0; i < chars2.length; i++) {
			nums2[i] = chars2[i]-'0';
		}

		// 根据公式从低位取出每一位相乘
		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {
				result[i+j] +=nums1[i]*nums2[j];
			}
		}

		for (int i = result.length-1; i > 0 ; i--) {
			if(result[i]>=10){
				result[i-1] += result[i]/10;
				result[i] = result[i]%10;
			}
		}

		if(result[0]==0) return "0";
		//转成string并返回
		StringBuilder resultStr = new StringBuilder();
		for (int i = 0; i < result.length - 1; i++) {
			resultStr.append(result[i]);
		}
		return resultStr.toString();

	}
	@Test
	public void test01(){
		String multiply = multiply("5", "12");
		System.out.println(multiply);
	}
}
