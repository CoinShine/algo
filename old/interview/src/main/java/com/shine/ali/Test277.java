package com.shine.ali;

/**
 * description:搜寻名人
 * 使用双指针分别从头和尾两个节点遍历
 * @author shine
 * @date 2019/11/18 12:22
 * @version 1.0
 */
public class Test277 {
	public int findCelebrity(int n) {
		int left = 0;
		int right = n - 1;
		// 先排除掉不是名人的候选人
		while (left != right) {
			if (knows(left, right)) {  // 如果left认识right 则right有可能是名人
				left += 1;
			}
			else { // // 如果left认识right 则right肯定不是名人
				right -= 1;
			}
		}
		// 最后left和right会相遇，这个人是潜在的名人
		// 按照定义验证：
		// case1：候选人认识其他人，那么他不是名人
		// case2：存在至少一个人不认识候选人，那么他也不是名人

		for (int i = 0; i < n; i++) {
			if (i != left && (knows(left, i)||!knows(i, left))) {
				return -1;
			}
		}
		return left;
	}

	/**
	 * 辅助函数 判断a是否认识b
	 * @param a
	 * @param b
	 * @return
	 */
	boolean knows(int a, int b) {
		return false;
	}
}
