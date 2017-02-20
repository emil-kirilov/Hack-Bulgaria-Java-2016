package hackathonPrefixOracle;

public class BranchNode extends Node{
	//references the first node of the subtrie 
	//Node firstNode;
	private int charOfInterest;
	//stands for the letters of the alphabet and '#' (End Of Word)
	private Node[] children = new Node[27];
	private int childrenCount = 0;
	
	
	public BranchNode(int charOfInterest) {
		this.charOfInterest = charOfInterest;
	}
	
	public int getcharOfInterest() {
		return charOfInterest;
	}
	
	public void setcharOfInterest(int charOfInterest) {
		this.charOfInterest= charOfInterest;
	}
	
	public Node getChild(int index) {
		return children[index];
	}
	
	public void setChild(int index, Node node) {
		children[index] = node;
	}
	
	public int getChildrenCount() {
		return childrenCount;
	}

	public void insert(String word, int charToCompare) {
		childrenCount += 1; //?
		//vijdame simvola na poziciq charAt na koi index otgovarq i zapazvame referenciqta v tozi index
		int radixIndex = BranchNodeHelper.getCharToRadix( word.charAt(charToCompare));
		Node reference = children[radixIndex]; 
		
		if ( reference == null ) {
			//sazdavame novo listo
			children[radixIndex] = new InformationNode(word);
		} else {
			if (reference.isInformationNode()) {
				//mestim infoNode-a kato sazdavame nov branchNode
				InformationNode infoNode = (InformationNode) reference;
				
				//kompresirame Trie kato setame saotvetniq skip
				int newCharOfInterest = BranchNodeHelper.sameUntil(infoNode.getWord(), word);
				children[radixIndex] = new BranchNode(newCharOfInterest);
				
				int newRadixIndexOfInfoNode = BranchNodeHelper.getCharToRadix(infoNode.getWord().charAt(newCharOfInterest));
				((BranchNode) children[radixIndex]).setChild( newRadixIndexOfInfoNode, infoNode);
				((BranchNode) children[radixIndex]).insert(word, newCharOfInterest);
				
			} else {
				//izpolzvame sashtesvuvashtiq branchNode kato padame rekursivno v nov insert
				((BranchNode) reference).insert(word, charToCompare + 1); //comparing next char
			}
		}
	}
}
