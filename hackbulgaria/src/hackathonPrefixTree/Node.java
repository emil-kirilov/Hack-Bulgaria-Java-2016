package hackathonPrefixTree;

public abstract class Node implements Cloneable{
	public boolean isElement() {
		return getClass().getSimpleName().equals("Element");		
	}
	
	public static Element asElement(Node node) {
		return (Element) node;
	}
	
	public static Branch asBranch(Node node) {
		return (Branch) node;
	}
}
