package hackathonPrefixTree;

import java.util.Set;

public class Trie implements Trieable {
	private Branch root = new Branch("#"); //empty word -> epsilon
	
	@Override
	public void insert(String word) {
		// Root is always a Branch so I can call insert
		root.insert(word);
	}

	@Override
	public boolean search(String word) {
		//appends EOW and call search on the root
		return root.search(word + "#");
	}
	
	@Override
	public Set<String> suggest(String word) {
		//if (search(word)) {
		//	System.out.println("There's such word!");
		//}
		//System.out.println("Alternatives:\n\t");
		return root.suggest(word);
	}
	
	public String toString() {
		//TODO make it faster, stringbuilders...
		helper(root,"","", 0);		
		return "";
	}
	
	// recursively print all nodes 
	private static void helper(Branch node, String ident, String wordSoFar, int oldCoI) {
		char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '#'}; 
		
		for(Character ch : letters) {
			String prefix = wordSoFar;
			Node child = node.getChild(ch);
			if (child != null) {		
				int coi = Node.asBranch(node).getCoI();   // coi = character of interest
				if (child.isElement()) {
					String word = Node.asElement(child).getWord();
					System.out.println( ident + word.substring(0, oldCoI) + "{" + word.charAt(oldCoI) + "}" +
							word.substring(oldCoI + 1, coi) + "[" + word.charAt(coi) + "]" + word.substring(coi + 1)+ "\n");					
				} else {
					prefix += String.valueOf(ch);
					System.out.println(ident + prefix + " {");
					helper((Branch) node.getChild(ch), ident + "    ", prefix, coi);
				}
			}
		}
			System.out.println(ident + "}");
	}
}