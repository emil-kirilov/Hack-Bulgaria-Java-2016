package sortingAlgorithms;

import java.util.ArrayList;

public class QuickSort<T extends Comparable <T>>{
	public static final int FIRST = 0;
	
	public void quickSort(ArrayList<T> arr) {
		quickSortR(arr, FIRST, arr.size()- 1);
	}
	
	private void quickSortR(ArrayList<T> arr, int low, int high) {
		if(low < high) {
			int indexPivot = partition(arr, low, high);
		
			quickSortR(arr, FIRST, indexPivot - 1);
			quickSortR(arr, indexPivot + 1, high);
		}
	}
	
	private int partition(ArrayList<T> arr, int low, int high) {
		//T pivot = middleOfThree(arr.get(low), arr.get((high - low) / 2 + low), arr.get(high));
		T pivot = arr.get(high);
		int indexOfPivot = arr.indexOf(pivot);
		int indexOfLastSmallerThanPivot = low;
		
		//swap(arr, indexOfPivot, high);
		indexOfPivot = high;
		
		for (int i = low; i < high; i++) {
			if (arr.get(i).compareTo(pivot) < 0) {
				swap(arr, indexOfLastSmallerThanPivot, i);
				indexOfLastSmallerThanPivot++;
			}
		}
		
		swap(arr, indexOfLastSmallerThanPivot, indexOfPivot);
		indexOfPivot = indexOfLastSmallerThanPivot;
		
		return indexOfPivot;
	}

	private void swap(ArrayList<T> arr, int index1, int index2) {
		T temp = arr.get(index1);
		
		arr.set(index1, arr.get(index2));
		arr.set(index2, temp);
	}

	//slows down the algorithm
//	private T middleOfThree(T first, T middle, T last ){
//		T max = null;
//		
//		if( first.compareTo(middle) > 0) {
//			if ( first.compareTo(last) > 0) {
//				max = last;
//			} else {
//				max = first;
//			}
//		} else {
//			if (middle.compareTo(last) > 0) {
//				max = last;
//			} else {
//				max = middle; 
//			}
//		}
//		return max;
//	}
}

