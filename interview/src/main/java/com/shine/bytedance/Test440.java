package com.shine.bytedance;

/**
 * description:字典序的第K小数字
 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 *
 * 注意：1 ≤ k ≤ n ≤ 109。
 *
 * 示例 :
 * 输入:
 * n: 13   k: 2
 * 输出:
 * 10
 * 解释:
 * 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * 字节跳动最常考题之一
 * 每一个节点都拥有 10 个孩子节点，因为作为一个前缀 ，它后面可以接 0~9 这十个数字。而且你可以非常容易地发现，整个字典序排列也就是对十叉树进行先序遍历。1, 10, 100, 101, ... 11, 110 ...
 *
 * 回到题目的意思，我们需要找到排在第k位的数。找到他的排位，需要搞清楚三件事情:
 *
 * 1、怎么确定一个前缀下所有子节点的个数？
 * 2、如果第 k 个数在当前的前缀下，怎么继续往下面的子节点找？
 * 3、如果第 k 个数不在当前的前缀，即当前的前缀比较小，如何扩大前缀，增大寻找的范围？
 *
 * @author shine
 * @date 2019/11/15 11:43
 * @version 1.0
 */
public class Test440 {

	public int findKthNumber(int n,int k){
		int p = 1;//作为一个指针，指向当前所在位置，当p==k时，也就是到了排位第k的数
		int prefix = 1;//前缀
		while(p < k) {
			long count = getCount(prefix, n);//获得当前前缀下所有子节点的和
			if(p + count > k) { //第k个数在当前前缀下
				prefix *= 10;
				p++; //把指针指向了第一个子节点的位置，比如11乘10后变成110，指针从11指向了110
			} else if(p + count <= k) { //第k个数不在当前前缀下
				prefix ++;
				p += count;//注意这里的操作，把指针指向了下一前缀的起点
			}
		}
		return prefix;
	}

	/**
	 * 确定指定前缀下所有子节点数
	 * @param prefix
	 * @param n
	 * @return
	 */
	private long getCount(long prefix, long n){
		long cur = prefix; // prefix是前缀，n是上界
		long next = prefix+1; // 下一个前缀
		long count = 0;
		while (cur <=n){
			count+=Math.min(n+1,next) - cur; // 考虑next的值大于上界的情况，下一个前缀的起点减当前前缀起点
			cur*=10;
			next*=10; // 到下一层延伸，如果说刚刚prefix是1，next是2，那么现在分别变成10和201为前缀的子节点增加10个，十叉树增加一层, 变成了两层
		}
		return count;
	}

	  //for(let cur = prefix, next = prefix + 1; cur <= n; cur *= 10, next *= 10)
		//	count += Math.min(next, n+1) - cur;

}
