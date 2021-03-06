package InterviewDemo;

import static org.junit.Assert.*;

import org.junit.Test;

public class FunctionManagerTest {

	@Test
	public void addingAFunctionShouldIncreasingTheArraySize() {
		FunctionManager fm = new FunctionManager();
		Function func = new Function("f :: String -> Int");
		fm.add(func);
		
		assertEquals("Function Manager does not add functions properly!" , 1, fm.funcs.size());
	}
	
	@Test
	public void searchByNameShouldFindAFunctionIfItWasAdded() {
		FunctionManager fm = new FunctionManager();
		Function func = new Function("f :: String -> Int");
		fm.add(func);
		Function found = fm.searchByName(func.getName());
		
		assertEquals("Function Manager does not search for functions properly!" , func, found);
	}
}
