package TP3;

public class HeapSort extends Sort {

	public HeapSort (int[] vector) {
		super(vector);
	}

	public HeapSort (int length) {
		super(length);
	}
	
	@Override
	public String getName () {
		return "Heap Sort";
	}

	@Override
	public void sort () {
		heapify(vector.length);
		
		int aux, endIdx = vector.length - 1;
		while (endIdx > 0) {
			aux = vector[endIdx];
			vector[endIdx] = vector[0];
			vector[0] = aux;
			
			endIdx--;
			siftDown(0, endIdx);
		}
	}
	
	private void heapify (int idx) {
		int start = parent(idx - 1);
		
		while (start >= 0) {
			siftDown(start, idx - 1);
			start--;
		}
	}
	
	private void siftDown (int startIdx, int endIdx) {
		int child, swap, aux, root = startIdx;
		
		while (leftChild(root) <= endIdx) {
			child = leftChild(root);
			swap = root;
			
			if (vector[swap] < vector[child]) swap = child;
			if (child + 1 <= endIdx && vector[swap] < vector[child + 1]) swap = child + 1;
			if (swap == root) return;
			else {
				aux = vector[root];
				vector[root] = vector[swap];
				vector[swap] = aux;
				root = swap;
			}
		}
	}
	
	private static int parent (int idx) {
		return (int) Math.floor((idx - 1) / 2);
	}
	
	private static int leftChild (int idx) {
		return 2 * idx + 1;
	}
	
}
