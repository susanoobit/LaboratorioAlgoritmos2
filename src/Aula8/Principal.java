package Aula8;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int counter = in.nextInt();
		BinaryTree bt = new BinaryTree();
		while (counter --> 0) {
			bt.insert(in.nextInt());
		}
		bt.show();
		System.out.println("");
		bt.showPrevious();
		System.out.println("");
		bt.showPosterior();
		System.out.println("");
		System.out.println(bt.getHeight());
		System.out.println("");
		System.out.println(bt.isComplete() ? "Is Complete." : "Is not complete.");
		System.out.println("");
		System.out.println("Quantity of even numbers: " + numOfEven(bt));
	}
	
	public static int numOfEven(BinaryTree bt) {
		if (bt == null || bt.root == null) return 0;
		return numOfEven(bt.root, 0);
	}
	
	public static int numOfEven(Node n, int sum) {
		if (n == null) return 0;
		return sum + (n.item % 2 == 0 ? 1 : 0) + numOfEven(n.left, sum) + numOfEven(n.right, sum); 
	}
}
