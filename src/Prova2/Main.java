package Prova2;

import java.util.ArrayList;

public class Main {
	public static void main (String args[]) {
		//questao1();
		//questao2();
		questao3();
	}
	
	public static void questao1 () {
		BinaryTree bt = new BinaryTree();
		bt.insert(100);
		bt.insert(80);
		bt.insert(25);
		bt.insert(72);
		bt.insert(23);
		bt.insert(42);
		bt.insert(5);
		System.out.println("As folhas da arvore formada pela sequencia dos numeros 100, 80, 25, 72, 23, 42 e 5 e':");
		bt.printLeaves();
	}
	
	public static void questao2 () {
		BinaryTree bt = new BinaryTree();
		bt.insert(100);
		bt.insert(80);
		bt.insert(25);
		bt.insert(72);
		bt.insert(23);
		bt.insert(42);
		bt.insert(5);
		System.out.println("A arvore formada pela sequencia dos numeros 100, 80, 25, 72, 23, 42 e 5 e': " + (bt.isComplete() ? "completa" : "incompleta") + ".");
		
		BinaryTree bt2 = new BinaryTree();
		bt2.insert(80);
		bt2.insert(25);
		bt2.insert(23);
		bt2.insert(5);
		bt2.insert(24);
		bt2.insert(72);
		bt2.insert(42);
		bt2.insert(73);
		bt2.insert(88);
		bt2.insert(86);
		bt2.insert(85);
		bt2.insert(87);
		bt2.insert(90);
		bt2.insert(89);
		bt2.insert(91);
		System.out.println("A arvore formada pela sequencia dos numeros 80, 25, 23, 5, 24, 72, 42, 73, 88, 86, 85, 87, 90, 89 e 91 e': " + (bt2.isComplete() ? "completa" : "incompleta") + ".");
	}
	
	private static void questao3() {
		BinaryTree bt = new BinaryTree();
		bt.insert(80);
		bt.insert(25);
		bt.insert(23);
		bt.insert(5);
		bt.insert(24);
		bt.insert(72);
		bt.insert(42);
		bt.insert(73);
		bt.insert(88);
		bt.insert(86);
		bt.insert(85);
		bt.insert(87);
		bt.insert(90);
		bt.insert(89);
		bt.insert(91);
		System.out.println("A arvore formada pela sequencia dos numeros 80, 25, 23, 5, 24, 72, 42, 73, 88, 86, 85, 87, 90, 89 e 91 foi construida.");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		bt.mapItems(list);
		
		System.out.println("A list de elementos em ordem crescente e':");
		list.forEach(i -> System.out.print(i.intValue() + " "));
	}
}
