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
			// We either add the word as InformationNode or find its place in the BranchNode
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
						// lastCommonCharIndex = firstIndexOfDifference - 1;
					
					// Creating the intermediate branch 
					// Its wordSoFar should be the common prefix between word and wordSoFarBranchNode
					Node intermediateBranch = new BranchNode(word.substring(0, firstIndexOfDifference));
					
					// intermediateBranch's index in the *current branch will be determined by the letter following
					// the common prefix of wordSoFar and wordSoFarBranchNode
					// * intermediateBranch will be positioned between the current 
					// branch(this) and the branch with the long common prefix (nodeInPlaceOfWord)
					//char firstLetterAfterCommonPrefix = wordSoFarBranchNode.charAt( indexOfCharOfInterest() );
					//int intermediateIndex = BranchNodeHelper.getCharToRadix(firstLetterAfterCommonPrefix);
					
					// saving the BranchNode with common prefix of its InformationNodes longer that the word we want to insert
					BranchNode commonPrefixBranch = Node.asBranchNode(children[charOfInterestToRadix]);
					
					// placing the intermediate branch at commonPrefixBranch's place
					children[charOfInterestToRadix] = intermediateBranch;
					
					// placing the word at its place by the character of interest of the intermediate branch
					char charOfInterestInWord = word.charAt( Node.asBranchNode(intermediateBranch).indexOfCharOfInterest());
					int indexOfWordInIntermediateBranch = BranchNodeHelper.getCharToRadix(charOfInterestInWord);
					Node.asBranchNode(children[charOfInterestToRadix]).setChild(indexOfWordInIntermediateBranch, new InformationNode(word));
					
					// placing the branch with the long common prefix at its place by the character of interest of the intermediate branch
					char charOfInterestInCommonPrefixBranch = word.charAt( Node.asBranchNode(intermediateBranch).indexOfCharOfInterest());
					int indexOfCommonPrefixBranchInIntermediateBranch = BranchNodeHelper.getCharToRadix(charOfInterestInCommonPrefixBranch);
					Node.asBranchNode(children[charOfInterestToRadix]).setChild(indexOfCommonPrefixBranchInIntermediateBranch, commonPrefixBranch);
					
					
					
					/*
					// creating the intermediate BranchNode setting wordSoFar
					// right index XXXX
					int XXXX = BranchNodeHelper.getCharToRadix( wordSoFarBranchNode.charAt(wordSoFar.length()) );
					int YYYY = BranchNodeHelper.getCharToRadix( word.charAt(wordSoFar.length()) );
					//System.out.println(
					//		wordSoFarBranchNode.substring(0, wordSoFar.length()) + "{" + wordSoFarBranchNode.charAt(wordSoFar.length()) + "}" + wordSoFarBranchNode.substring(wordSoFar.length() + 1));
					//System.out.println(
					//		word.substring(0, wordSoFar.length()) + "{" + word.charAt(wordSoFar.length()) + "}" + word.substring(wordSoFar.length() + 1));
					
					children[XXXX] = new BranchNode(wordSoFarBranchNode.substring(0, firstIndexOfDifference));
		
					// The old BranchNode should be placed at index corresponding to
					// the first letter of its wordSoFar that is not common with the word we wanted to insert
					int indexOfOldBranchInNewBranch = BranchNodeHelper
							.getCharToRadix(commonPrefixBranch.wordSoFar.charAt(firstIndexOfDifference));
					// placing the old BranchNode into the new intermediate BranchNode
					Node.asBranchNode(children[XXXX]).setChild(indexOfOldBranchInNewBranch, commonPrefixBranch);

					// the first letter of its wordSoFar that is not common with the word we wanted to insert
					int indexOfWordInNewBranch = BranchNodeHelper
							.getCharToRadix(word.charAt(firstIndexOfDifference));
					// placing the word as an InformatonNode in the new intermediate BranchNode
					Node.asBranchNode(children[XXXX]).setChild(indexOfWordInNewBranch, new InformationNode(word));
					*/
				}
			}
		}
	}

	public boolean search(String word) {
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
