package com.shine.pojo.leetcode;

import org.junit.Test;

import java.util.LinkedList;

/**
 * DESCRIPTION:
 * 在选修某些课程之前需要一些先修课程。
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 使用邻接表，采用广度优先搜索，根据入度计算
 * 判断图有没有环，首先将入度为0的节点添加到队列中，当完成一个点的搜索后(出队)，它指向
 * 的所有点的入度都减1，如果有顶点的入度为0，则添加到队列，直到最后遍历完，说明没有环，
 * 否则说明有环
 * @author Shine
 * @create 2019/9/17 18:52
 */
public class Test207 {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] inDegrees = new int[numCourses];
		for (int[] prerequisite : prerequisites) {
			inDegrees[prerequisite[0]]++; // 遍历将有依赖的课程的入度加一
		}
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if(inDegrees[i]==0){ //将入度等于0的节点加入到队列中
				queue.offer(i);
			}
		}
		while(!queue.isEmpty()){ // 当队列不为空时，进行广搜遍历
			Integer pre = queue.poll();
			numCourses--; // 遍历一个节点，课程减一
			for (int[] prerequisite : prerequisites) { // 遍历找到 剩余课程中 依赖为已经遍历过节点的节点
				if(prerequisite[1]!=pre) continue;
				inDegrees[prerequisite[0]]--; // 将所有依赖遍历过节点的入度减一
				if(inDegrees[prerequisite[0]]==0) queue.offer(prerequisite[0]); // 将剩余节点中入度为0的节点加入队列
			}
		}
		return numCourses==0;
	}

	@Test
	public void test01(){
		int[][] pre = new int[][]{{1,0}};
		boolean b = canFinish(2, pre);
		System.out.println(b);
	}
}
