package hackathonPrefixTree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PrefixTree {
	Trie trie;
	
	public PrefixTree(String filePath) { 
		trie = new Trie();
		
		try {	
			Files.lines(Paths.get(filePath), StandardCharsets.UTF_8).forEach((word) -> trie.insert(word));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return trie.toString();
	}
}
