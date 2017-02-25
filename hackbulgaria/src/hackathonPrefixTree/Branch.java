package hackathonPrefixTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Branch extends Node implements Cloneable {
	private static final char epsilon = '#';
	public static int epsCount = 0;
	// common prefix for children
	private String wordSoFar;
	// first char of difference between all children
	private int charOfInterest = Integer.MAX_VALUE;
	//public int childrenCount = 0;
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
					
					// the word in the new branch and putting it there
					char indexOfWordInBranch2 = word.charAt( newCharOfInterest );
					Node.asBranch( children.get( indexInchildren) ).setChild( indexOfWordInBranch2 , new Element(word));
					
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
				Node copyOfBranch = (Branch) this.clone();
				Node.asBranch(copyOfBranch).children = (HashMap<Character, Node>) children.clone();
			
				
				// 2)
				// wiping the current Branch and setting it anew
				charOfInterest = Helper.sameUntil(word, wordSoFar);
				wordSoFar = word.substring(0, charOfInterest);
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

	@Override
	public boolean search(String word) {
		if (word.length() <= charOfInterest) {  //CARE ==
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
	
	@Override
	public Set<String> suggest(String word) {
		
		Set<String> suggestions = new HashSet<>();
		
		// checks whether wordSoFar + charOfInterest is a valid prefix
		char keyInChildren = word.charAt(charOfInterest);

		// 1) new prefix is valid.
		if (children.containsKey(keyInChildren)) {
			// 1.1 mutate COI 
			// delete COI
			String deletedCOI = word.substring(0 , charOfInterest ) + word.substring(charOfInterest + 1);
			if ( search(deletedCOI) ) {
				suggestions.add(deletedCOI);
			}
			
			// insertions
			Set<Character> validKeys = children.keySet();
			String insertedChar;
			for (Character key : validKeys) { // adds #
				insertedChar = word.substring(0, charOfInterest) + key + word.substring(charOfInterest);
				if ( children.get(key).search ( insertedChar ) ) {
					suggestions.add(insertedChar);
				}
			}
			
			//substitute
			String subsCOI;
			for (Character key : validKeys) {
				subsCOI = word.substring(0, charOfInterest) + key + word.substring(charOfInterest + 1);
				if ( search( subsCOI) ) {
					suggestions.add(subsCOI);
				}
			}
			
			
			// 1.2)		get the Node that has key = word.chatAt(charOfInterest)
			Node nodeOfInterest = children.get( word.charAt(charOfInterest) );
			
			// 1.2.1)   Node is Element - if mutations < 2 print Element.word
			if (nodeOfInterest.isElement()) {
				String wordAtElement = Node.asElement(nodeOfInterest).getWord();
				
				if (2 > Helper.mutationsCount( wordAtElement , word)) {
					suggestions.add(wordAtElement);
				}

			// 1.2.2)   Node is Branch: check for prefix mutations (wordSoFar and compression)
			} else {
			
				Branch branchOfInterest = Node.asBranch(nodeOfInterest);
				int compressingMutataions = Helper.mutationsInPrefix(word, branchOfInterest.wordSoFar);
				
				// 1.2.2.1) 0 mutations: branch.suggest(word)
				if (compressingMutataions == 0) {
					suggestions.addAll( branchOfInterest.suggest(word) );
					
				// 1.2.2.2) 1 mutation -> exact matching a.k.a search
				} else if (compressingMutataions == 1) {
					String mutatedWord = branchOfInterest.wordSoFar + word.substring(branchOfInterest.charOfInterest);
					if (branchOfInterest.search( mutatedWord )) {
						suggestions.add(mutatedWord);
					}
				}
			}
		
		
		// 2)     no such prefix, mutation needed   
		} else {
			
			// 2.1)   cover substitutions
			//substitute
			
			Set<Character> validKeys = children.keySet();
			String subsCOI;
			for (Character key : validKeys) {
				subsCOI = word.substring(0, charOfInterest) + key + word.substring(charOfInterest + 1);
				//word = Helper.substite(word, charOfInterest, key);
				if ( search( subsCOI) ) {
					suggestions.add(subsCOI);
				}
			}

			
			// 2.1)   cover deletions
			String deletedCOI = word.substring(0, charOfInterest) + word.substring(charOfInterest + 1);
			if ( search(deletedCOI) ) {
				suggestions.add(deletedCOI);
			}
			
			// 2.1)   cover insertions
			// insertion
			String insertedChar;
			for (Character key : validKeys) {
				insertedChar = word.substring(0, charOfInterest) + key + word.substring(charOfInterest);
				if ( children.get(key).search ( insertedChar ) ) {
					suggestions.add(insertedChar);              
				} 
			}
			
		}

		return suggestions;
	}
}
