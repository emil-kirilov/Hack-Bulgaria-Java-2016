package hackathonPrefixOracle;

public class Main {
	public static void main(String[] args) {
		// Creating a new PrefixOracle giving it a dictionary
		PrefixOracle po = new PrefixOracle("/home/emo/Desktop/new_wordlist");
		System.out.println(po.trie.search("dog"));
		//System.out.println(po.trie);
	}
}
