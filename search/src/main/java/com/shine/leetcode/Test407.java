package com.shine.leetcode;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * description: 收集雨水
 * 给定一个 m x n 的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * 说明:
 * m 和 n 都是小于110的整数。每一个单位的高度都大于 0 且小于 20000。
 * 1.搜索队列使用优先级队列，越低的的点优先级越高，
 * 2.从四周的点为起始点进行广度优先搜索，push到优先队列
 * 3.使用二维数组对push进队列的点进行标记，保证点被搜索一次
 * 4.队列不空，取出优先队列头元素就行搜索，按照上下左右四个方向搜索，过程中忽略超出边界和已经入队的点
 * 5.当对某点(x, y, h)进行拓展时(h即为(x, y)位置的高度，heightMap[x][y]):得到的新点为(newx, newy)，高度为heightMap[newx][newy])，
 * 如果 h 大于 heightMap[newx][newy]:最终结果 += h - heightMap[newx][newy];
 * 将heightMap[newx][newy]赋值为h(即升高该位置的水面)。 将(newx, newy, heightMap[newx][newy]) push进入优先级队列，并做标记。
 *
 * @author shine
 * @version 1.0
 * @date 2019-09-25 23:42
 */
public class Test407 {
	public int trapRainWater(int[][] heightMap) {
		PriorityQueue<Item> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.h)); // 根据h排序的小顶堆
		if (heightMap.length <= 2 && heightMap[0].length <= 2) return 0; // 如果小于两排则不能积水
		// 首先对二维数组的四周初始化
		int row = heightMap.length; // 行数
		int column = heightMap[0].length; // 列数
		int[][] mark = new int[row][column]; // 标记二维数组是否遍历,保证搜索过程中每个点只被遍历一次
		int result = 0;
		for (int i = 0; i < row; i++) {
			queue.add(new Item(i, 0, heightMap[i][0])); // 第一列
			queue.add(new Item(i, column - 1, heightMap[i][column - 1])); // 最后一列
			mark[i][0] = 1;
			mark[i][column - 1] = 1;
		}
		for (int i = 1; i < column - 1; i++) { // 第一行和最后一行
			queue.add(new Item(0, i, heightMap[0][i]));
			queue.add(new Item(row - 1, i, heightMap[row - 1][i]));
			mark[0][i] = 1;
			mark[row - 1][i] = 1;
		}

		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1}; // 定义方向数组
		while (!queue.isEmpty()) {
			Item item = queue.poll();
			int x = item.x;
			int y = item.y;
			int h = item.h;

			for (int i = 0; i < 4; i++) {
				int new_x = x + dx[i];
				int new_y = y + dy[i];
				if (new_x < 0 || new_y < 0 || new_x >= row || new_y >= column || mark[new_x][new_y] == 1) {
					continue; // 超出边界或已经添加到队列的点
				}
				if (h > heightMap[new_x][new_y]) {
					result += h - heightMap[new_x][new_y];
					heightMap[new_x][new_y] = h;
				}
				queue.add(new Item(new_x, new_y, heightMap[new_x][new_y]));
				mark[new_x][new_y] = 1;
			}
		}
		return result;
	}


	/**
	 * 定义堆中存放的元素
	 */
	public class Item {
		private int x; //在二维数组中所在的行
		private int y; //在二维数组中所在的列
		private int h; //二维数组中所在行列对应的值

		public Item(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}

	@Test
	public void test01() {
		int[][] a = {
				{1, 4, 3, 1, 3, 2},
				{3, 2, 1, 3, 2, 4},
				{2, 3, 3, 2, 3, 1}
		};
		int i = trapRainWater(a);
		System.out.println(i);
	}
}
