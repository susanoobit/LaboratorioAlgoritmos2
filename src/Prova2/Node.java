package Prova2;

public class Node {
	int item;
	Node left;
	Node right;
	
	Node (int item) {
		this.item = item;
	}
	
	Node (int item, Node left, Node right) {
		this.item = item;
		this.left = left;
		this.right = right;
	}
}
