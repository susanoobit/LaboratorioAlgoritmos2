package TP3;

public class ShellSort extends Sort {
	// http://sun.aei.polsl.pl/~mciura/publikacje/shellsort.pdf
	private static final int[] gaps = new int[]{701, 301, 132, 57, 23, 10, 4, 1};
	
	ShellSort (int[] vector) {
		super(vector);
	}
	
	ShellSort (int length) {
		super(length);
	}
	
	@Override
	public String getName () {
		return "Shell Sort";
	}
		
	@Override
	public void sort () {
		for (int h : gaps) {
			int i, c, j, n = vector.length;
			for (i = h; i < n; i++) {
				c = vector[i];
				j = i;
				while (j >= h && vector[j - h] > c) {
					vector[j] = vector[j - h];
					j = j - h;
				}
				vector[j] = c;
			}
		}
	}
	
}