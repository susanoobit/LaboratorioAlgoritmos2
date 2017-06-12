package TP3;

public class MergeSort extends Sort {
		
	MergeSort (int[] vector) {
		super(vector);
	}
	
	MergeSort (int length) {
		super(length);
	}
	
	@Override
	public String getName () {
		return "Merge Sort";
	}
	
	@Override
	public void sort () {
		sort(0, vector.length - 1);
	}	
	
	private void sort (int startIdx, int endIdx) {
		if (startIdx >= endIdx) return;
		
		int middleIdx = ((endIdx + startIdx) / 2);

		sort(startIdx, middleIdx);
		sort(middleIdx + 1, endIdx);

		merge(startIdx, middleIdx, endIdx);
	}
	
	private void merge (int startIdx, int middleIdx, int endIdx) {
		int auxEndIdx = endIdx - startIdx,
			auxMiddleIdx = auxEndIdx / 2;
		
		int[] auxiliar = new int[auxEndIdx + 1];

		for (int i = startIdx; i <= endIdx; i++) {
			auxiliar[i - startIdx] = vector[i];
		}
		
		int i = 0,
			j = auxMiddleIdx + 1,
			k = startIdx;

		while (i <= auxMiddleIdx && j <= auxEndIdx) {
			if (compare(auxiliar[i], auxiliar[j]) < 0) {
				vector[k] = auxiliar[i];
				i++;
			} else {
				vector[k] = auxiliar[j];
				j++;
			}
			k++;
		}

		while (i <= auxMiddleIdx) {
			vector[k] = auxiliar[i];
			i++;
			k++;
		}

		while (j <= auxEndIdx) {
			vector[k] = auxiliar[j];
			j++;
			k++;
		}
	}
	
}
