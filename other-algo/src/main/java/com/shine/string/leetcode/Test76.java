package com.shine.string.leetcode;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * description: 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，
 * 请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * @author shine
 * @date 2019/9/23 16:19
 * @version 1.0
 */
public class Test76 {
	public String minWindow(String s, String t) {
		int[] s_map = new int[128]; // 记录原串字符哈希表
		int[] t_map = new int[128]; // 记录子串字符哈希表
		IntStream.range(0,t.length()).forEach(i->t_map[t.charAt(i)]++); // 统计子串字符出现次数

		int window_begin = 0; // 最小滑动窗口的起点
		String result=""; // 最小滑动窗口对应的字符串

		for(int i = 0;i<s.length();i++){
			s_map[s.charAt(i)]++; // 将为指针指向的字符添加到map中
			while(window_begin<i){ // 头指针小于尾指针
				char begin = s.charAt(window_begin);
				if(t_map[begin]==0){ // 说明窗口头指针指向的字符在t中不存在
					window_begin++; // 窗口头指针右移
				}else if(s_map[begin]>t_map[begin]){ // 头指针指向的字符在窗口中出现的数量大于t中出现的数量
					s_map[begin]--;
					window_begin++;
				}else{
					break; // 不满足上述两个条件，退出
				}
			}
			if(is_window_ok(s_map,t_map)){
				int new_window_len = i - window_begin + 1; // 计算新字符串长度
				if(result.equals("") ||result.length()>new_window_len){
					result=s.substring(window_begin,window_begin+new_window_len); // 结果为空或新窗口比之前的窗口更小，则替换为当前窗口
				}
			}
		}
		return result;
	}

	private boolean is_window_ok(int[] s_map,int[] t_map){
		for (int i=0; i<128;i++) {
			if (s_map[i] < t_map[i]) {
				return false;
			}
		}
		return true;
	}
	@Test
	public void test01(){
		String s = "abcdacbdfeHLjdsZXY,.";
		int[] char_map = new int[128];
		IntStream.range(0,s.length()).forEach(i->char_map[s.charAt(i)]++);
		IntStream.range(0, 128).forEach(i -> { if(char_map[i]>0) System.out.println((char)i+" 出现次数："+char_map[i]);});

		String str="a";
		String t= "b";
		String s1 = minWindow(str, t);
		System.out.println(s1);
	}
}
