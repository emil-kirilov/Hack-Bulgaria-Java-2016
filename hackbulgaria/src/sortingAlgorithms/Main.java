package sortingAlgorithms;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
//	public static void main(String[] args) {
//		MergeSort<Integer> ms = new MergeSort<Integer>();
//		//QuickSort<Integer> qs = new QuickSort<Integer>();
//		ArrayList<Integer> arr = new ArrayList<Integer>();
//		for (int i = 0; i < 60000; i++) {
//			arr.add((int) Math.ceil(Math.random() * 10000));
//			
//		}
//		System.out.println("---------");
////		qs.quickSort(arr);
//		arr = ms.mergeSort(arr);
//		for (int i = 0; i < arr.size(); i++) {
//			System.out.print(arr.get(i) + ", ");
//		}
//	}
	
	public static void main(String[] args) {
		   //String text = "delivery 4 2016-10-25 12:31 420,369 23 5 54 20";
		   //Pattern p = Pattern.compile("([a-z]+)\\s([0-9])+\\s[0-9]+\\-[0-9]+\\-[0-9]+\\s[0-9]+//:[0-9]+\\s([0-9]+)\\,([0-9]+)\\s([0-9]+)\\s([0-9]+)");
		String text = "some.text.here only but not Some other " + 
                "there some.name.separated.by.dots and.we are done!";
        Pattern p = Pattern.compile("\\w+(\\.\\w+)+");
		Matcher m = p.matcher(text);
		   while(m.find()) {
		       System.out.println(m.group());
		   }
		}
}
