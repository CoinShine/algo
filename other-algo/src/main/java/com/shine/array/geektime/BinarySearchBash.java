package com.shine.array.geektime;

import org.junit.Test;

/**
 * DESCRIPTION:基础二分查找
 *
 * @author coins
 * @create 2019-04-21 16:55
 */
public class BinarySearchBash {

	/**
	 * 查找第一个等于给定值得元素
	 * @param a
	 * @param n
	 * @param key
	 * @return
	 */
	public int search01(int[] a,int n,int key){
		int low = 0;
		int high = n-1;
		while(low <= high){
			int mid = low +((high-low)>>1);
			if(a[mid]< key){
				low = mid+1;
			}else{
				high = mid-1;
			}
		}
		if(low<n && a[low] == key){
			return low;
		}else {
			return -1;
		}
	}


	/**
	 * 查找最后一个等于给定值得元素
	 * @param a
	 * @param n
	 * @param key
	 * @return
	 */
	public int search02(int[] a,int n,int key){
		int low = 0;
		int high = n-1;
		while(low <= high){
			int mid = low +((high-low)>>1);
			if(a[mid]<= key){
				low = mid+1;
			}else{
				high = mid-1;
			}
		}
		if(high<n && a[high] == key){
			return high;
		}else {
			return -1;
		}
	}

	//public int search03(int[] a,int n,int key){
	//	int low = 0;
	//	int high = n-1;
	//	while(low <= high){
	//		int mid = low +((high-low)>>1);
	//		if(a[mid]< key){
	//			low = mid+1;
	//		}else{
	//			high = mid-1;
	//		}
	//	}
	//	if(low<n && a[low] >= key){
	//		return low;
	//	}else {
	//		return -1;
	//	}
	//}


	public int search04(int[] a,int n,int key){
		int low = 0;
		int high = n-1;
		while(low <= high){
			int mid = low +((high-low)>>1);
			if(a[mid]<= key){
				if(mid == n-1 || a[mid+1] >key) return mid;
				else low = mid+1;
			}else{
				high = mid-1;
			}
		}
		return -1;
	}
	@Test
	public  void testSearch(){
		int[] a = {1,3,4,5,6,8,8,8,11,18};
		int index1 = search01(a, a.length, 8);
		int index2 = search02(a, a.length, 8);
		//int index3 = search03(a, a.length, 6);
		int index4 = search04(a, a.length, 6);
		System.out.println("第一个等于："+index1);
		System.out.println("最后一个等于："+index2);
		//System.out.println("第一个大于等于："+index3);
		System.out.println("最后一个小于等于："+index4);
	}
}
