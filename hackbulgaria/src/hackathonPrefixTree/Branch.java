package hackathonPrefixTree;

public class Branch extends Node implements Cloneable {
	// common prefix for children
	String wordSoFar;
	// first char of difference between all children
	int charOfInterest = Integer.MAX_VALUE;
	//int childrenCount = 0;
	// stands for the letters of the alphabet - starting letters of the children and '#' (End Of Word)
	private Node[] children = new Node[27];

	public Branch(String wordSoFar) {
		this.wordSoFar = wordSoFar;
	}
	
	public Branch(String wordSoFar, int charOfInterest) {
		this.wordSoFar = wordSoFar;
		this.charOfInterest = charOfInterest;
	}

	public char getCharOfInterest(String word) {
		return word.charAt(charOfInterest);
	}
	
	public void setChild(int index, Node node) {
		children[index] = node;
	}
	
	public Node getChild(int index) {
		return children[index];
	}

	public void insert(String word) {
		// 0) this node has to determine what to do with the word:
		// 0.1) if wordSoFar is prefix of the word and the word is long enough to provide the charOfInterest
		// 0.2) else we have to create a new branch that has a shorter wordSoFar, so we guarantee the same prefix of wordSoFar
		
		// 0.1)
		if (word.length() > charOfInterest && Helper.samePrefix(word, wordSoFar, charOfInterest)) {
			// word has come to the right place
			// 1) 		we need to establish the word's index in children
			// 2) 		check whether that index is free/ occupied
			// 2.1) 	free -> new Element(word)
			// 2.2) 	there's a node at the index we need to insert the word into
			// 2.2.1) 	Element -> create a Branch, place Element and word in it
			// 2.2.2)   Branch -> insert word in it

		
			// 1)
			int indexInChildren = Helper.indexInChildren(word, charOfInterest);
		
			// 2)
			Node nodeAtIndex = children[indexInChildren];
			
			//2.1)
			if(nodeAtIndex == null) {
				children[indexInChildren] = new Element(word);
			
			// 2.2)
			} else {
				
				// 2.2.1)
				if (nodeAtIndex.isElement()) {
					Element elementAtIndex = Node.asElement(nodeAtIndex);
					
					//creating the new branch
					int newCharOfInterest = Helper.sameUntil(word, elementAtIndex.getWord());
					String newWordSoFar = word.substring(0, newCharOfInterest);
					children[indexInChildren] = new Branch(newWordSoFar, newCharOfInterest);
					
					// Finding the place of:
					// the element in the new branch and putting it there
					int indexOfElementInBranch = Helper.indexInChildren( elementAtIndex.getWord() , newCharOfInterest);
					Node.asBranch( children[indexInChildren] ).setChild(indexOfElementInBranch, elementAtIndex);
					
					// the word in the new branch and putting it there
					int indexOfWordInBranch = Helper.indexInChildren( word , newCharOfInterest);
					Node.asBranch( children[indexInChildren] ).setChild( indexOfWordInBranch , new Element(word));
				
				// 2.2.2)
				} else {
					Node.asBranch(nodeAtIndex).insert(word);
				}
			}
		
		// 0.2)
		} else {
			// word is not long enough to provide the charOInterest or wordSoFar is not prefix of the word
			// 1) copy current branch - copy this
			// 2) compression -> this = new Branch, charOInterest = length of the common prefix (word's and branch's wordSoFar)
			// 3) place the word in its corresponding index with setChild (no recursion)
			// 4) place the copy of the branch at its corresponding index ( use the new this.charOfInterest)
			
			// 1)
			try {
				Node copyOfBranch = (Branch) this.clone();
				Node.asBranch(copyOfBranch).children = children.clone();
			
				
				// 2)
				// wiping the current Branch and setting it anew
				charOfInterest = Helper.sameUntil(word, wordSoFar);
				wordSoFar = word.substring(0, charOfInterest);
				for (int i = 0; i < 27; i++) {
					children[i] = null;
				}
				
				// 3)
				int indexOfWordInChildren = Helper.indexInChildren(word, charOfInterest);
				setChild( indexOfWordInChildren ,new Element(word));
				
				// 4)
				int indexOfCopyBranchInChildren = Helper.indexInChildren( Node.asBranch(copyOfBranch).wordSoFar, charOfInterest);
				setChild( indexOfCopyBranchInChildren , copyOfBranch);
			
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean search(String word) {
		if (word.length() < charOfInterest) {
			return false;
		}
		int radix = Helper.getCharToRadix( getCharOfInterest(word) );
		Node node = children[radix];
		if (node == null) {
			return false;
		} else if (node.isElement()) {
			return Node.asElement(node).getWord().equals(word);
		} else {
			return Node.asBranch(node).search(word);
		}
	}
}
