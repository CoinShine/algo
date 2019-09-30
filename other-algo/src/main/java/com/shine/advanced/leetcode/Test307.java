package com.shine.advanced.leetcode;

/**
 * description: 区域和查询
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 *
 * 示例:
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 说明:
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 *
 * 使用线段树 是一种平衡二叉搜索树（完全二叉树），对于线段树的非叶子节点[a,b]
 * 它的左儿子为[a,(a+b)/2] 右儿子为[(a+b)/2+1,b],最后叶子节点数目为N，与数组
 * 下标对应，建立树的时间复杂度为O(nlogn) ,其他操作的时间复杂度为O(logn)
 * @author shine
 * @date 2019/9/30 10:16
 * @version 1.0
 */
public class Test307 {

	private int[] value;
	private int rightEnd;
	public Test307(int[] nums) {
		if(nums.length == 0) return;
		int n = nums.length * 4; // 一般线段树数组大小是原数组大小的4倍
		value = new int[n];
		rightEnd = nums.length-1;
		buildSegmentTree(value,nums,0,0,nums.length-1);
	}

	public void update(int i, int val) {
		updateSegmentTree(value,0,0,rightEnd,i,val);
	}

	public int sumRange(int i, int j) {
		return sumRangeSegmentTree(value,0,0,rightEnd,i,j);
	}

	void updateSegmentTree(int[] value,int pos,int left,int right,int index,int newValue){ // pos为数组脚标
		if(left == right && left == index){
			value[pos] = newValue;
			return;
		}
		int mid = (left+right)/2;
		if(index<=mid){
			updateSegmentTree(value,pos*2+1,left,mid,index,newValue);
		}else{
			updateSegmentTree(value,pos*2+2,mid+1,right,index,newValue);
		}
		value[pos]=value[pos*2+1]+value[pos*2+2];
	}

	int sumRangeSegmentTree(int[] value,int pos,int left,int right,int qLeft,int qRight){
		if(qLeft>right || qRight<left) return 0;
		if(qLeft<=left&&qRight>=right){
			return value[pos];
		}
		int mid = (left+right)/2;
		return sumRangeSegmentTree(value,pos*2+1,left,mid,qLeft,qRight)+sumRangeSegmentTree(value,pos*2+2,mid+1,right,qLeft,qRight);
	}
	private void buildSegmentTree(int[] vals,int[] nums,int pos,int left,int right){ // 线段的左右端点
		if(left == right){
			vals[pos] = nums[left]; // nums 是原数组，vals是和数组
			return;
		}
		int mid = (left+right)/2;

		buildSegmentTree(vals,nums,pos*2+1,left,mid);
		buildSegmentTree(vals,nums,pos*2+2,mid+1,right);
		vals[pos] = vals[pos*2+1]+vals[pos*2+2];
	}

}




/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
