package hackathonPrefixTree;

import java.util.Set;

public interface Trieable {
	public void insert(String word);
	public boolean search(String word);	
	public Set<String> suggest(String word);
}
