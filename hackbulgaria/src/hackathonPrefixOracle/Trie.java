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
		return helper(root, "");
	}
	
	private static String helper(Node node, String res) {
		if(node != null) {
			for(int i = 0; i < 27; i++) {
				if (node.isInformationNode()) {
					res +=  Node.asInformationNode(node).getWord() + "\n";					
				} else {
					res += 
				}
			}
			String addTab = "    ";
			
		}
		
		return res;
	}

}