package hackathonPrefixOracle;

public class BranchNode extends Node {
	// references the first node of the subtrie
	String wordSoFar;
	// stands for the letters of the alphabet and '#' (End Of Word)
	private Node[] children = new Node[27];

	public BranchNode(String wordSoFar) {
		this.wordSoFar = wordSoFar;
	}

	public char getCharOfInterest(String word) {
		return word.charAt(wordSoFar.length());
	}

	public int indexOfCharOfInterest() {
		return wordSoFar.length() + 1;
	}

	public Node getChild(int index) {
		return children[index];
	}

	public void setChild(int index, Node node) {
		children[index] = node;
	}

	public void insert(String word) {
		if (word.length() > indexOfCharOfInterest()) {
			// We either add the word as InformationNode or find its place in
			// the BranchNode
			int charOfInterestToRadix = BranchNodeHelper.getCharToRadix(getCharOfInterest(word));
			Node nodeInPlaceOfWord = children[charOfInterestToRadix];

			// If node is empty -> create a new InformationNode
			// wordSoFar becomes the word itself
			if (nodeInPlaceOfWord == null) {
				children[charOfInterestToRadix] = new InformationNode(word);

				// If node is an InformationNode -> see at which character they differ
				// Make a new BranchNode with wordSoFar up until the character which
				// is different Insert the word and the InformationNode in the the BranchNode
				// At indexes corresponding to the first different character
			} else if (nodeInPlaceOfWord.isInformationNode()) {
				// nodeInPlaceOfWord is an InformationNode containing a word
				InformationNode infoNode = Node.asInformationNode(nodeInPlaceOfWord);

				int firstIndexOfDifference = BranchNodeHelper.sameUntil(infoNode.getWord(), word);

				// creating a new Branch with a wordSoFar the common prefix
				// of the InformationNode and the word we want to insert
				children[charOfInterestToRadix] = new BranchNode(word.substring(0, firstIndexOfDifference));

				// places the InformationNode in its place in the new BranchNode
				int newInfoNodeInxed = BranchNodeHelper
						.getCharToRadix(infoNode.getWord().charAt(firstIndexOfDifference));
				Node.asBranchNode(children[charOfInterestToRadix]).setChild(newInfoNodeInxed, infoNode);

				// places the word in its place in the new BranchNode as an
				// InformatioNode
				int newWordIndex = BranchNodeHelper.getCharToRadix(word.charAt(firstIndexOfDifference));
				Node.asBranchNode(children[charOfInterestToRadix]).setChild(newWordIndex, new InformationNode(word));
			} else {
				String wordSoFarBranchNode = Node.asBranchNode(nodeInPlaceOfWord).wordSoFar;
				if (word.length() > wordSoFarBranchNode.length()) {					
					Node.asBranchNode(nodeInPlaceOfWord).insert(word);
				} else {					
					// The word we wish to insert is shorter than the common prefix of
					// the words in the BranchNode
					// We need to build an intermediate BranchNode figuring out when
					// the end of the common prefix ends and adding one
					int firstIndexOfDifference = BranchNodeHelper.sameUntil(wordSoFarBranchNode, word);
					int lastCommonCharIndex = firstIndexOfDifference - 1;
		
					// last common char between word and in Branch's wordSoFar to radix index
					int intermediateIndex = BranchNodeHelper.getCharToRadix(word.charAt(lastCommonCharIndex));
		
					// saving the BranchNode with common prefix of its
					// InformationNodes longer that the word we want to insert
					BranchNode commonPrefixBranch = Node.asBranchNode(children[charOfInterestToRadix]);
					// creating the intermediate BranchNode setting wordSoFar
					children[intermediateIndex] = new BranchNode(wordSoFarBranchNode.substring(0, firstIndexOfDifference));
		
					// The old BranchNode should be placed at index corresponding to
					// the first letter of its wordSoFar that is not common with the word we wanted to insert
					int indexOfOldBranchInNewBranch = BranchNodeHelper
							.getCharToRadix(commonPrefixBranch.wordSoFar.charAt(firstIndexOfDifference));
					// placing the old BranchNode into the new intermediate BranchNode
					Node.asBranchNode(children[intermediateIndex]).setChild(indexOfOldBranchInNewBranch, commonPrefixBranch);

					// the first letter of its wordSoFar that is not common with the word we wanted to insert
					int indexOfWordInNewBranch = BranchNodeHelper
							.getCharToRadix(word.charAt(firstIndexOfDifference));
					// placing the word as an InformatonNode in the new intermediate BranchNode
					Node.asBranchNode(children[intermediateIndex]).setChild(indexOfWordInNewBranch, new InformationNode(word));
				}
			}
		}
	}

	public boolean search(String word) {
		System.out.println(indexOfCharOfInterest());
		if (word.length() < indexOfCharOfInterest()) {
			return false;
		}
		int radix = BranchNodeHelper.getCharToRadix( getCharOfInterest(word) );
		Node node = children[radix];
		if (node == null) {
			return false;
		} else if (node.isInformationNode()) {
			return Node.asInformationNode(node).getWord().equals(word);
		} else {
			return Node.asBranchNode(node).search(word);
		}
	}
}
