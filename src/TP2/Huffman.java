package TP2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Huffman {
	
	// Hashtable containing the characters distribution
	Hashtable<Character, Long> distribution;
	
	// Hashtable containing the codification codes of each character
	Hashtable<Character, String> codification;
	
	// Hashtable to decode
	Hashtable<String, Character> decodification;
	
	// Huffman Tree
	HuffmanNode root;
	
	String fileName;
	String text;
	
	public void readFile () throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		root = readHuffmanTreeAsBinaryData(fis);
		buildCodification();
		ArrayList<Character> charCode = new ArrayList<Character>();
		
		int temp = fis.read();
		String key;
		while (temp != -1) {
			charCode.add((char) temp);
			key = charCode
					.stream()
					.map(Object::toString)
                    .collect(Collectors.joining());
			if (decodification.containsKey(key)) {
				text.concat(decodification.get(key).toString());
				charCode.clear();
			}
			temp = fis.read();
		}
	}
	
	public void writeFile () throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(buildOutputData());
		fos.flush();
		fos.close();
	}
	
	public byte[] buildOutputData () {
		ArrayList<Byte> finalData = new ArrayList<Byte>();
		
		finalData.addAll(buildHuffmanTreeAsBinaryData());
		finalData.addAll(buildBinaryCompressedText());
		
		byte[] finalByteArray = new byte[finalData.size()];
		int index = finalData.size();
		while (index --> 0)
			finalByteArray[index] = finalData.get(index);
		return finalByteArray;
	}
	
	private ArrayList<Byte> buildHuffmanTreeAsBinaryData () {
		ArrayList<Byte> huffmanTreeData = new ArrayList<Byte>();
		buildHuffmanTreeAsBinaryData(root, huffmanTreeData);
		return huffmanTreeData;
	}
	
	private void buildHuffmanTreeAsBinaryData (HuffmanNode hn, ArrayList<Byte> alb) {
		if (hn.isLeaf()) {
			alb.add(Byte.valueOf((byte) 1));
			alb.add(Byte.valueOf((byte) hn.ch));
			return;
		}
		
		alb.add(Byte.valueOf((byte) 0));
		buildHuffmanTreeAsBinaryData(hn.left, alb);
		buildHuffmanTreeAsBinaryData(hn.right, alb);
	}
	
	public HuffmanNode readHuffmanTreeAsBinaryData (FileInputStream fis) throws IOException {
		if (fis.read() == 0) {
			char c = (char) fis.read();
			return new HuffmanNode(c, 0);
		}
		return new HuffmanNode(readHuffmanTreeAsBinaryData(fis), readHuffmanTreeAsBinaryData(fis));
	}
	
	private ArrayList<Byte> buildBinaryCompressedText () {
		return text
			.chars()
			.mapToObj(i -> Byte.valueOf((byte) i))
			.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public void loadDistribution () {	
		distribution = new Hashtable<Character, Long>(
			text
				.chars()
				.mapToObj(i -> (char) i)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		);
	}
	
	public void buildTree () {
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
	
	public void buildCodification () {
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
	
}
