package hackathonPrefixOracle;

import java.util.stream.Stream;

public class Branch extends Node implements Cloneable {
	// common prefix for children
	String wordSoFar;
	// first char of difference between all children
	int charOfInterest = Integer.MAX_VALUE;
	int childrenCount = 0;
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
		if (word.length() > charOfInterest) {
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
			
		} else {
			// word is not long enough to provide the charOInterest
			// 1) copy current branch - copy this
			// 2) compression -> this = new Branch, charOInterest = length of the common prefix (word's and branch's wordSoFar)
			// 3) place the word in its corresponding index with setChild (no recursion)
			// 4) place the copy of the branch at its corresponding index ( use the new this.charOfInterest)
			
			// 1)
			try {
				Node copyOfBranch = (Branch) this.clone();
			
				// 2)
				// wiping the current Branch and setting it anew
				charOfInterest = Helper.sameUntil(word, wordSoFar);
				wordSoFar = word.substring(0, charOfInterest);
				Stream.of(children).forEach(x -> x = null);
				
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
			
			
			/*
			// We either add the word as ElementNode or find its place in the Branch
			int charOfInterestToRadix = Helper.getCharToRadix(getCharOfInterest(word));
			Node nodeInPlaceOfWord = children[charOfInterestToRadix];

			// If node is empty -> create a new ElementNode
			// wordSoFar becomes the word itself
			if (nodeInPlaceOfWord == null) {
				children[charOfInterestToRadix] = new ElementNode(word);

				// If node is an ElementNode -> see at which character they differ
				// Make a new Branch with wordSoFar up until the character which
				// is different Insert the word and the ElementNode in the the Branch
				// At indexes corresponding to the first different character
			} else if (nodeInPlaceOfWord.isElementNode()) {
				// nodeInPlaceOfWord is an ElementNode containing a word
				ElementNode infoNode = Node.asElementNode(nodeInPlaceOfWord);

				int firstIndexOfDifference = Helper.sameUntil(infoNode.getWord(), word);

				// creating a new Branch with a wordSoFar the common prefix
				// of the ElementNode and the word we want to insert
				children[charOfInterestToRadix] = new Branch(word.substring(0, firstIndexOfDifference));

				// places the ElementNode in its place in the new Branch
				int newInfoNodeInxed = Helper
						.getCharToRadix(infoNode.getWord().charAt(firstIndexOfDifference));
				Node.asBranch(children[charOfInterestToRadix]).setChild(newInfoNodeInxed, infoNode);

				// places the word in its place in the new Branch as an
				// InformatioNode
				int newWordIndex = Helper.getCharToRadix(word.charAt(firstIndexOfDifference));
				Node.asBranch(children[charOfInterestToRadix]).setChild(newWordIndex, new ElementNode(word));
			} else {
				String wordSoFarBranch = Node.asBranch(nodeInPlaceOfWord).wordSoFar;
				if (word.length() > wordSoFarBranch.length()) {					
					Node.asBranch(nodeInPlaceOfWord).insert(word);
				} else {					
					// The word we wish to insert is shorter than the common prefix of
					// the words in the Branch
					// We need to build an intermediate Branch figuring out when
					// the end of the common prefix ends and adding one
					int firstIndexOfDifference = Helper.sameUntil(wordSoFarBranch, word);
						// lastCommonCharIndex = firstIndexOfDifference - 1;
					
					// Creating the intermediate branch 
					// Its wordSoFar should be the common prefix between word and wordSoFarBranch
					Node intermediateBranch = new Branch(word.substring(0, firstIndexOfDifference));
					
					// intermediateBranch's index in the *current branch will be determined by the letter following
					// the common prefix of wordSoFar and wordSoFarBranch
					// * intermediateBranch will be positioned between the current 
					// branch(this) and the branch with the long common prefix (nodeInPlaceOfWord)
					//char firstLetterAfterCommonPrefix = wordSoFarBranch.charAt( indexOfCharOfInterest() );
					//int intermediateIndex = Helper.getCharToRadix(firstLetterAfterCommonPrefix);
					
					// saving the Branch with common prefix of its ElementNodes longer that the word we want to insert
					Branch commonPrefixBranch = Node.asBranch(children[charOfInterestToRadix]);
					
					// placing the intermediate branch at commonPrefixBranch's place
					children[charOfInterestToRadix] = intermediateBranch;
					
					// placing the word at its place by the character of interest of the intermediate branch
					char charOfInterestInWord = word.charAt( Node.asBranch(intermediateBranch).indexOfCharOfInterest());
					int indexOfWordInIntermediateBranch = Helper.getCharToRadix(charOfInterestInWord);
					Node.asBranch(children[charOfInterestToRadix]).setChild(indexOfWordInIntermediateBranch, new ElementNode(word));
					
					// placing the branch with the long common prefix at its place by the character of interest of the intermediate branch
					char charOfInterestInCommonPrefixBranch = word.charAt( Node.asBranch(intermediateBranch).indexOfCharOfInterest());
					int indexOfCommonPrefixBranchInIntermediateBranch = Helper.getCharToRadix(charOfInterestInCommonPrefixBranch);
					Node.asBranch(children[charOfInterestToRadix]).setChild(indexOfCommonPrefixBranchInIntermediateBranch, commonPrefixBranch);
				}
			}
		}
		*/
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
