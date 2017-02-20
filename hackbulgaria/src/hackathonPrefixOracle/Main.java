package hackathonPrefixOracle;

public class Main {
	public static void main(String[] args) {
		PrefixOracle po = new PrefixOracle("/home/emo/Desktop/new_wordlist");
		System.out.println(po.trie);
	}
}
