package week4;

public class fastMult {
	int[] numbers;
	int res = 1;
	
	public fastMult(int[] arr) {
		numbers = arr;
		for (int i = 0; i < arr.length; i++) {
			res *= numbers[i];
		}
	}
	
	public int without(int n){
		return res / numbers[n-1];
	}
	
	public static void main(String[] args){
		int[] arr = {1,5,8,10};
		fastMult test = new fastMult(arr);
		System.out.println(test.without(1));
		
	}
}
