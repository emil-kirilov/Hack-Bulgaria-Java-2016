package hackathonPrefixOracle;

public class Main {
	public static void main(String[] args) {
		// Creating a new PrefixOracle giving it a dictionary
		PrefixOracle po = new PrefixOracle("/home/emo/Desktop/new_wordlist");
		System.out.println(32453425);
		System.out.println(po.trie.search("apple"));
		//System.out.println(po.trie);
	}
}
