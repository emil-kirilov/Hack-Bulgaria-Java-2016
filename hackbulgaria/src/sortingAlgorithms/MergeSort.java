package sortingAlgorithms;

import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> {
	public static final int FIRST = 0;
	public ArrayList<T> mergeSort(ArrayList<T> arr) {
		int n = arr.size();
		
		if(n == 1) {
			return arr;
		}
		
		ArrayList<T> left = new ArrayList<T>();
		for (int i = 0; i < n / 2; i++) {
			left.add(arr.get(i));
		}
		
		ArrayList<T> right = new ArrayList<T>();
		for (int i = n / 2; i < n; i++) {
			right.add(arr.get(i));
		}
		
		left = mergeSort(left);
		right = mergeSort(right);
		
		return merge(left, right);
	}
	
	private ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right) {
		ArrayList<T> sorted = new ArrayList<T>();
		
		while(!left.isEmpty() && !right.isEmpty()) {
			T leftFirst = left.get(FIRST);
			T rightFirst = right.get(FIRST);
			if (rightFirst.compareTo(leftFirst) > 0) {
				sorted.add(left.get(FIRST));
				left.remove(FIRST);
			} else {
				sorted.add(right.get(FIRST));
				right.remove(FIRST);
			}
		}
		
		if (left.isEmpty()) {
			sorted.addAll(right);
		} else {
			sorted.addAll(left);
		}
		
		return sorted;
	}
	
	

	
}
