package com.shine.bytedance;

/**
 * description:移除盒子
 *
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
 * 当你将所有盒子都去掉之后，求你能获得的最大积分和。
 *
 * 示例 1：
 * 输入:
 *
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * 输出:
 *
 * 23
 * 解释:
 *
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 * ----> [1, 3, 3, 3, 1] (1*1=1 分)
 * ----> [1, 1] (3*3=9 分)
 * ----> [] (2*2=4 分)
 *
 * 方法 1：暴力方法【超时】
 * 算法
 * 暴力方法是显然的，我们试图移除每一个可能的元素，并计算得分以及剩下的序列继续递归
 * 复杂度分析
 * 时间复杂度：O(n!)，f(n) 是找到 n 个盒子有 n 种不同颜色的方案，显然 f(n) =n×f(n−1)，所以结果 n! 是时间复杂度。
 * 空间复杂度：O(n^2)。递归树的深度为 n，每层包含最多 n 个 newBoxes 元素。
 *
 * 方法 2：记忆化搜索
 * 算法
 * 1、前面的方法出现了很多重复计算，例如考虑数组 [3, 2, 1, 4, 4, 4]。我们考虑移除 3 然后移除 2 之后的数组为 [1, 4, 4, 4]。
 * 	这和先移除 2 再移除 3 的代价是一样的。我们可以筛选这样的搜索，通过记忆化的方式来完成。
 *
 * 2、但是问题是在这个例子下记忆化搜索太难了。我们并不能直接使用起始节点和结束节点决定最大分数，因为这个分数并不只依赖于子序列，
 * 	也依赖于之前的移动对当前数组的影响，这可能让最终的子序列不是一个连续的子串。例如，考虑数组 [3, 2, 1, 4, 4, 2, 4, 4]，子数组 [3, 2, 1, 4] 的
 * 	分数取决于元素 2（下标 5）是否 被移除，因为者会影响数字 4 是否连成完整的一段。
 *
 * 3、因此，为了保存这个信息，我们需要在记忆化数组中加上额外一维，告诉我们当前子序列有多少个元素被合并在一起。我们使用一个 dp 数组，用来存储最大分数，
 * 	对于一个特定的子序列。对于元素 dp[l][r][k]，l表示起始下标，r 表示结束下表，k表示与第 r个元素相似的元素个数，可以在最后一起合并并得到最终的分数
 * 	存入dp[l][r][k] 中。
 *
 * 4、用下面的例子来理解。考虑一个子序列 [x_l, x_{l+1},.., x_i,.., x_r, 6, 6, 6] 如果 x_r=6 那么 dp[l][r][3] 表示 boxes[l:r] 可以获得的最大分数，
 * 	同时有 3 个数字 6 在 x_r之后。
 *
 * 5、现在考虑如何填上 dp 数组。考虑上面提到的子序列，我们先对 dp[l][r][k] 赋初值，考虑合并最后 k+1 个相似元素，然后处理之后的剩余数组。因此，初值
 * 	为 dp[l][r][k]=dp[l][r−1][0]+(k+1)∗(k+1)。然后我们合并所有后缀相似元素，所以数值 0 是因为第 r-1 元素之后不存在相似元素。
 *
 * 6、但上面的情况不是唯一情况，我们可以考虑一个更优解法，如果找到一些 boxes[l:r] 中的相似元素和尾部元素一起合并。
 *
 * 7、因此，我们找到 boxes[l:r] 中的元素，和结尾的 kk 个元素相似，也就是和第 r 个元素相似，当一个 boxes[i] 找到的时候，我们就检查这个新情况是否会带来
 * 	更多的得分，如果是更新数组。
 *
 * 8、为了更好地理解，考虑这个子序列 [x_l, x_{l+1},.., x_i,.., x_r, 6, 6, 6]，如果 x_i = x_r = 6，我们可以通过移除中间元素合并 x_i和 x_r获得更高的价值。
 * 	移除 [x_{i+1}, x_{i+2},..., x_{r-1}] 元素的价值是 dp[i+1][r-1][0]，而之前数列为 [x_l, x_{l+1},.., x_i,x_r, 6, 6, 6]可以更新成 dp[l][i][k+1]。
 *
 * 9、最后，dp 的更新式为：dp[l][r][k]=max(dp[l][r][k],dp[l][i][k+1]+dp[i+1][r-1][0])。
 *
 * 10、最后，dp[0][n-1][0] 就是最后的结果。实现如下，使用 calculatePoints 函数用递归更好的计算结果
 * @author shine
 * @date 2019/11/18 11:38
 * @version 1.0
 */
public class Test546 {
	public int removeBoxes(int[] boxes) {
		return remove(boxes);
	}
	public int remove(int[] boxes)
	{
		if(boxes.length==0)
			return 0;
		int res=0;
		for(int i=0,j=i+1;i<boxes.length;i++)
		{
			while(j<boxes.length && boxes[i]==boxes[j])
				j++;
			int[] newboxes=new int[boxes.length-(j-i)];
			for(int k=0,p=0;k<boxes.length;k++)
			{
				if(k==i)
					k=j;
				if(k<boxes.length)
					newboxes[p++]=boxes[k];
			}
			res=Math.max(res,remove(newboxes)+(j-i)*(j-i));
		}
		return res;
	}

	/**
	 * 递归+记忆化
	 * @param boxes
	 * @return
	 */
	public int removeBoxes1(int[] boxes) {
		int[][][] dp = new int[100][100][100];
		return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
	}

	public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
		if (l > r) return 0;
		if (dp[l][r][k] != 0) return dp[l][r][k];
		while (r > l && boxes[r] == boxes[r - 1]) {
			r--;
			k++;
		}
		dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
		for (int i = l; i < r; i++) {
			if (boxes[i] == boxes[r]) {
				dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
			}
		}
		return dp[l][r][k];
	}
}
