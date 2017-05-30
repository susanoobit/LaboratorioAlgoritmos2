package Aula8;

public class Node {
	
	int item;
	Node left;
	Node right;
	
	Node (int item, Node left, Node right) {
		this.item = item;
		this.right = right;
		this.left = left;
	}
	
	Node () {
		this (0, null, null);
	}
	
}