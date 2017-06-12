package TP3;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Sort implements ISort {
	protected int[] vector;
	
	public abstract String getName();
	
	Sort (int[] vector) {
		this.vector = vector;
	}
	
	Sort (int length) {
		this.vector = createRandomVector(length);
	}
	
	public int[] getVector () {
		return this.vector;
	}
	
	public void setVector (int[] vector) {
		this.vector = vector;
	}
	
	public long timedSort () {
		long initialTime = System.currentTimeMillis();
		sort();
		return System.currentTimeMillis() - initialTime;
	}
	
	public static int[] createRandomVector (int length) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (length --> 0) list.add(length);
		Collections.shuffle(list);
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static int[] createOrderedVector (int length) {
		int[] vector = new int[length];
		while (length --> 0) vector[length] = length;
		return vector;
	}
	
	public static int[] createReversedOrderedVector (int length) {
		int[] vector = new int[length];
		for (int i = 0, j = length; i < length; i ++) vector[i] = --j;
		return vector;
	}
	
	public static int compare (int element1, int element2) {
		if (element1 - element2 > 0) return 1;
		if (element1 - element2 < 0) return -1;
		return 0;
	}
}
