package InterviewDemo;

import java.util.ArrayList;
import java.util.List;

public class FunctionManager {
	List<Function> funcs = new ArrayList<Function>();
	
	public void add(Function f) {
		funcs.add(f);
	}
	
	public Function searchByName(String name) {
		Function result = null;
		
		for (int i = 0; i < funcs.size(); i++) {
			Function curFunc = funcs.get(i);
			if (curFunc.getName().equals(name)) {
				result = curFunc;
			}
		}
		
		return result;
	}
}
