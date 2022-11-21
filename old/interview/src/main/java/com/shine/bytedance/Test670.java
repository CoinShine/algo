package com.shine.bytedance;

import org.junit.Test;

/**
 * description:最大交换
 * 定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 * 算法：
 *
 * 我们将计算 last[d] = i，最后一次出现的数字 d（如果存在）的索引 i。
 * 然后，从左到右扫描数字时，如果将来有较大的数字，我们将用最大的数字交换；如果有多个这样的数字，我们将用最开始遇到的数字交换。
 * 复杂度分析
 *
 * 时间复杂度：O(N)。其中，N 是输入数字的总位数。每个数字最多只考虑一次。
 * @author shine
 * @date 2019/11/17 11:30
 * @version 1.0
 */
public class Test670 {

	/**
	 * 尽量交换前面的大数位，并且和它交换的数还得是在它后面大于它的最大数
	 * 	1、倒序使用数组存储下来每个位置，在它及它以后的最大数的索引
	 * 	2、然后再正序从一个数开始遍历，如果它及它以后的最大数不是它本身，那么这个数就是我们需要交换的
	 * @param num
	 * @return
	 */
	public int maximumSwap(int num) {
		//从第一位开始 找到后面比它大的最大值进行交换
		char[] chars = String.valueOf(num).toCharArray();
		int[] indexes = new int[chars.length];
		char max = '0';
		int max_index = chars.length-1;
		//倒序遍历求 每个数之后最大数的索引
		for(int i =chars.length-1;i>=0;i--){
			if(max < chars[i]){
				max = chars[i];
				max_index = i;
			}
			indexes[i] = max_index;
		}

		for(int i =0;i<chars.length;i++){
			if(chars[i] != chars[indexes[i]]){
				char temp  = chars[i];
				chars[i] = chars[indexes[i]];
				chars[indexes[i]] = temp;
				break;
			}
		}
		return Integer.parseInt(new String(chars));
	}

	@Test
	public void test01(){
		int num =10;
		System.out.println(maximumSwap(num));
	}
}
