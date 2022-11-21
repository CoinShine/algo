package com.shine.pojo;

import java.util.LinkedList;

/**
 * description: 无向图定义
 * @author shine
 * @date 2019/08/29 15:34
 * @version 1.0
 */
public class UndiGraph { // 无向图
	private int v; // 顶点的个数
	private LinkedList<Integer> adj[]; // 邻接表

	public UndiGraph(int v) {
		this.v = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; ++i) {
			adj[i] = new LinkedList<>();
		}
	}

	public void addEdge(int s, int t) { // 无向图一条边存两次
		adj[s].add(t);
		adj[t].add(s);
	}
}

