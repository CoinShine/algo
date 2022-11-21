package com.shine.bytedance;

import java.util.ArrayList;

/**
 * description: 24 点游戏
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 *
 * 示例 1:
 *
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 *
 *
 * 共有三个运算符：C(4,1)*C(4,1)*C(4,1) = 4*4*4
 * 首先从4个中选两个排列 A(4,2)=12 得到一个数，再和剩下的两个数 组合成三个数再选两个 A(3,2) = 6,然后再生成一个数 在选两个
 * A(2,2) = 2
 *
 * 我们将对我们的数字或结果数字执行 3 次二元运算（+，-，*，/ 是运算）。因为 - 和 / 不满足交换律，我们必须仔细考虑 a / b 和 b / a。
 * 对于在我们的列表中移除 a, b 这两个数字的每一种方法，以及它们可能产生的每种结果，如 a + b、a / b等，我们将采用递归的方法解决这个较小的数字列表上的问题
 * @author shine
 * @date 2019/11/17 12:30
 * @version 1.0
 */
public class Test679 {
	public boolean judgePoint24(int[] nums) {
		ArrayList<Double> A = new ArrayList<>();
		for (int v: nums) A.add((double) v);
		return solve(A);
	}
	private boolean solve(ArrayList<Double> nums) {
		if (nums.size() == 0) return false;
		if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;
		//当四个的时候，第一次选两个数时有4*3种，之后选运算符为12*4=48，
		//然后3个选两个为3*2种，之后选运算符：6*4 = 24；
		//最后2个选运算符：2*4种（有先后顺序之分哦）
		//不过+和*满足交换律
		for (int i = 0; i < nums.size(); i++) {
			for (int j = 0; j < nums.size(); j++) {
				if (i != j) {
					ArrayList<Double> nums2 = new ArrayList<>();
					for (int k = 0; k < nums.size(); k++) if (k != i && k != j) { //不能添加i，j因为i和j所在位置的数接下里我们要进行计算
						nums2.add(nums.get(k));
					}
					for (int k = 0; k < 4; k++) {
						if (k < 2 && j > i) continue; //加和乘满足交换律
						if (k == 0) nums2.add(nums.get(i) + nums.get(j));
						if (k == 1) nums2.add(nums.get(i) * nums.get(j));
						if (k == 2) nums2.add(nums.get(i) - nums.get(j));
						if (k == 3) {
							if (nums.get(j) != 0) {
								nums2.add(nums.get(i) / nums.get(j));
							} else {
								continue;
							}
						}
						if (solve(nums2)) return true;
						//之前添加的那个运算符不行，扔了之前那个结果吧,回溯到之前选择运算符
						nums2.remove(nums2.size() - 1);
					}
				}
			}
		}
		return false;
	}
}
