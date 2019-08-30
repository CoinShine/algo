package com.shine.geektime;

/**
 * description: 快排
 * @author shine
 * @date 2019/08/29 15:27
 * @version 1.0
 */
public class QuickSort {

	private static void sort(int[] a,int lo,int  hi){
		if(lo>=hi){
			return;
		}
		int q = quickSort(a,lo,hi);
		sort(a, lo, q-1);
		sort(a,q+1, hi);
	}

	private static int quickSort(int[] a,int lo,int  hi){

		//三数取中,防止超过int最大值
		int mid=lo+((hi-lo)>>1);
		if(a[mid]>a[hi]){
			swap(a[mid],a[hi]);
		}
		if(a[lo]>a[hi]){
			swap(a[lo],a[hi]);
		}
		if(a[mid]>a[lo]){
			swap(a[mid],a[lo]);
		}

		int key = a[lo];
		while(lo<hi){
			while(a[hi]>key&&lo<hi){
				hi--;
			}
			a[lo] = a[hi];
			while(a[lo]<key&& lo<hi){
				lo++;
			}
			a[hi]=a[lo];
		}
		a[hi] = key;
		return hi;
	}

	public static void swap(int a,int b){
		int temp=a;
		a=b;
		b=temp;
	}
	public static void main(String[] args){
	  int[] a = {23,46,0,8,11,18};
	  sort(a, 0, 5);
	}
}
