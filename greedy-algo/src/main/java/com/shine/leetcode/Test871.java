package com.shine.leetcode;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * description: 最小加油次数
 * 分析：从起始油量开始，在油用完之前一直往前走，将途经的每个加油站的油量
 * 放入一个大顶堆中，当没有油了，从大顶堆取出堆顶，继续以上步骤，
 * 如果堆中没有数据了，并且油箱没有油，也没到加油站，则不能走到目的地
 * @author shine
 * @date 9/10/2019 3:06 PM
 * @version 1.0
 */
public class Test871 {
	public int minRefuelStops(int target, int startFuel, int[][] stations) {

		PriorityQueue<Integer> oilQueue = new PriorityQueue<>((i1,i2)->i2-i1);
		int minOilNum = 0; // 加油次数
		for (int i = 0; i < stations.length ; i++) { // 遍历加油站
			int distance = stations[i][0];  // 加油站到起点的距离
			while(!oilQueue.isEmpty() && startFuel < distance){ // 没有油了就取出堆顶的油，继续走
				startFuel += oilQueue.poll();
				minOilNum++;
			}
			if(oilQueue.isEmpty()&&startFuel<distance) // 加完所有的油都没有达到下一个加油站
				return -1;
			oilQueue.add(stations[i][1]); // 在有油之前一直走，将每个加油站的油量放入堆中
		}
		while (!oilQueue.isEmpty() && startFuel < target){ // 走过所有加油站还没到达目的地，继续从堆顶取出油
			startFuel += oilQueue.poll();
			minOilNum++;
		}
		if(startFuel<target) return -1; // 加完所有的油没有到达目的地
		return minOilNum;
	}


	@Test
	public void test01(){
		int[][] stations = {{25,50},{50,25},{75,25}};

		int i = minRefuelStops(100, 50, stations);
		System.out.println(i);
	}
}
