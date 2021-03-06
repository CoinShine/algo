package com.shine.advanced.leetcode;

import org.junit.Test;

/**
 * description: 朋友圈问题
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，
 * B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生
 * 互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 示例 1:
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 *
 * 并查集实现 结合952题
 * @author shine
 * @date 2019/9/29 18:21
 * @version 1.0
 */
public class Test547 {


	/**
	 * 深度优先搜索
	 * @param M
	 * @return
	 */
	public int findCircleNum(int[][] M) {
		int[] visit = new int[M.length]; // 设置节点个数
		int count = 0;
		for (int i = 0; i < M.length; i++) {
			if(visit[i]==0){ // 如果没有访问过 深搜
				DFS_Graph(i,M,visit);
				count++; //搜索完成++
			}
		}
		return count;
	}
	private void DFS_Graph(int i,int[][] M,int[] visit){
		visit[i] = 1; // 深搜时标记为1
		for (int j = 0; j < M[i].length; j++) {
			if(visit[j] == 0 && M[i][j] == 1){ // 相邻且没有访问过
				DFS_Graph(j,M,visit); // 继续深搜
			}
		}
	}


	private int[] id;  // 节点个数
	private int[] size; // 子树规模
	private int count; // 朋友圈个数

	/**
	 * 将小的树合并到大的树 使得合并后的树更加平衡
	 * @param p
	 * @param q
	 */
	private void union(int p,int q){
		int i = find(p);
		int j = find(q);
		if(i==j) return;
		if(size[i]<size[j]){ // i的size小于j时，将i合并到j
			id[i] = j;
			size[j]+=size[i];
		}else{
			id[j] = i;
			size[i]+=size[j];
		}
		count--;
	}

	private int find(int p){
		while (p!=id[p]){  // id[p] 是p的父节点
			id[p] =id[id[p]]; // 指向父节点的父节点 压缩
			 p = id[p]; // 移动节点
		}
		return p;
	}
	/**
	 * 使用并查集实现
	 * @param M
	 * @return
	 */
	public int findCircleNum02(int[][] M) {
		count = M.length;
		id = new int[count];
		size = new int[count];
		for (int i = 0; i < M.length; i++) {
			id[i] = i;
			size[i] = 1;
		}
		for (int i = 0; i < M.length; i++) {
			for (int j = i+1; j < M.length; j++) {
				if(M[i][j] == 1){
					union(i,j);
				}
			}
		}
		return count;
	}

	@Test
	public void test01(){
		int[][] nums = {{1,1,0},{1,1,0},{0,0,1}};
		findCircleNum02(nums);
		while(Thread.activeCount() > 2){
			Thread.yield();
		}

	}
}
