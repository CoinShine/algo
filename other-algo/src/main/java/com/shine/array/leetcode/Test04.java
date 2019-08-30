package com.shine.array.leetcode;

/**
 * DESCRIPTION:两个有序数组，查找中位数，要求时间复杂度O(log(m+n))
 *
 * @author shine
 * @create 2019-04-10 18:55
 */
public class Test04 {

	/** C1是短的
	 * 如果C1或C2已经到头了怎么办？ C1和C2长度不等时
	 * 这种情况出现在：如果有个数组完全小于或大于中值。因为C1比C2短，可能有2种情况：
	 * - C1 = 0 —— 数组1整体都比中值大，L1 >R2，中值在2中
	 * - C1 = 2n —— 数组1整体都比中值小，L2 >R1,中值在2中
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n = nums1.length;
		int m = nums2.length;
		if(n > m)   //保证数组1一定最短
			return findMedianSortedArrays(nums2,nums1);
		int L1=0,L2=0,R1=0,R2=0,c1,c2,lo = 0, hi = 2*n;  //虚拟加了'#'所以数组1是2*n+1长度，角标为2n
		while(lo <= hi)
		{
			c1 = (lo+hi)/2;  //c1是二分的结果
			c2 = m+n- c1;    //一半的数为（2m+1+2n+1）/2=m+n+1,角标从0开始，中值位置角标为m+n
			//noinspection Duplicates
			L1 = (c1 == 0)? Integer.MIN_VALUE:nums1[(c1-1)/2];
			R1 = (c1 == 2*n)? Integer.MAX_VALUE:nums1[c1/2];
			L2 = (c2 == 0)? Integer.MIN_VALUE:nums2[(c2-1)/2];
			R2 = (c2 == 2*m)? Integer.MAX_VALUE:nums2[c2/2];
			if(L1 > R2)
				hi = c1-1; //c1太大
			else if(L2 > R1)
				lo = c1+1; //c2太大，c1太小
			else
				break;
		}

		return (Math.max(L1,L2)+ Math.min(R1,R2))/2.0;
	}


	public static void main(String[] args){
		int[] a = {1,4,7,9,11};
		int[] b = {2,3,5,8};
		int[] c = {5,6,7,8};
		int[] d = {1,2,3,4};
		double middle = findMedianSortedArrays(a, b);
		//double middle = findMedianSortedArrays(c, d);
		System.out.println(middle);
	}


}
