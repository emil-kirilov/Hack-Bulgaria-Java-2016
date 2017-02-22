package hackathonPrefixTree;

public class Element extends Node{
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
}
