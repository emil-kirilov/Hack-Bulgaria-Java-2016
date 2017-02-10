package sudoku2;

import java.util.concurrent.Callable;

public class OneToNineChecker implements Callable<Boolean>{
	private static final int SIZE = 9;
	int[] row;
	
	public OneToNineChecker(int[] row) {
		this.row = row;
	}
	
	
	@Override
	public Boolean call() throws Exception {
		boolean[] numbers = new boolean[SIZE];
		
		for (int i = 0; i < row.length; i++) {
			numbers[row[i] - 1] = true;
		}
		
		boolean result = true;
		for (int i = 0; i < numbers.length; i++) {
			result = result && numbers[i];
		}
		return result;
	}
}
