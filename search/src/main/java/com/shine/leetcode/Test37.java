package com.shine.leetcode;

import org.junit.Test;

/**
 * description: 解数独问题
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 * <p>
 * 分析：使用DFS 首先对空余的元素填充 1-9 校验每行是否重复 校验每列是否重复  校验3*3格子是否重复
 * 2.剪枝方法，先遍历网格，找出空少的行或列，对于空少的行或列枚举 空格处可能出现的数字，将所有 空中 可能出现的数字数量排序，从最少的可能开始
 * 3.使用高级数据结构 比如 DanceLink
 *
 * @author shine
 * @version 1.0
 * @date 2019/11/1 21:22
 */
public class Test37 {
	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0) return;
		solve(board);
	}

	public boolean solve(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					for (char c = '1'; c <= '9'; c++) {
						if (isValid(board, i, j, c)) {
							board[i][j] = c;
							if (solve(board)) {
								return true;
							} else {
								board[i][j] = '.'; // 回溯
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValid(char[][] board, int row, int col, char c) {
		for (int k = 0; k < 9; k++) {
			if (board[row][k] != '.' && board[row][k] == c) return false;
			if (board[k][col] != '.' && board[k][col] == c) return false;
			if (board[3 * (row / 3) + k / 3][3 * (col / 3) + k % 3] != '.' && board[3 * (row / 3) + k / 3][3 * (col / 3) + k % 3] == c)
				return false;
		}
		return true;
	}


	@Test
	public void test01() {
		//char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		char[][] board = {{'.', '4', '7', '.', '5', '.', '.', '.', '8'}, {'6', '.', '5', '.', '3', '.', '2', '.', '1'}, {'.', '.', '.', '7', '.', '6', '.', '3', '.'}, {'.', '.', '6', '.', '7', '.', '.', '2', '4'}, {'9', '.', '.', '8', '.', '4', '.', '.', '6'}, {'4', '5', '.', '.', '1', '.', '9', '.', '.'}, {'.', '1', '.', '5', '.', '2', '.', '.', '.'}, {'2', '.', '8', '.', '4', '.', '5', '.', '3'}, {'5', '.', '.', '.', '9', '.', '7', '1', '.'}};
		//for (char c='1'; c<='9';c++){
		//	System.out.println(c);
		//}

		solveSudoku(board);
	}
}
