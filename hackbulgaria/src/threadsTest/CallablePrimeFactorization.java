package threadsTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallablePrimeFactorization implements Callable<String>{
	int number;
	
	public CallablePrimeFactorization(int number) {
		this.number = number;
	}
	
	@Override
	public String call() throws Exception {
		String result = "The prime factors of " + number + " are ";
		List<Integer> primeFactors = new ArrayList<>();
		
		for(int i = 2; i <= number; i++) {
			if (number % i == 0 ) {
				primeFactors.add(i);
				while (number % i == 0) {
					number /= i;
				}
			}
		}
		for(Integer i : primeFactors) {
			result += i + ", ";
		}
		result = result.substring(0, result.length() - 2) + ".";
		return result;
	}

}
