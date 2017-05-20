package Prova2;

import java.util.ArrayList;

public class BinaryTree {
	Node root;
	
	public void insert (int item) {
		root = insert(root, item);
	}
	
	private Node insert (Node n, int item) {
		if (n == null) return new Node(item);
		else if (item < n.item) n.left = insert(n.left, item);
		else if (item > n.item) n.right = insert(n.right, item);
		return n;
	}
	
	public void printLeaves () {
		printLeaves(root);
	}
	
	private void printLeaves (Node n) {
		if (n == null) return;
		printLeaves(n.left);
		printLeaves(n.right);
		if (isLeaf(n))
			System.out.print(n.item + " ");
	}
	
	private boolean isLeaf (Node n) {
		return n.left == null && n.right == null;
	}
	
	public boolean isComplete () {
		return isComplete(root);
	}
	
	private boolean isComplete (Node n) {
		if (n == null) return false;
		// both are null
		if (n.left == n.right) return true;
		// one is null and the other isn't
		if ((n.left == null && n.right != null) || (n.right == null && n.left != null)) return false;
		
		// check using binary search is less expensive then compute the height
		if (!isComplete(n.left)) return false;
		if (!isComplete(n.right)) return false;
		
		return getHeight(n.left, -1) == getHeight(n.right, -1);

	}
	
	private int getHeight (Node n, int actualHeight) {
		if (n != null) {
			int leftHeight = getHeight(n.left, actualHeight + 1);
			int rightHeight = getHeight(n.right, actualHeight + 1);
			actualHeight = leftHeight > rightHeight ? leftHeight : rightHeight;
		}
		
		return actualHeight;
	}
	
	public void mapItems(ArrayList<Integer> list) {
		mapItemsAsc(root, list);
	}
	
	private void mapItemsAsc(Node n, ArrayList<Integer> list) {
		if (n == null) return;
		mapItemsAsc(n.left, list);
		list.add(n.item);
		mapItemsAsc(n.right, list);
	}
}
