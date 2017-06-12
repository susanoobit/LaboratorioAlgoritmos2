package TP3;

public class QuickSort extends Sort {

	public QuickSort (int[] vector) {
		super(vector);
	}

	public QuickSort (int length) {
		super(length);
	}
	
	@Override
	public String getName () {
		return "Quick Sort";
	}

	@Override
	public void sort () {
		sort(0, vector.length - 1);
	}
	
	private void sort (int startIdx, int endIdx) {
		if (startIdx >= endIdx) return;
		int p = partition(startIdx, endIdx);
		sort(startIdx, p);
		sort(p + 1, endIdx);
	}
	
	private int partition (int startIdx, int endIdx) {
		int pivot = vector[startIdx],
			i = startIdx - 1,
			j = endIdx + 1,
			aux;
		
		while (true) {
			while (vector[++i] < pivot);
			while (vector[--j] > pivot);
			if (i >= j) return j;
			
			aux = vector[i];
			vector[i] = vector[j];
			vector[j] = aux;
		}
	}
	
}
