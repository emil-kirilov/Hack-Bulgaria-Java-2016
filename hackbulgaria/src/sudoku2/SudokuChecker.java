package sudoku2;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SudokuChecker {
	private final static int SIZE = 9;
	private final int[][] sudoku;
	//private boolean legit = true; could come in hand if using Runnables
	private ExecutorService cachedPool = Executors.newCachedThreadPool();
	
	public SudokuChecker(int[][] sudoku) {
		//TODO validate
		this.sudoku = sudoku;
	}
	
	private void printLine(int i) {
		for (int j = 0; j < SIZE; j++) {
			System.out.print(sudoku[i][j] + "  ");
			if(j % 3 == 2 && j < 6) {
				System.out.print("| ");
			}
		}
		System.out.println();
	}
	
	public void printSudoku() {
		for (int i = 0; i < SIZE; i++) {
			printLine(i);
			if (i % 3 == 2 && i < 6) {
				System.out.println("-----------------------------");
			}
		}
	}
	
	
	
	
	public boolean checkSudoku() throws InterruptedException, ExecutionException {
		boolean result = true;
		LinkedList<Future<Boolean>> futures = new LinkedList<Future<Boolean>>();
		
		callRowCheckers(futures);
		callColCheckers(futures);
		callLittleSquareCheckers(futures);
		
		while(!futures.isEmpty()) {
			Future<Boolean> future = futures.poll();
			if (future.isDone()) {
				if(!future.get()) {
					result = false;
					break;
				} 
			} else {
				futures.add(future);
			}
		}
		cachedPool.shutdown();
		return result;
	}
	
	private void callRowCheckers(LinkedList<Future<Boolean>> futures) {
		for (int i = 0; i < SIZE; i++) {
			OneToNineChecker OneToNineCheckerTask = new OneToNineChecker(copyOfRow(i));
			Future<Boolean> temp = cachedPool.submit(OneToNineCheckerTask);
			futures.add(temp);
		}
	}
	
	private int[] copyOfRow(int row) {
		if( row < 0 || row > SIZE - 1) {
			return null;
		}
		
		int[] result = new int[SIZE];
		for (int col = 0; col < SIZE; col++) {
			result[col] = sudoku[row][col];
		}
		return result;
	}
	
	private void callColCheckers(LinkedList<Future<Boolean>> futures) {
		for (int i = 0; i < SIZE; i++) {
			OneToNineChecker OneToNineCheckerTask = new OneToNineChecker(copyOfCol(i));
			Future<Boolean> temp = cachedPool.submit(OneToNineCheckerTask);
			futures.add(temp);
		}
	}
	
	private int[] copyOfCol(int col) {
		if( col < 0 || col > SIZE - 1) {
			return null;
		}
		
		int[] result = new int[SIZE];
		for (int row = 0; row < SIZE; row++) {
			result[row] = sudoku[row][col];
		}
		return result;
	}
	
	private void callLittleSquareCheckers(LinkedList<Future<Boolean>> futures) {
		for (int i = 1; i < SIZE; i+=3) {
			for (int j = 1; j < SIZE; j+=3) {
				OneToNineChecker OneToNineCheckerTask = new OneToNineChecker(copyOfLittleSquare(i, j));
				Future<Boolean> temp = cachedPool.submit(OneToNineCheckerTask);
				futures.add(temp);
			}
		}
	}
	
	private int[] copyOfLittleSquare(int row, int col) {
		if( col < 0 || col > SIZE - 1 || row < 0 || row > SIZE - 1) {
			return null;
		}
		
		int[] result = new int[SIZE];
		int resultIndex = 0;
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				result[resultIndex] = sudoku[i + row][j + col];
				resultIndex++;
			}
		}
		return result;
	}
}
