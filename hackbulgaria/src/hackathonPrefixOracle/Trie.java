package hackathonPrefixOracle;

public class Trie implements Trieable{
	private BranchNode root = new BranchNode(""); //empty word -> epsilon
	
	@Override
	public void insert(String word) {
		// Root is always a BranchNode so I can call insert
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
	private static void helper(BranchNode node, String ident) {
		for(int i = 0; i < 27; i++) {
			Node child = node.getChild(i);
			if (child != null) {		
				if (child.isInformationNode()) {
					System.out.println( ident + Node.asInformationNode(child).getWord() + "\n");					
				} else {
					System.out.println( ident + BranchNodeHelper.getRadixToChar(i) + "\n");
					helper((BranchNode) node.getChild(i), ident + "    ");
				}
			}
		}
	}

}