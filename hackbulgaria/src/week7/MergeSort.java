package week7;

public class MergeSort<T extends Comparable<T>> {
	public T[] sort(T[] arr, int lower, int upper) {
		if (upper - lower < 32) {
			insertionSort(arr, lower, upper);
		} else {			
			merge(sort(arr, 0, arr.length/2), sort(arr, arr.length/2 + 1, arr.length - 1), arr, 0);
		}
	}
	
	private void merge(T[] left, T[] right, T[] inPlace, int from) {
		int length = left.length + right.length;
		int indexLeft = 0;
		int indexRight = 0;
		T currentMin;
		for (int i = from; i < length; i++) {
			if (left[indexLeft].compareTo( right[indexRight] ) > 0) {
				currentMin = right[indexRight];
				indexRight++;
			}
			else {
				currentMin = left[indexLeft];
				indexLeft++;
			}
			inPlace[i] = currentMin;
			
		}
	}
	
	private void insertionSort(T[] arr, int lower, int upper){
		for (int i = lower + 1; i < upper + 1; i++) {
			for (int j = i - 1; j >= lower; j--) {
				if (arr[j].compareTo( arr[j + 1] ) > 0) {
					T temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] whole = {4, 3, 123, 31, 14, 55, 7, 24, 5, 6, 1, 0, 53, 13};
		int[] left = {4, 3, 123, 31, 14, 55, 7};
		int[] right = {24, 5, 6, 1, 0, 53, 13};
		merge(left, right, whole, 0);
	}
}
