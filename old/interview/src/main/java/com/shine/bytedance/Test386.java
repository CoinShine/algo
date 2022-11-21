package com.shine.bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * description:字典序排数
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 *
 * 例如，
 * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000
 *
 * 先序遍历10叉树
 * @author shine
 * @date 2019/11/18 11:03
 * @version 1.0
 */
public class Test386 {


	/**
	 * DFS 先从1开始递归
	 * @param n
	 * @return
	 */
	public List<Integer> lexicalOrder(int n) {
		List<Integer> result = new ArrayList<>();
		lexicalOrder(result,0,n);
		return result;
	}

	public void lexicalOrder(List<Integer> result,int currentValue,int maxNum) {
		if(currentValue > maxNum){
			return;
		}
		if(currentValue != 0) {
			result.add(currentValue);
		}
		for(int nextBit = 0; nextBit <= 9;nextBit++){
			if(currentValue == 0){ // 去掉0开头全为0的节点
				if(nextBit == 0) continue;
			}
			lexicalOrder(result,currentValue*10+nextBit,maxNum);
		}
	}

	/**
	 * 先序遍历10叉树
	 * @param n
	 * @return
	 */
	public List<Integer> lexicalOrder1(int n) {
		List<Integer> res = new ArrayList<Integer>();
		Stack<Integer> tree = new Stack<Integer>();
		if(n < 10) {
			for(int i = n;i > 0;i--) tree.push(i);
		}else{
			for(int i = 9;i > 0;i--) tree.push(i);
		}
		int t,m;
		while(!tree.empty()){
			t = tree.pop();
			res.add(t);
			if(t*10>n) continue; // 如果超过上界，在下一次循环
			else {
				m = n - t * 10; // 剩余的节点
				if(m >9) m = 9;
			}
			for(int i = m;i >= 0;i--) {
				if (t * 10 + i <= n) tree.push(t * 10 + i);
			}
		}
		return res;
	}

	@Test
	public void test01(){
		System.out.println(lexicalOrder(13));
	}
}
