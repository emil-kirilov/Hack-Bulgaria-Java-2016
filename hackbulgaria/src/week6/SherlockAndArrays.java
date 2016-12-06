package week6;

import java.util.Scanner;

public class SherlockAndArrays {
	public static boolean isSherlock(int[] numbers) {
		boolean result = false;
		int sum = sum(numbers);
		
		if (sum - numbers[0] == 0 || sum - numbers[numbers.length - 1] == 0) {
			return true;
		}
		
		int accSum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum -= numbers[i];
			if ( accSum == sum){
				return true;
			}
			accSum += numbers[i];
		}
		
		
		return result;
		
	}
	
	private static int sum(int[] arr) {
		int result = 0;
		
		for (int i = 0; i < arr.length; i++) {
			result += arr[i];
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int numberOfTests = sc.nextInt();
		
		for (int i = 0; i < numberOfTests; i++) {
			int testSize = sc.nextInt();
			
		
			int[] numbers = new int[testSize];
			for (int j = 0; j < testSize; j++) {
				numbers[j] = sc.nextInt();
			}
			
			if ( isSherlock(numbers)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			
		}
		
		sc.close();
	}
}
