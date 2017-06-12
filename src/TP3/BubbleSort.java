package TP3;

public class BubbleSort extends Sort {

	public BubbleSort (int[] vector) {
		super(vector);
	}

	public BubbleSort (int length) {
		super(length);
	}
	
	@Override
	public String getName () {
		return "Bubble Sort";
	}

	@Override
	public void sort() {
		int i, aux, n = vector.length;
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (i = 1; i < n; i++) {
				if (vector[i - 1] > vector[i]) {
					aux = vector[i - 1];
					vector[i - 1] = vector[i];
					vector[i] = aux;
					swapped = true;
				}
			}
		}

	}

}
