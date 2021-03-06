package InterviewDemo;

public class Function {
	String name;
	String inputType;
	String outputType;
	
	public Function(String name, String inputType, String outputType) {
		this.name = name;
		this.inputType = inputType;
		this.outputType = outputType;
	}

	public Function(String defenition) {
		String[] funcDef =  defenition.split(" ");
		this.name = funcDef[0];
		this.inputType = funcDef[2];
		this.outputType = funcDef[4];
	}
	
	public String toString() {
		return name + " :: " + inputType + " -> " + outputType;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInputType() {
		return inputType;
	}
	
	public String getOutputType() {
		return outputType;
	}
	
	
}
