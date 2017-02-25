package hackathonPrefixTree;

import java.util.HashSet;
import java.util.Set;

public class Element extends Node {
	private final String word;
	
	public Element(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}
	
	public String toString() {
		return word;
	}

	@Override
	public Set<String> suggest(String word) {
		Set<String> res = new HashSet<>();
		if (this.word == word) {
			res.add(word);
		}
		return res;
	}
	
	@Override
	public boolean search (String word) {
		return this.word.equals(word);
	}
}
