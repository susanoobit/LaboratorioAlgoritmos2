package TP2;

public class Test {

	public static void main(String[] args) {
		Huffman huff = new Huffman();
		huff.loadDistribution("Os proletários nada têm a perder, exceto seus grilhões. Têm um mundo a ganhar!");
		huff.buildTree();
		huff.buildCodification();
		//huff.distribution.forEach((c, s) -> System.out.println(c + ": " + s));
		//huff.codification.forEach((c, s) -> System.out.println(c + ": " + s));
		//huff.decodification.forEach((c, s) -> System.out.println(c + ": " + s));
	}

}
