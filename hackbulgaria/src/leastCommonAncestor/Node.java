package leastCommonAncestor;

public class Node {
	public String name;
	public Node parent;
	
	public Node(Node parent, String name) {
		this.parent = parent;
		this.name = name;
	}
}
