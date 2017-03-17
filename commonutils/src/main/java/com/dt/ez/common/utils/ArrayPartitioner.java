package com.dt.ez.common.utils;

public class ArrayPartitioner {

	private int start;
	private int end;
	private int partLen;
	
	/* Index range */
	private int left, right;
	
	private void reset () {
		this.left = this.right = start;
	}
	
	public boolean next () {
		/* First loop */
		if (right == start) {
			right = left + partLen - 1;
			return true;
		}
		
		left += partLen;
		
		if (left <= end) {
			int tmp = left + partLen - 1;
			if (tmp > end) {
				right = end;
				return true;
			}
			right = tmp;
			return true;
		}
		
		return false;
	}
	
	/* TODO... range checking */
	public ArrayPartitioner (int length, int partLen) {
		this.start = 0;
		this.end = length - 1;
		this.partLen = partLen;
		reset ();
	}
	
	/* TODO... range checking */
	public ArrayPartitioner (int start, int end, int partLen) {
		this.start = start;
		this.end = end;
		this.partLen = partLen;
		reset ();
	}
	
	public int getPartLen () {
		return this.partLen;
	}
	
	public int getLeft () {
		return this.left;
	}
	
	public int getRight () {
		return this.right;
	}
	
	/* Testing. */
	public static void main(String[] args) {
		ArrayPartitioner part = new ArrayPartitioner (100, 98);
		
		while (part.next ()) {
			int startIdx = part.getLeft ();
			int endIdx = part.getRight ();
			System.out.println ("(" + startIdx + "," + endIdx + ")");
		}
		
//		int start = 0, end = 99, p = 20;

//		int i = start;
//		while (i <= end) {
//			int left = i;
//			int right = i + p - 1;
//			System.out.println ("(" + left + "," + right + ")");
//			i += p;
//		}
		
//		do {
//			int left = i;
//			int right = i + p - 1;
//			System.out.println ("(" + left + "," + right + ")");
//			i += p;
//		} while (i <= end);
		

	}

}



