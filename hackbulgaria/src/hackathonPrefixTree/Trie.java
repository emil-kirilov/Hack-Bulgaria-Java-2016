package hackathonPrefixTree;

public class Trie implements Trieable{
	private Branch root = new Branch("#"); //empty word -> epsilon
	
	@Override
	public void insert(String word) {
		// Root is always a Branch so I can call insert
		root.insert(word);
	}

	@Override
	public boolean search(String word) {
		//appends EOW and call search on the root
		return root.search(word + "#");
	}
	
	public String toString() {
		//TODO fix this
		helper(root,"");		
		return "";
	}
	
	// recursively print all nodes 
	private static void helper(Branch node, String ident) {
		for(int i = 0; i < 27; i++) {
			Node child = node.getChild(i);
			if (child != null) {		
				if (child.isElement()) {
					System.out.println( ident + Node.asElement(child).getWord() + "\n");					
				} else {
					System.out.println( ident + Helper.getRadixToChar(i) + "\n");
					helper((Branch) node.getChild(i), ident + "    ");
				}
			}
		}
	}

}