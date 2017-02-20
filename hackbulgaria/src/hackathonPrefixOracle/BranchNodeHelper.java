package hackathonPrefixOracle;

import java.util.HashMap;

public class BranchNodeHelper {
	@SuppressWarnings("serial")
	private static final HashMap<Character, Integer> radix = new HashMap<Character, Integer>(27) {{
		put('a',0);
		put('b',1);
		put('c',2);
		put('d',3);
		put('e',4);
		put('f',5);
		put('g',6);
		put('h',7);
		put('i',8);
		put('j',9);
		put('k',10);
		put('l',11);
		put('m',12);
		put('n',13);
		put('o',14);
		put('p',15);
		put('q',16);
		put('r',17);
		put('s',18);
		put('t',19);
		put('u',20);
		put('v',21);
		put('w',22);
		put('x',23);
		put('y',24);
		put('z',25);
		put('#',26);
	}};
	
	@SuppressWarnings("serial")
	private static final HashMap<Integer, Character> abc = new HashMap<Integer, Character>(27) {{
		put(0, 'a');
		put(1, 'b');
		put(2, 'c');
		put(3, 'd');
		put(4, 'e');
		put(5, 'f');
		put(6, 'g');
		put(7, 'h');
		put(8, 'i');
		put(9, 'j');
		put(10, 'k');
		put(11, 'l');
		put(12, 'm');
		put(13, 'n');
		put(14, 'o');
		put(15, 'p');
		put(16, 'q');
		put(17, 'r');
		put(18, 's');
		put(19, 't');
		put(20, 'u');
		put(21, 'v');
		put(22, 'w');
		put(23, 'x');
		put(24, 'y');
		put(25, 'z');
		put(26, '#');
	}};
	
	public static int getCharToRadix(char charAt) {
		return radix.get(charAt);
	}

	public static int sameUntil(String a, String b) {
		int lengthCommonSubstring = 0;
		
		for(int i = 0; i < Math.min( a.length() ,b.length() ); i++ ) {
			if (a.charAt(i) == b.charAt(i)) {
				lengthCommonSubstring++;
			} else {
				break;
			}
		}
		
		// as we started from 0 and incremented on the first match 
		//the result will be the index of the first chars which are not the same
		return lengthCommonSubstring; 
	}
	
	public static Character getRadixToChar(int radix) {
		return abc.get(radix);
	}
}
