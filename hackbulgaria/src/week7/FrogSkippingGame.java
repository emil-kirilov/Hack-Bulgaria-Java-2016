package week7;

import java.util.ArrayList;
import java.util.Scanner;

public class FrogSkippingGame {
	private ArrayList<Character> frogs =  new ArrayList<Character>();
	int noFrog;
	ArrayList<Integer> steps = new ArrayList<Integer>();
	
	public FrogSkippingGame(int frogCount) {
		noFrog = frogCount; 
		
		for (int i = 0; i < frogCount; i++) {
			frogs.add('♞');
		}
		frogs.add('_');
		for (int i = 0; i < frogCount; i++) {
			frogs.add('❄');
		}
	}
	
	private void generateSteps() {
		int sign = 1;
		for (int i = 0; i < frogs.size() / 2; i++) {
			sign *= -1; 
			
			for (int j = 0; j < i; j++) {
				steps.add(2 * sign);
			}
			steps.add(sign);
		}
		
		sign *= -1; 
		for (int j = 0; j < frogs.size() / 2; j++) {
			steps.add(2 * sign);
		}
		
		for (int i = 0; i < frogs.size() / 2; i++) {
			sign *= -1;
			
			steps.add(sign);
			
			for (int j = frogs.size() / 2 - 1; j > i; j--) {
				steps.add(2 * sign);
			}
		}
	}
	
	public void solve() {
		printFrogs();
		generateSteps();
		for (int i = 0; i < steps.size(); i++) {
			move(steps.get(i));
		}
	}
	
	private void move(int step) {
		char orientation = frogs.get(noFrog + step);
		frogs.set(noFrog, orientation);
		frogs.set(noFrog + step, '_');
		noFrog += step;
		printFrogs();
	}
	
	public void printFrogs() {
		for (int i = 0; i < frogs.size(); i++) {
			System.out.print(frogs.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int numberOfFrogs = s.nextInt();
		FrogSkippingGame fsg = new FrogSkippingGame(numberOfFrogs);
		fsg.solve();
		
		s.close();
	}
}
