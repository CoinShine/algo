package com.shine.bytedance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description: 按公因数计算最大组件大小
 * 给定一个由不同正整数的组成的非空数组 A，考虑下面的图：
 *
 * 有 A.length 个节点，按从 A[0] 到 A[A.length - 1] 标记；
 * 只有当 A[i] 和 A[j] 共用一个大于 1 的公因数时，A[i] 和 A[j] 之间才有一条边。
 * 返回图中最大连通组件的大小
 *
 *
 * 思路
 *
 * 设 W = \max(A[i])W=max(A[i])，R = \sqrt{W}R=W。对于数组 AA 中的每个数，最多只有一个非本身的质因数 p 满足 p≥R。
 * 这就意味着最多只有 R +A.length 个不同的质因数： 为本身的质因数最多有 A.length 个，非本身的质因数一定比 R 小，最多有 R 个。
 *
 * 算法：
 * 提取数组 AA 中每个数的质因数，对每个质因数建立索引。接着，用并查集把 AA 中的质因数合并起来。最后计算每个集合的大小。
 *
 * 时间复杂度： O(N\sqrt{W})，其中 N 是 A 的长度，W=max(A[i])。
 * 空间复杂度： O(N)
 *
 *
 * @author shine
 * @date 2019/11/15 12:15
 * @version 1.0
 */
public class Test952 {
	public int largestComponentSize(int[] A) {
		int N = A.length;

		// factored[i] = a list of unique prime factors of A[i]
		ArrayList<Integer>[] factored = new ArrayList[N];
		for (int i = 0; i < N; ++i) {
			factored[i] = new ArrayList<Integer>();
			int d = 2, x = A[i];
			while (d * d <= x) {
				if (x % d == 0) {
					while (x % d == 0)
						x /= d;
					factored[i].add(d);
				}

				d++;
			}

			if (x > 1 || factored[i].isEmpty())
				factored[i].add(x);
		}

		// primesL : a list of all primes that occur in factored
		Set<Integer> primes = new HashSet();
		for (List<Integer> facs: factored)
			for (int x: facs)
				primes.add(x);

		int[] primesL = new int[primes.size()];
		int t = 0;
		for (int x: primes)
			primesL[t++] = x;

		// primeToIndex.get(v) == i  iff  primes[i] = v
		Map<Integer, Integer> primeToIndex = new HashMap();
		for (int i = 0; i < primesL.length; ++i)
			primeToIndex.put(primesL[i], i);

		DSU dsu = new DSU(primesL.length);
		for (List<Integer> facs: factored)
			for (int x: facs)
				dsu.union(primeToIndex.get(facs.get(0)), primeToIndex.get(x));

		int[] count = new int[primesL.length];
		for (List<Integer> facs: factored)
			count[dsu.find(primeToIndex.get(facs.get(0)))]++;

		int ans = 0;
		for (int x: count)
			if (x > ans)
				ans = x;
		return ans;
	}
}

class DSU {
	int[] parent;
	public DSU(int N) {
		parent = new int[N];
		for (int i = 0; i < N; ++i)
			parent[i] = i;
	}
	public int find(int x) {
		if (parent[x] != x) parent[x] = find(parent[x]);
		return parent[x];
	}
	public void union(int x, int y) {
		parent[find(x)] = find(y);
	}
}
