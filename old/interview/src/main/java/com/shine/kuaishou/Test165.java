package com.shine.kuaishou;

import org.junit.Test;

/**
 * description:比较版本号
 *
 * @author shine
 * @version 1.0
 * @date 2019/11/20 17:58
 */
public class Test165 {
	//将版本号转换为用.（一个点）隔开的数组，然后依次比较数据的大小
	public int compareVersion(String version1, String version2) {
		String[] v1 = version1.split("\\.");//得到两个版本号分别对应的字符串数组v1和v2
		String[] v2 = version2.split("\\.");
		for (int i = 0, j = 0; i < v1.length || j < v2.length; i++, j++) {
			int n1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
			int n2 = j < v2.length ? Integer.parseInt(v2[j]) : 0;//如果i、j大于v1、v2的length，取0；
			//否则用Integer.parseInt()来转换，像“001”这种会自动转换为1
			if (n1 != n2) {
				return n1 > n2 ? 1 : -1;//如果n1大，说明第一个版本大，返回1；否则返回-1
			}

		}
		return 0;//如果循环结束都没有返回结果，说明版本是一样的，返回0
	}
	@Test
	public void test01(){
		String v1 = "0.1.1";
		String v2 = "1.0";
		int i = compareVersion(v1, v2);
		System.out.println(i);
	}
}

