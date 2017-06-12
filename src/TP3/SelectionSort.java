package TP3;

public class SelectionSort extends Sort {
	
	SelectionSort (int[] vector) {
		super(vector);
	}
	
	SelectionSort (int length) {
		super(length);
	}
	
	@Override
	public String getName () {
		return "Selection Sort";
	}

	@Override
	public void sort () {
		int i, j, min, aux, tam = vector.length;
		for (i = 0; i < (tam - 1); i++) {
			min = i;
			for (j = (i + 1); j < tam; j++) {
				if(vector[j] < vector[min]) min = j;
			}
			if (i != min) {
				aux = vector[i];
				vector[i] = vector[min];
				vector[min] = aux;
			}
		}
	}

}
