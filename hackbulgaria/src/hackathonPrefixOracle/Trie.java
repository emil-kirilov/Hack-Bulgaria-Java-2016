package hackathonPrefixOracle;

public class Trie implements Trieable{
	private BranchNode root;
	
	public Trie() {
		this.root = new BranchNode(0);
	}

	@Override
	public void insert(String word) {
		root.insert(word, 0);
	}

	@Override
	public boolean search(String word) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		//ne mu e chista rabotata na toq metod
		helper(root,"");		
		return "";
	}
	
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