package sortingAlgorithms;

import java.util.ArrayList;

public class QuickSort<T extends Comparable <T>>{
	public static final int FIRST = 0;
	
	private T biggestOfThree(T first, T middle, T last ){
		T max = null;
		
		if( first.compareTo(middle) > 0) {
			if ( first.compareTo(last) > 0) {
				max = first;
			} else {
				max = last;
			}
		} else {
			if (middle.compareTo(last) > 0) {
				max = middle;
			} else {
				max = last; 
			}
		}
		
		return max;
	}
	
	
	public void quickSort(ArrayList<T> arr) {
		quickSortR(arr, FIRST, arr.size());
	}
	
	private void quickSortR(ArrayList<T> arr, int low, int high) {
		if(low < high) {
			int indexPivot = partition(arr, low, high);
		
			quickSortR(arr, FIRST, indexPivot);
			quickSortR(arr, indexPivot + 1, high);
		}
	}
	
	private void swap(ArrayList<T> arr, int index1, int index2) {
		T temp = arr.get(index1);
		
		arr.set(index1, arr.get(index2));
		arr.set(index2, temp);
	}
	
	private int partition(ArrayList<T> arr, int low, int high) {
		T pivot = biggestOfThree(arr.get(low), arr.get((high - low) / 2 + low), arr.get(high-1));
		int indexOfPivot = arr.indexOf(pivot);
		int indexOfLastSmallerThanPivot = low;
		
		swap(arr, indexOfPivot, high - 1);
		
		for (int i = low; i < high; i++) {
			if (arr.get(i).compareTo(pivot) < 0) {
				swap(arr, indexOfLastSmallerThanPivot, i);
				indexOfLastSmallerThanPivot++;
			}
		}
		
		swap(arr, indexOfLastSmallerThanPivot, indexOfPivot);
		
		return indexOfLastSmallerThanPivot;
	}
}
