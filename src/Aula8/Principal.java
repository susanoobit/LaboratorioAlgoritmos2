package Aula8;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int counter = 10;
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
	}
}
