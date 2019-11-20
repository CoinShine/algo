package com.shine.didi;

import java.util.List;

/**
 * description:加权嵌套序列和 II
 * dfs
 * @author shine
 * @version 1.0
 * @date 2019/11/20 16:41
 */
public class Test364 {
	private int maxLevel = 0; //嵌套集合的最大层数
	private int sum = 0; //加权求和值

	public int depthSumInverse(List<NestedInteger> nestedList) {
		countLevels(nestedList, 0);
		sum(nestedList, maxLevel);
		return sum;
	}

	/**
	 * 计算嵌套集合的层数，递归运算
	 * 嵌套集合
	 * 上层集合所在的层数，最外层集合时level值为 0
	 * @param nestedList
	 * @param level
	 */
	private void countLevels(List<NestedInteger> nestedList, int level) {
		if (!nestedList.isEmpty()) {
			level++;
		}
		boolean isNested = false;
		for (NestedInteger ni : nestedList) {
			if (!ni.isInteger()) {
				isNested = true;
				countLevels(ni.getList(), level);
			}
		}
		if (!isNested) {
			if (level > maxLevel) {
				maxLevel = level;
			}
		}
	}

	/**
	 * 求和，递归运算
	 * 嵌套集合
	 * 最外层集合的权重值
	 * @param nestedList
	 * @param weightedValue
	 */
	public void sum(List<NestedInteger> nestedList, int weightedValue) {
		for (NestedInteger ni : nestedList) {
			if (ni.isInteger()) {
				sum += weightedValue * ni.getInteger();
			} else {
				sum(ni.getList(), weightedValue - 1);
			}
		}
	}
}

interface NestedInteger {
	// Constructor initializes an empty nested list.
	//public NestedInteger();

	// Constructor initializes a single integer.
	//public NestedInteger(int value);

	// @return true if this NestedInteger holds a single integer, rather than a nested list.
	public boolean isInteger();

	// @return the single integer that this NestedInteger holds, if it holds a single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();

	// Set this NestedInteger to hold a single integer.
	public void setInteger(int value);

	// Set this NestedInteger to hold a nested list and adds a nested integer to it.
	public void add(NestedInteger ni);

	// @return the nested list that this NestedInteger holds, if it holds a nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}
