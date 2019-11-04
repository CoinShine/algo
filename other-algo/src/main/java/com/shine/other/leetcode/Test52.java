package com.shine.other.leetcode;

import org.junit.Test;

/**
 * DESCRIPTION:使用位运算解决N皇后数量问题
 *
 * @author Shine
 * @create 2019/11/3 11:45
 */
public class Test52 {
	private int count = 0;
	public int totalNQueens(int n) {
		DFS(0,0,0,0,n);
		return count;
	}

	public void DFS(int row,int col,int pie,int na,int n){
		if(row>=n) {
			count++;
			return;
		}
		//获得所有的空位，0代表空位，1代表皇后可以攻击的范围，将0转为1 计算
		int bits = (~(col | na | pie)) & ((1 << n) - 1);
		while (bits>0){
			int lowBit = bits & (-bits); // 取最低位的1
			DFS(row+1,col|lowBit,(pie|lowBit)<<1,(na|lowBit)>>1,n);
			bits = bits&(bits-1); // 去掉最低位的1
		}
	}

	@Test
	public void test01(){
		totalNQueens(4);
	}
}
