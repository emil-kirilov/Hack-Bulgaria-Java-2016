package hackathonPrefixTree;

import java.util.HashMap;

public class Branch extends Node implements Cloneable {
	private static final char epsilon = '#';
	public static int epsCount = 0;
	// common prefix for children
	private String wordSoFar;
	// first char of difference between all children
	private int charOfInterest = Integer.MAX_VALUE;
	public int childrenCount = 0;
	// stands for the letters of the alphabet - starting letters of the children and '#' (End Of Word)
	private HashMap<Character, Node> children = new HashMap<>(27);
	
	
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
	
	public void setChild(char key, Node value) {
		if (key == '#') {
			children.put(epsilon, value);
			epsCount++;
		} else {
			children.put(key, value);
		}
	}
	
	public int getCoI() {
		return charOfInterest;
	}
	
	public Node getChild(char key) {
		return children.get(key);
	}

	@SuppressWarnings("unchecked")
	public void insert(String word) {
		// 0) this node has to determine what to do with the word:
		// 0.1) if wordSoFar is prefix of the word and the word is long enough to provide the charOfInterest
		// 0.2) else we have to create a new branch that has a shorter wordSoFar, so we guarantee the same prefix of wordSoFar
		
		// 0.1)
		if (word.length() > charOfInterest && Helper.samePrefix(word, wordSoFar, charOfInterest)) {
			childrenCount++;
			// word has come to the right place
			// 1) 		we need to establish the word's index in children
			// 2) 		check whether that index is free/ occupied
			// 2.1) 	free -> new Element(word)
			// 2.2) 	there's a node at the index we need to insert the word into
			// 2.2.1) 	Element -> create a Branch, place Element and word in it
			// 2.2.2)   Branch -> insert word in it

		
			// 1)
			char indexInchildren = word.charAt(charOfInterest);
		
			// 2)
			Node nodeAtIndex2 = children.get(indexInchildren);
			
			//2.1)
			if(nodeAtIndex2 == null) { //  nodeAtIndex == null
				children.put(indexInchildren, new Element(word));
			// 2.2)
			} else {
				// 2.2.1)
				if (nodeAtIndex2.isElement()) {  // nodeAtIndex2.isElement()
					Element elementAtIndex2 = Node.asElement(nodeAtIndex2);
					
					//creating the new branch
					int newCharOfInterest = Helper.sameUntil(word, elementAtIndex2.getWord());
					String newWordSoFar = word.substring(0, newCharOfInterest);
					children.put(indexInchildren, new Branch(newWordSoFar, newCharOfInterest));
					
					// Finding the place of:
					// the element in the new branch and putting it there
					char indexOfElementInBranch2 = elementAtIndex2.getWord().charAt( newCharOfInterest );
					Node.asBranch( children.get(indexInchildren) ).setChild(indexOfElementInBranch2, elementAtIndex2);
					Node.asBranch( children.get(indexInchildren) ).childrenCount++;
					
					// the word in the new branch and putting it there
					char indexOfWordInBranch2 = word.charAt( newCharOfInterest );
					Node.asBranch( children.get( indexInchildren) ).setChild( indexOfWordInBranch2 , new Element(word));
					Node.asBranch( children.get(indexInchildren) ).childrenCount++;
					
				// 2.2.2)
				} else {
					Node.asBranch(nodeAtIndex2).insert(word);
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
				childrenCount++;
				Node copyOfBranch = (Branch) this.clone();
				Node.asBranch(copyOfBranch).children = (HashMap<Character, Node>) children.clone();
			
				
				// 2)
				// wiping the current Branch and setting it anew
				charOfInterest = Helper.sameUntil(word, wordSoFar);
				wordSoFar = word.substring(0, charOfInterest);
				childrenCount = 0;
	//childrenCount -= children.size();
				children = new HashMap<Character, Node>(27);
				
				// 3)
				char indexOfWordInchildren = word.charAt(charOfInterest);
				setChild( indexOfWordInchildren ,new Element(word));
				
				// 4)
				char indexOfCopyBranchInchildren = Node.asBranch(copyOfBranch).wordSoFar.charAt(charOfInterest);
				setChild( indexOfCopyBranchInchildren , copyOfBranch);
			
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
		char radix = getCharOfInterest(word);
		Node node = children.get(radix);
		if (node == null) {
			return false;
		} else if (node.isElement()) {
			return Node.asElement(node).getWord().equals(word);
		} else {
			return Node.asBranch(node).search(word);
		}
	}
	
	public String correct(String word) {
		// apble
		
		return null;
	}
}
