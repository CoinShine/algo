package com.shine.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * description: 分发饼干
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，
 * 都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * 分析：使用饼干满足孩子的过程就是一个贪心的过程，
 * 	a.如果一个饼干不能满足一个孩子，那么更不能满足胃口更大的孩子
 * 	b.如果一个孩子可以用一个小的饼干满足，就没必要用大的饼干
 * 	c.孩子的胃口越小越容易满足，因此应该先满足小胃口的孩子
 * @author shine
 * @date 9/10/2019 3:40 PM
 * @version 1.0
 */
public class Test455 {

	public int findContentChildren(int[] g, int[] s) {
		Arrays.sort(g);// 将孩子的胃口从小到大排序
		Arrays.sort(s); // 将饼干的尺寸从小到大排序
		int gi = 0;
		int si = 0;
		while(si<s.length&&gi<g.length){
			if(s[si]>=g[gi]){
				gi++;
			}
			si++;
		}
		return gi;
	}




	@Test
	public void test01(){
		int[] g = {10,9,8,7};
		int[] s = {5,6,7,8};
		findContentChildren(g,s);


		//Arrays.sort(g,(i1,i2)->i2-i1);
		//
		//Arrays.sort(g, new Comparator<Integer>() {
		//	@Override
		//	public int compare(Integer o1, Integer o2) {
		//		return o2-o1;
		//	}
		//});



	}
}
