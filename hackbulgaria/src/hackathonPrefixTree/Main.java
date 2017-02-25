package hackathonPrefixTree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		// Creating a new PrefixTree giving it a dictionary
		PrefixTree pt = new PrefixTree("/home/emo/Desktop/bigbig_parsed");

		/*
		// Serialize it
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("/home/emo/Desktop/prefix_tree.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(pt);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /home/emo/Desktop/prefix_tree.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
		
		 
		// Deserialize it
		PrefixTree pt  = null;
	      try {
	         FileInputStream fileIn = new FileInputStream("/home/emo/Desktop/prefix_tree.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         pt = (PrefixTree) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c) {
	         System.out.println("PrefixTree class not found");
	         c.printStackTrace();
	         return;
	      }
	      
	      System.out.println(9999);
	   	}
		*/
		
		
		try {	
			// writes to a file all the words and whether each of of them is in the PrefixTree
			BufferedWriter writer = new BufferedWriter( new FileWriter("/home/emo/Desktop/results" ));
			Files.lines(Paths.get("/home/emo/Desktop/bigbig_parsed2"), StandardCharsets.UTF_8).
			forEach((word) ->  {
				try {
					writer.write(pt.search(word) + " --- " + word + "\n");
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
		
		//System.out.println(Branch.epsCount);
		//ArrayList<String> words = new ArrayList<>();
		//words.add("kamenkotse#");
//		
//		String demo;
//		Scanner s = new Scanner(System.in);
//		int counter = 0;
//		while(counter < 10) {
//			System.out.print("Please enter a word:");
//			demo = s.nextLine() + "#";
//			System.out.println( pt.suggest(demo) + "\n");
//			counter++;
//		}
		
		//for (String w : words) {
		//	System.out.println( pt.suggest(w) );
		//}
		//System.out.println(pt);
		
		//string
		//rusty
		//mock
		//carr
		//botle
		
		
		try {	
			BufferedWriter writer2 = new BufferedWriter( new FileWriter("/home/emo/Desktop/scramled_results" ));
			Files.lines(Paths.get("/home/emo/Desktop/suggestion_list"), StandardCharsets.UTF_8).
			forEach((word) ->  {
				String suggestions = "";
				Set<String> temp = pt.suggest(word);
				for ( String str : temp) {
					suggestions += str + "   ";
				}
				
				try {
					writer2.write(suggestions +"\n" );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}     );
			writer2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Done");
		
	}
}

