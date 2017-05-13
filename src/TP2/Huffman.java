package TP2;

import java.util.Hashtable;

public class Huffman {
	
	// hashtable to compute the caracters distribution
	Hashtable<Character, Integer> distribution = new Hashtable<Character, Integer>();
	
	public void loadDistribution (String text) {
		char[] charArray = text.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (distribution.containsKey(charArray[i]))
				distribution.replace(charArray[i], distribution.get(charArray[i]) + 1);
			else
				distribution.put(charArray[i], 1);
		}
	}
	
	public void buildTree () {
		distribution.entrySet().stream().sorted();
	}
	
}
