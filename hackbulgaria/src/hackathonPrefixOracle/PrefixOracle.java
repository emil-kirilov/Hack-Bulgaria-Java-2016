package hackathonPrefixOracle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PrefixOracle {
	Trie trie;
	
	public PrefixOracle(String filePath) {   //"/home/emo/Desktop/test" 
		trie = new Trie();
		
		try {	
			Files.lines(Paths.get(filePath), StandardCharsets.UTF_8).forEach((word) -> trie.insert(word));

		} catch (IOException e) {
			System.out.println("tapak");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return trie.toString();
	}
}
