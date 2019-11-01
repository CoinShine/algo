package com.shine.other.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 生成括号 使用递归
 * 当前可以放置左括号的数量，当前可以放置右边括号的数量
 * 进行剪枝处理，增加判断规则  将时间复杂度从O(2^2N) 变为O(2^N)
 * （也可以当做是DFS范畴）
 * @author shine
 * @version 1.0
 * @date 9/12/2019 6:14 PM
 */
public class Test22 {
	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		generate("",result,n,n);
		return result;
	}

	public void generate(String item, List<String> result, int leftNum, int rightNum) {
		if (leftNum == 0 && rightNum == 0) {
			result.add(item);
			return;
		}
		if (leftNum > 0) {
			generate(item + "(", result,leftNum-1,rightNum);
		}
		if(leftNum<rightNum){
			generate(item+")",result,leftNum,rightNum-1);
		}
	}


	@Test
	public void test01(){
		List<String> list = generateParenthesis(2);
		System.out.println(list.toString());
	}
}
