package InterviewDemo;

import static org.junit.Assert.*;

import org.junit.Test;

public class FunctionTest {

	@Test
	public void functionConstructorShouldCreateAValidFunction() {
		String functionDeclaration = "f :: String -> Int";
		Function testFunction = new Function(functionDeclaration);
		
		assertEquals("Name is wrong!!!. Expected \"f\", recieved " + testFunction.getName(), 
				"f", testFunction.getName());
		assertEquals("Input type is wrong!!!. Expected \"String\", recieved " + testFunction.getName(), 
				"String", testFunction.getInputType());
		assertEquals("Output type is wrong!!!. Expected \"Int\", recieved " + testFunction.getName(), 
				"Int", testFunction.getOutputType());
	}
	
	

}
