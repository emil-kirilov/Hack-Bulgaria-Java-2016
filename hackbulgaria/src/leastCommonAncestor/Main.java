package leastCommonAncestor;

public class Main {
	public static void main(String[] args) {
		Node root = new Node(null, "root");
		Node a = new Node(root, "a");
		Node b = new Node(a, "b");
		Node c = new Node(b, "c");
		Node d = new Node(a, "d");
		Node e = new Node(d, "e");
		Node f = new Node(e, "f");
		Node g = new Node(f, "g");
		
		LCA lca = new LCA();
		Node leastCommonAncestor = lca.getLCA(e, g);
		System.out.println("LCA is node " + leastCommonAncestor.name);
	}
}
