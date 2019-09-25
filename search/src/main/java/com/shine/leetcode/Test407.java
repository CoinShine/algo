package com.shine.leetcode;

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
 * @author shine
 * @date 2019-09-25 23:42
 * @version 1.0
 */
public class Test407 {
	public int trapRainWater(int[][] heightMap) {

	}
}
