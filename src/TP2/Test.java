package TP2;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		Huffman huff = new Huffman();
		//writtingTest(huff);
		readingTest(huff);
	}
	
	public static void writtingTest (Huffman huff) throws IOException {
		huff.text = "Os proletários nada têm a perder, exceto seus grilhões. Têm um mundo a ganhar!";
		huff.fileName = "teste";
		huff.loadDistribution();
		huff.buildTree();
		huff.buildCodification();
		huff.writeFile();
		huff.distribution.forEach((c, s) -> System.out.println(c + ": " + s));
		huff.codification.forEach((c, s) -> System.out.println(c + ": " + s));
		huff.decodification.forEach((c, s) -> System.out.println(c + ": " + s));
	}
	
	public static void readingTest (Huffman huff) throws IOException {
		huff.fileName = "teste";
		huff.readFile();
		huff.codification.forEach((c, s) -> System.out.println(c + ": " + s));
		huff.decodification.forEach((c, s) -> System.out.println(c + ": " + s));
	}

}
