package TP3;

import java.io.FileWriter;
import java.io.IOException;

public class Principal {

	public static void main (String[] args) throws IOException {
		IVectorSupplier[] vectorGenerators = new IVectorSupplier[]{Sort::createRandomVector, Sort::createOrderedVector, Sort::createReversedOrderedVector};
		String[] vectorTypes = new String[]{"vetor-desordenado.tsv", "vetor-ordenado.tsv", "vetor-ordenado-inversamente.tsv"};
		int[] vectorLengths = new int[]{100, 1000, 10000, 100000, 1000000};
		
		for (int i = 0; i < vectorTypes.length; i++) {
			FileWriter output = new FileWriter(vectorTypes[i]);
					
			output.write("Tamanho array");
			output.write("\tInsertionSort");
			output.write("\tSelectionSort");
			output.write("\tMergeSort");
			output.write("\tShellSort");
			output.write("\tBubbleSort");
			output.write("\tHeapSort");
			output.write("\tQuickSort");
			
			output.write("\n");
			output.flush();
			
			for (int vectorLength : vectorLengths) {
				output.write(String.valueOf(vectorLength));
				output.flush();
				
				int[] vector = vectorGenerators[i].apply(vectorLength);
				
				InsertionSort is = new InsertionSort(vector.clone());
				SelectionSort ss = new SelectionSort(vector.clone());
				MergeSort ms = new MergeSort(vector.clone());
				ShellSort shs = new ShellSort(vector.clone());
				BubbleSort bs = new BubbleSort(vector.clone());
				HeapSort hs = new HeapSort(vector.clone());
				QuickSort qs = new QuickSort(vector.clone());
				
				output.write("\t" + is.timedSort());
				output.flush();
				output.write("\t" + ss.timedSort());
				output.flush();
				output.write("\t" + ms.timedSort());
				output.flush();
				output.write("\t" + shs.timedSort());
				output.flush();
				output.write("\t" + bs.timedSort());
				output.flush();
				output.write("\t" + hs.timedSort());
				output.flush();
				output.write("\t" + qs.timedSort());
				output.write("\n");
				output.flush();
			}
			
			output.close();
		}
		
	}
}
