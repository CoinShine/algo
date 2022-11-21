package com.shine.ali;

import java.util.HashMap;

/**
 * description:网络延迟时间
 * @author shine
 * 有 N 个网络节点，标记为 1 到 N。
 *
 * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。
 * 现在，我们向当前的节点 K 发送了一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
 *
 * 	注意:
 * 		N 的范围在 [1, 100] 之间。
 * 		K 的范围在 [1, N] 之间。
 * 		times 的长度在 [1, 6000] 之间。
 * 		所有的边 times[i] = (u, v, w) 都有 1 <= u, v <= N 且 0 <= w <= 100。
 * @date 2019/11/19 12:10
 * @version 1.0
 */
public class Test743 {
	public int networkDelayTime(int[][] times, int N, int K) {
		// 构建邻接矩阵
		int[][] graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = -1;
			}
		}
		for (int i = 0; i < times.length; i++) {
			graph[times[i][0]-1][times[i][1]-1] = times[i][2];  // 注意下标从0开始
		}
		return dijkstra(graph, N, K-1);
	}
	// 寻找连接的最小的点，不存在，返回 -1；
	private int findMinVertex(int[] distance, boolean[] visited) {
		int minValue = Integer.MAX_VALUE, minVertex = -1;
		for (int i = 0; i < distance.length; i++) {
			if (!visited[i] && distance[i] < minValue) {
				minValue = distance[i];
				minVertex = i;
			}
		}
		return minVertex;
	}
	public int dijkstra(int[][] graph, int N, int src) {
		int[] distance = new int[N];
		boolean[] visited = new boolean[N];
		HashMap<Integer, Integer> retMap = new HashMap<>();
		int ret = -1;
		for (int i = 0; i < N; i++) {
			if (graph[src][i] != -1) distance[i] = graph[src][i];
			else distance[i] = Integer.MAX_VALUE;
		}
		visited[src] = true;
		for (int i = 0; i < N-1; i++) {
			int minVertex = findMinVertex(distance, visited);
			if (minVertex == -1) return -1;  // 有无法抵达的点
			visited[minVertex] = true;
			retMap.put(minVertex, distance[minVertex]);
			ret = Math.max(ret, distance[minVertex]);  // 更新最大
			// 更新 distance
			for (int j = 0; j < N; j++) {
				if (graph[minVertex][j] != -1 && !visited[j] && distance[minVertex]+graph[minVertex][j] < distance[j]) {
					distance[j] = distance[minVertex] + graph[minVertex][j];
				}
			}
		}
		// System.out.println(retMap.toString());
		return ret;
	}
}
