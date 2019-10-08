package com.shine.geektime;

import org.junit.Test;
/**
 * description: 二分查找变形
 * @author shine
 * @date 2019/10/8 10:13
 * @version 1.0
 */
public class BinarySearchChange {
	/**
	 * 查找第一个值为key的数，要求时间复杂度为O(log(n))
	 * @param a
	 * @param n
	 * @param key
	 */
	public int findNum(int[] a,int n ,int key){
		int lo = 0;
		int hi = n-1;
		while(lo<=hi){
			int mid = lo+((hi-lo)>>1);//防止溢出
			if(a[mid]<key){
				lo=mid+1;
			}else {
				hi=mid-1;
			}
		}
		if (lo < n && a[lo] == key) {
			return lo;
		} else {
			return -1;
		}
	}

	public int findNum2(int[] a,int n,int key){
		int lo = 0;
		int hi = n-1;
		while(lo<=hi){
			int mid = lo+((hi-lo)>>1);
			if(a[mid]<key){
				lo=mid+1;
			}else if(a[mid]>key){
				hi=mid-1;
			}else{
				if (mid ==n-1 || a[mid+1]!=key) {
						return mid;
				}else lo=mid+1;
			}
		}
		return -1;
	}


	@Test
	public void testFindNum(){
		int[] a = {1,3,5,6,8,8,8,10,18};
		int length = a.length;
		System.out.println(findNum2(a, length, 8));
	}
}
