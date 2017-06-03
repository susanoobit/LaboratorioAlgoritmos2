package TP2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class huff {
	
	public static void main(String[] args) throws Exception {
		if (args.length < 2 || (!args[0].equals("-c") && !args[0].equals("-d"))) {
			System.out.println("Uso: java huff [-c] [-d] <caminho_arquivo> [<caminho_arquivo_saida>]");
			System.out.println("");
			System.out.println("Modos:");
			System.out.println("\t-c\tCompacta o arquivo especificado.");
			System.out.println("\t-d\tDescompacta o arquivo especificado.");
			System.out.println("");
			System.out.println("Caso o caminho do arquivo de saida nao seja especificado, sera usado o mesmo caminho do arquivo informado.");
			System.exit(0);
		}
		
		String inputFilePath, outputFilePath;
		inputFilePath = args[1];
		if (args.length < 3) outputFilePath = inputFilePath;
		else outputFilePath = args[2];
		
		huff h = new huff();
		
		if (args[0].equals("-c")) {
			if (outputFilePath.indexOf(".huff") == -1) {
				if (outputFilePath.indexOf('.') != -1)
					outputFilePath = outputFilePath.substring(0, outputFilePath.indexOf('.'));
				outputFilePath += ".huff";
			}
			
			h.compressFile(inputFilePath, outputFilePath);
		}
		else if (args[0].equals("-d")) {
			if (outputFilePath.indexOf(".huff") != -1)
				outputFilePath = outputFilePath.substring(0, outputFilePath.indexOf(".huff"));
			h.decompressFile(inputFilePath, outputFilePath);
		}
	}
	
	private class HuffmanNode {
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
	
	// Hashtable containing the characters distribution
	Hashtable<Character, Long> distribution;
	
	// Hashtable containing the codification codes of each character
	Hashtable<Character, String> codification;
	
	// Hashtable to decode
	Hashtable<String, Character> decodification;
	
	// Huffman Tree
	HuffmanNode root;
	
	String text;
	
	public void compressFile (String inputFilePath, String outputFilePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
		text = "";
		int c;
		while ((c = br.read()) != -1)
			text += String.valueOf((char) c);
		
		br.close();
		
		this.loadDistribution();
		this.buildTree();
		this.buildCodification();
		this.writeCompressedFile(outputFilePath);
	}
	
	public void decompressFile (String inputFilePath, String outputFilePath) throws Exception {
		readCompressedFile(inputFilePath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
		bw.write(text);
		bw.flush();
		bw.close();
	}
	
	private void readCompressedFile (String filePath) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		root = readHuffmanTreeAsBinaryData(br);
		buildCodification();
		buildTextFromCompressedFile(br);
		br.close();	
	}
	
	private void buildTextFromCompressedFile (BufferedReader br) throws IOException {
		ArrayList<Character> charCode = new ArrayList<Character>();
		String key;
		text = "";
		int temp = br.read();
		while (temp != -1) {
			charCode.add((char) temp);
			key = charCode
					.stream()
					.map(Object::toString)
                    .collect(Collectors.joining());
			if (decodification.containsKey(key)) {
				text += decodification.get(key).toString();
				charCode.clear();
			}
			temp = br.read();
		}
	}
	
	private void writeCompressedFile (String filePath) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
		bw.write(buildOutputData());
		bw.flush();
		bw.close();
	}
	
	private char[] buildOutputData () {
		ArrayList<Character> finalData = new ArrayList<Character>();
		
		finalData.addAll(buildHuffmanTreeAsBinaryData());
		finalData.addAll(buildBinaryCompressedText());
		
		char[] finalCharArray = new char[finalData.size()];
		int index = finalData.size();
		while (index --> 0)
			finalCharArray[index] = finalData.get(index);
		return finalCharArray;
	}
	
	private ArrayList<Character> buildHuffmanTreeAsBinaryData () {
		ArrayList<Character> huffmanTreeData = new ArrayList<Character>();
		buildHuffmanTreeAsBinaryData(root, huffmanTreeData);
		return huffmanTreeData;
	}
	
	private void buildHuffmanTreeAsBinaryData (HuffmanNode hn, ArrayList<Character> alc) {
		if (hn.isLeaf()) {
			alc.add('1');
			alc.addAll(completeLeadingZerosToLength(Integer.toBinaryString(hn.ch), 16));
			return;
		}
		
		alc.add('0');
		buildHuffmanTreeAsBinaryData(hn.left, alc);
		buildHuffmanTreeAsBinaryData(hn.right, alc);
	}
	
	private HuffmanNode readHuffmanTreeAsBinaryData (BufferedReader br) throws Exception {
		int testEOF = br.read();
		if (testEOF == -1) throw new Exception("Unknown file format. Provide a .huff file to decompress.");
		
		if ((char) testEOF == '1') {
			char[] binaryCode = new char[16];
			br.read(binaryCode);
			return new HuffmanNode((char) Integer.parseInt(String.valueOf(binaryCode), 2), 0);
		}
		return new HuffmanNode(readHuffmanTreeAsBinaryData(br), readHuffmanTreeAsBinaryData(br));
	}
	
	private ArrayList<Character> buildBinaryCompressedText () {
		return
			text
				.chars()
				.mapToObj(i -> codification.get((char) i))
				.reduce(new ArrayList<Character>(), (alc, code) -> {
						alc.addAll(getCharListFromString(code));
						return alc;
					},
					(alc1, alc2) -> {
						alc1.addAll(alc2);
						return alc1;
					});
	}
	
	
	private void loadDistribution () {	
		distribution = new Hashtable<Character, Long>(
			text
				.chars()
				.mapToObj(i -> (char) i)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		);
	}
	
	private void buildTree () {
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
	
	private void buildCodification () {
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
	
	private static ArrayList<Character> getCharListFromString (String s) {
		return s.chars().mapToObj(i -> (char) i).collect(Collectors.toCollection(ArrayList::new));
	}
	
	private static ArrayList<Character> completeLeadingZerosToLength (String s, int length) {
		if (s.length() >= length) return getCharListFromString(s);
		
		ArrayList<Character> returnList = new ArrayList<Character>();
		while (returnList.size() < length - s.length()) returnList.add('0');
		returnList.addAll(getCharListFromString(s));
		return returnList;
	}
	
}
