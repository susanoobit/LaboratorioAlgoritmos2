package TP2;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Huffman {
	
	// Hashtable containing the characters distribution
	Hashtable<Character, Long> distribution;
	
	// Hashtable containing the codification codes of each character
	Hashtable<Character, String> codification;
	
	// Hashtable to decode
	Hashtable<String, Character> decodification;
	
	// Huffman Tree
	HuffmanNode root;
	
	public void loadDistribution (String text) {	
		distribution = new Hashtable<Character, Long>(
			text
				.chars()
				.mapToObj(i -> (char) i)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		);
	}
	
	public void buildTree () {
		ArrayList<HuffmanNode> nodes = distribution
			.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue())
			.map(e -> new HuffmanNode(e.getKey(), e.getValue()))
			.collect(Collectors.toCollection(ArrayList::new));
		
		while (nodes.size() > 1) {
			HuffmanNode mergedNode = new HuffmanNode(nodes.remove(0), nodes.remove(0));
			
			if (nodes.size() == 0) {
				nodes.add(mergedNode);
				break;
			}
			
			int counter = -1;
			while (++counter < nodes.size()) // to avoid unnecessary sorting
				if (nodes.get(counter).frequency >= mergedNode.frequency) {
					nodes.add(counter > 0 ? counter - 1 : counter, mergedNode);
					break;
				}
				else if (counter == nodes.size() - 1) {
					nodes.add(mergedNode);
					break;
				}
		}
		
		root = nodes.get(0);
	}
	
	public void buildCodification () {
		codification = new Hashtable<Character, String>();
		decodification = new Hashtable<String, Character>();
		buildCodification(root, "");
	}
	
	private void buildCodification (HuffmanNode node, String actualCode) {
		if (node.isLeaf()) {
			codification.put(node.ch, actualCode);
			decodification.put(actualCode, node.ch);
			return;
		}
		
		buildCodification(node.left, actualCode + "0");
		buildCodification(node.right, actualCode + "1");
	}
	
}
