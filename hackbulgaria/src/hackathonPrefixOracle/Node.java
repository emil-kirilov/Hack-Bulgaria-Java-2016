package hackathonPrefixOracle;

public abstract class Node {
	public boolean isElement() {
		return getClass().getSimpleName().equals("InformationNode");		
	}
	
	public static Element asElement(Node node) {
		return (Element) node;
	}
	
	public static Branch asBranch(Node node) {
		return (Branch) node;
	}
}
