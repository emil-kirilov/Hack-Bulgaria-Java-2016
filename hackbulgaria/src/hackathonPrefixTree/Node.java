package hackathonPrefixTree;

import java.util.Set;

public abstract class Node {
	public boolean isElement() {
		return getClass().getSimpleName().equals("Element");		
	}
	
	public static Element asElement(Node node) {
		return (Element) node;
	}
	
	public static Branch asBranch(Node node) {
		return (Branch) node;
	}
	
	public abstract Set<String> suggest(String word);
	public abstract boolean search(String word);
}
