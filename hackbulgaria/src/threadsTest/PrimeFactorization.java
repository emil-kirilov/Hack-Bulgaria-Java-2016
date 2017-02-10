package threadsTest;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorization implements Runnable {
	int number;
	List<Integer> primeFactors;
	String primeFactorsToString;
	
	public PrimeFactorization(int number) {
		this.number = number;
		primeFactors = new ArrayList<>();
		primeFactorsToString = "The prime factors of " + number + " are ";
	}
	
	@Override
	public void run() {
		for(int i = 2; i <= number; i++) {
			if (number % i == 0 ) {
				primeFactors.add(i);
				while (number % i == 0) {
					number /= i;
				}
			}
		}
		
		for(Integer i : primeFactors) {
			primeFactorsToString += i + ", ";
		}
		primeFactorsToString = primeFactorsToString.substring(0, primeFactorsToString.length() - 2) + ".";
		System.out.println(primeFactorsToString);
	}
}
