package TP3;

public class InsertionSort extends Sort {
	
	InsertionSort (int[] vector) {
		super(vector);
	}
	
	InsertionSort (int length) {
		super(length);
	}
	
	@Override
	public String getName () {
		return "Insertion Sort";
	}

	@Override
	public void sort () {
		int i, j, key, tam = vector.length;
		for (i = 1; i < tam; i++){
			key = vector[i];
			j = i;
			
			while ((j > 0) && (vector[j - 1] > key)){
				vector[j] = vector[j - 1];
				j -= 1;
			}
			vector[j] = key;
		}
	}
	
}
