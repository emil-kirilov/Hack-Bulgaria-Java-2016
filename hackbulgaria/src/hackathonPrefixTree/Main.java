package hackathonPrefixTree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		// Creating a new PrefixTree giving it a dictionary
		PrefixTree po = new PrefixTree("/home/emo/Desktop/bigbig_parsed");
		
		try {	
			// writes to a file all the words and whether each of of them is in the PrefixTree
			BufferedWriter writer = new BufferedWriter( new FileWriter("/home/emo/Desktop/results" ));
			Files.lines(Paths.get("/home/emo/Desktop/bigbig_parsed2"), StandardCharsets.UTF_8).
			forEach((word) ->  {
				try {
					writer.write(po.trie.search(word) + " --- " + word + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}     );
			writer.close( );

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(po.trie);
	}
}

