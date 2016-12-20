package InterviewDemo;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		FunctionManager funcs = new FunctionManager();
		
		//Adding the function declarations in the FunctionManager object
		int numberOfDefinitions = Integer.parseInt(s.nextLine());
		for (int i = 0; i < numberOfDefinitions; i++) {
			funcs.add(new Function(s.nextLine()));
		}

		s.nextLine();
		//Saving the function composition
		String composition = s.nextLine();
		s.close();
		
		//Getting the order of execution of the composition
		String[] reversedOrderOfExecution = composition.split(" "); 
		String[] orderOfExecution = new String[reversedOrderOfExecution.length / 2 + 1];
		
		int counter = 0;
		for (int i = reversedOrderOfExecution.length - 1; i > -1; i-=2) {
			orderOfExecution[counter] = reversedOrderOfExecution[i];
			counter++;
		}
		
		//Checking whether the composition is valid
		boolean result = true;
		String inputForNextFunc = null;
		for (int i = 0; i < orderOfExecution.length; i++) {
			Function curFunc = funcs.searchByName(orderOfExecution[i]);
			if (inputForNextFunc == null || curFunc.getInputType().equals(inputForNextFunc)) {
				inputForNextFunc = curFunc.getOutputType();
			} else {
				result = false;
				break;
			}
		}
	
		System.out.println(result);
	}
}
