package com.shine.other.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DESCRIPTION:N皇后问题
 * 使用回溯法，解决N皇后问题
 * 定义N*N的二维数组为棋盘，首先放置皇后
 * 比如在(x,y)位置放置皇后，然后将皇后的攻击范围更新为1，
 * 然后放置下一个皇后，以此类推，如果放置了N个皇后，则记录下来
 * 如果没有放置到N个皇后，不能再放置皇后，则向前回溯
 *
 * @author Shine
 * @create 9/16/2019 12:09 PM
 */
public class Test51 {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> re = new ArrayList<>(); // 最终结果
		int[][] mark = new int[n][n]; // 标记棋盘是否可以放置皇后的二维数组
		String[][] location = new String[n][n];// 存储某一次的摆放结果

		//初始化mark和location
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mark[i][j] = 0;
				location[i][j] = ".";
			}
		}
		generation(0, n, location, re, mark);
		return re;
	}

	/**
	 * k代表完成了几个皇后的放置，正好放置到第k行皇后
	 * mark表示棋盘的标记数组
	 *
	 * @param k
	 * @param n
	 * @param location
	 * @param re
	 * @param mark
	 */
	private void generation(int k, int n, String[][] location, List<List<String>> re, int[][] mark) {
		if (k == n) { // 当k==n时，说明完成了第0到第n-1行的皇后放置，此时所有皇后都放置完成，记录结果
			re.add(convert2Re(location));
			return;
		}
		for (int i = 0; i < n; i++) { // 尝试k行的第0到n-1列
			if (mark[k][i] == 0) { // 如果mark[k][i] == 0 说明可以放置皇后
				int[][] tempMark = new int[n][n];// 记录回溯前的mark镜像,深拷贝二维数组
				for (int j = 0; j < n; j++) {
					System.arraycopy(mark[j], 0, tempMark[j], 0, n);
				}
				location[k][i] = "Q"; //记录当前放置皇后的位置
				putQueen(k, i, mark); // 放置皇后，更新坐标
				generation(k + 1, n, location, re, mark);
				mark = tempMark; //将mark重新赋值为回溯前的状态
				location[k][i] = "."; //将当前尝试的皇后位置重新置为·
			}
		}
	}

	/**
	 * 将location二维数组转换成list
	 *
	 * @param location
	 */
	private List<String> convert2Re(String[][] location) {
		List<String> list = new ArrayList<>();
		for (String[] strings : location) {
			StringBuilder sb = new StringBuilder();
			for (String s1 : strings) {
				sb.append(s1);
			}
			list.add(sb.toString());
		}
		return list;
	}

	/**
	 * 放置皇后，更新皇后的攻击范围
	 * x是横坐标，y为纵坐标，mark为棋盘
	 *
	 * @param x
	 * @param y
	 * @param mark
	 */
	public void putQueen(int x, int y, int[][] mark) {
		// 定义方向数组
		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
		// 放置皇后
		mark[x][y] = 1;
		for (int i = 1; i < mark.length; i++) {
			for (int j = 0; j < 8; j++) { // 8个方向更新皇后的攻击范围
				int new_x = x + i * dx[j];
				int new_y = y + i * dy[j];
				if (new_x >= 0 && new_x < mark.length && new_y >= 0 && new_y < mark.length) {
					mark[new_x][new_y] = 1;
				}
			}

		}
	}

	@Test
	public void test01() {
		List<List<String>> lists = solveNQueens2(4);
		System.out.println(lists.size());
	}



	List<List<Integer>> result = new ArrayList<>();
	Set<Integer> cols = new HashSet<>();
	Set<Integer> pie = new HashSet<>();
	Set<Integer> na = new HashSet<>();
	public List<List<String>> solveNQueens2(int n) {
		if(n<1) return new ArrayList<>();
		DFS(n,0,new ArrayList<>());
		return generate_result(n);
	}

	public void DFS(int n,int row, List<Integer> curr_state){
		// 递归终止条件
		if(row>=n){
			result.add(new ArrayList<>(curr_state));
			return;
		}
		for (int col = 0; col < n; col++) {
			if(cols.contains(col) || pie.contains(row+col) || na.contains(row-col)){
				continue;
			}
			cols.add(col);
			pie.add(row+col);
			na.add(row-col);
			curr_state.add(col);
			DFS(n,row+1,curr_state);
			// 回溯
			cols.remove(col);
			pie.remove(row+col);
			na.remove(row-col);
			curr_state.remove(curr_state.size()-1);
		}
	}

	public List<List<String>> generate_result(int n){
		List<List<String>> re = new ArrayList<>();
		for (List<Integer> list : result) {
			List<String> element = new ArrayList<>();
			for (Integer i : list) {
				StringBuilder s = new StringBuilder();
				for (int j = 0; j < i; j++) {
					s.append(".");
				}
				s.append("Q");
				for (int j = 0; j < n-i-1; j++) {
					s.append(".");
				}
				element.add(s.toString());
			}
			re.add(element);
		}
		return re;
	}
}
