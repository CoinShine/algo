package com.shine.string.leetcode;

import org.junit.Test;

/**
 * description: 根据给定的字符串出现的字符数，组成最大回文串
 * 例如字符串为："abccccdd" 组成的最大回文串长度为7 例如"ccdadcc"
 * 如果回文串为偶数个字符 则只取偶数字符
 * 如果回文串为奇数个字符 则取偶数字符和中心点字符
 * 使用字符数组统计字符个数
 * @author shine
 * @date 2019/9/22 17:42
 * @version 1.0
 */
public class Test449 {
	public int longestPalindrome(String s) {
		int[] charSet = new int[128]; //字符哈希,将字符转成ascii码表
		int maxLen = 0; //回文串偶数部分最大长度
		int flag = 0; // 是否有中心点
		int length = s.length();
		for (int i = 0; i < length ; i++) {
			charSet[s.charAt(i)]++;
		}
		for(int i = 0 ; i < 128 ;i++){
			if(charSet[i]%2 == 0) maxLen+=charSet[i];
			else{
				maxLen+=charSet[i]-1;
				flag=1;
			}
		}
		return maxLen+ flag;

	}

	@Test
	public void test01(){
		String s = "abccccdd";
		System.out.println(longestPalindrome(s));
	}
}
