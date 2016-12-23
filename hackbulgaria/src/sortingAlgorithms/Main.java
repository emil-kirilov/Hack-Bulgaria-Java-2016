package sortingAlgorithms;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
//		MergeSort<Integer> ms = new MergeSort<Integer>();
//		ArrayList<Integer> arr = new ArrayList<Integer>();
//		arr.add(2);
//		arr.add(22456667);
//		arr.add(345);
//		arr.add(4);
//		arr.add(6);
//		arr.add(22);
//		arr.add(32);
//		arr.add(17);
//		arr.add(2);
//		arr.add(1);
//		arr.add(4);
//		arr.add(5);
//		arr.add(9);
//		arr = ms.mergeSort(arr);
//		for (int i = 0; i < arr.size(); i++) {
//			System.out.print(arr.get(i) + ", ");
//		}
//		
//	}
		
		QuickSort<Integer> qs = new QuickSort<Integer>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(2);
		arr.add(22456667);
		arr.add(345);
		arr.add(4);
		arr.add(6);
		arr.add(22);
		arr.add(32);
		arr.add(17);
		arr.add(2);
		arr.add(1);
		arr.add(4);
		arr.add(5);
		arr.add(9);
		qs.quickSort(arr);
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i) + ", ");
		}
		
	}
}
