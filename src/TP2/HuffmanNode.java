package TP2;

public class HuffmanNode {
	char ch;
	long frequency;
	HuffmanNode left, right;
	
	HuffmanNode (char ch, long frequency) {
		this.ch = ch;
		this.frequency = frequency;
	}
	
	HuffmanNode (HuffmanNode node1, HuffmanNode node2) {
		this.frequency = node1.frequency + node2.frequency;
		if (node1.frequency > node2.frequency) {
			this.right = node1;
			this.left = node2;
		}
		else {
			this.right = node2;
			this.left = node1;
		}
	}
	
	boolean isLeaf () {
		return this.left == null && this.right == null;
	}
}
