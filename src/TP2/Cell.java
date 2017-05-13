package TP2;

public class Cell<T> {
	public Cell<T> next;
	public T value;
	
	Cell () {
		this.next = null;
	}
	
	Cell (T value) {
		this.next = null;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	@Override
	public Cell<T> clone() {
		Cell<T> rc = new Cell<T>(value);
		rc.next = next.clone();
		return rc;
	}
}
