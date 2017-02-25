package hackathonPrefixTree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class PrefixTree implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Trie trie;
	
	public PrefixTree(String filePath) { 
		trie = new Trie();
		
		try {	
			Files.lines(Paths.get(filePath), StandardCharsets.UTF_8).forEach((word) -> trie.insert(word));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean search(String word) {
		return trie.search(word);
	}
	
	public Set<String> suggest(String word) {
		return trie.suggest(word);
	}
	
	public String toString() {
		return trie.toString();
	}
}
