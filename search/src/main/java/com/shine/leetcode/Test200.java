package com.shine.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * description: 岛屿数量
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 * 分析：可以采用深搜和宽搜 深搜 使用递归思想 宽搜使用队列的思想
 * @author shine
 * @date 2019/9/24 11:06
 * @version 1.0
 */
public class Test200 {
	public int numIslands(char[][] grid) {
		int island_num = 0;
		if(grid.length == 0) return island_num;
		int[][] mark = new int[grid.length][grid[0].length];
		for (int i = 0; i <grid.length ; i++) {
			for (int j = 0; j < grid[i].length ; j++) {
				if(grid[i][j]=='1' && mark[i][j] == 0){
					BFS(grid,mark,i,j);
					island_num++;
				}
			}
		}
		return island_num;
	}

	/**
	 * 深度优先搜索，搜索顺序上下左右
	 * @param grid 原网格
	 * @param mark 标记的网格
	 * @param x
	 * @param y
	 */
	private void DFS(char[][] grid,int[][] mark,int x,int y){
		mark[x][y] = 1;
		//定义方向数组
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		for (int i = 0; i < 4; i++) {
			int new_x = x+dx[i];
			int new_y = y+dy[i];
			if(new_x<0||new_y<0||new_x>=grid.length||new_y>=grid[new_x].length)
				continue;
			if(grid[new_x][new_y]=='1' && mark[new_x][new_y]==0)
				DFS(grid,mark,new_x,new_y);
		}
	}


	/**
	 * 广度优先搜索，搜索顺序为上下左右
	 * 第x行，第y列
	 * @param grid
	 * @param mark
	 * @param x
	 * @param y
	 */
	private void BFS(char[][] grid,int[][] mark,int x,int y){
		Queue<Integer> queue_x = new LinkedList<>(); // 宽度优先搜索队列存储x
		Queue<Integer> queue_y = new LinkedList<>(); // 宽度优先搜索队列存储y
		mark[x][y] = 1;
		queue_x.offer(x); // x和y是一一对应的
		queue_y.offer(y);
		//定义方向数组
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};

		while (!queue_x.isEmpty() && !queue_y.isEmpty()){
			x = queue_x.poll();
			y = queue_y.poll();
			for (int i = 0; i < 4; i++) {
				int new_x = x+dx[i];
				int new_y = y+dy[i];
				if(new_x<0||new_y<0||new_x>=grid.length||new_y>=grid[new_x].length)
					continue;
				if(grid[new_x][new_y]=='1' && mark[new_x][new_y]==0){
					queue_x.add(new_x);
					queue_y.add(new_y);
					mark[new_x][new_y] = 1;
				}
			}
		}
	}

	@Test
	public void test01(){
		char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
		//char[][] grid = {{'1','1'},{'0','0'}};
		int i = numIslands(grid);
		System.out.println(i);
	}
}
