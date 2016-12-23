package sortingAlgorithms;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		MergeSort<Integer> ms = new MergeSort<Integer>();
		//QuickSort<Integer> qs = new QuickSort<Integer>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < 60000; i++) {
			arr.add((int) Math.ceil(Math.random() * 10000));
			
		}
		System.out.println("---------");
//		qs.quickSort(arr);
		arr = ms.mergeSort(arr);
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i) + ", ");
		}
	}
}
