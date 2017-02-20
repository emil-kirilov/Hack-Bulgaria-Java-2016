package hackathonPrefixOracle;

public class BranchNode extends Node{
	//references the first node of the subtrie 
	//Node firstNode;
	//represents the skip 
	private int skip;
	//stands for the letters of the alphabet and '#' (End Of Word)
	private Node[] children = new Node[27];
	private int childrenCount = 0;
	
	
	public BranchNode(int skip) {
		this.skip = skip;
	}
	
	public int getSkip() {
		return skip;
	}
	
	public void setSkip(int skip) {
		this.skip = skip;
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

	public void insert(String word, int index) {
		//vijdame simvola na poziciq charAt na koi index otgovarq i zapazvame referenciqta v tozi index
		int radixIndex = BranchNodeHelper.getRadixIndex( word, index);
		Node reference = children[radixIndex]; 
		
		if ( reference == null ) {
			//sazdavame novo listo
			children[radixIndex] = new InformationNode(word);
			
		} else {
			index++;
			
			if (reference.isInformationNode()) {
				//mestim infoNode-a kato sazdavame nov branchNode
				InformationNode infoNode = (InformationNode) reference;
				
				//kompresirame Trie kato setame saotvetniq skip
				int newSkip = BranchNodeHelper.getSkipAfterIndex(skip, infoNode.getWord(), word);
				children[radixIndex] = new BranchNode(newSkip);
				((BranchNode) children[radixIndex]).setChild( BranchNodeHelper.getRadixIndex( infoNode.getWord(), index), infoNode); //childrenCount stays the same
				((BranchNode) children[radixIndex]).insert(word, newSkip + skip); //childrenCount += 1?
				
				
			} else {
				//izpolzvame sashtesvuvashtiq branchNode kato padame rekursivno v nov insert
				((BranchNode) reference).insert(word, index);
				
			}
		}
	}
}
