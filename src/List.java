import java.util.Comparator;
import java.util.function.Predicate;

public class List<T extends Comparable<T>> {
	public Cell<T> first, last;
	
	public List() {
		first = new Cell<T>();
		last = first;
	}
	
	@Override
	public String toString() {
		String r = "[";
		for (Cell<T> c = first.next; c != null; r += c.toString() + ", ", c = c.next);
		return r.substring(0, r.length() - 2) + "]";
	}
	
	@Override
	public List<T> clone() {
		List<T> rl = new List<T>();
		for (Cell<T> c = first.next; c != null; rl.push(c.value), c = c.next);
		return rl;
	}
	
	public void show() {
		System.out.print("[");
		for (Cell<T> c = first.next; ; c = c.next) {
			if (c.next == null) {
				System.out.print(c.toString());
				break;
			}
			System.out.print(c.toString() + ", ");
		}
		System.out.print("]");
	}
	
	public void unshift(T x) {
		Cell<T> tmp = new Cell<T>(x);
		tmp.next = first.next;
		first.next = tmp;
		if (first == last)
			last = tmp;
		tmp = null;
	}
	
	public void push(T x) {
		last.next = new Cell<T>(x);
		last = last.next;
	}
	
	public List<T> pushThen(T x) {
		last.next = new Cell<T>(x);
		last = last.next;
		return this;
	}
	
	public void pushArray(T[] x) {
		for (int i = 0; i < x.length; i++)
			this.push(x[i]);
	}
	
	public T pop() throws Exception {
		if (first == last)
			throw new Exception ("Erro!");
		Cell<T> tmp = first;
		while (tmp.next != last)
			tmp = tmp.next;
		T element = last.value;
		last = tmp;
		tmp = last.next = null;
		return element;
	}
	
	public T shift() throws Exception {
		if (first == last)
			throw new Exception ("Erro!");
		Cell<T> tmp = first.next;
		T element = tmp.value;
		first = tmp.next;
		tmp = null;
		return element;
	}
	
	public void insertAt(T x, int pos) throws Exception {
		int length = getLength();
		if (pos < 0 || pos > length) throw new Exception ("Erro!");
		else if (pos == 0) unshift(x);
		else if (pos == length) push(x);
		else {
			Cell<T> i = first;
			for (int j = 0; j < pos; j++, i = i.next);
			Cell<T> tmp = new Cell<T>(x);
			tmp.next = i.next;
			i.next = tmp;
			tmp = i = null;
		}
	}
	
	public T removeAt(int pos) throws Exception {
		T element;
		int length = getLength();
		if (first == last || pos < 0 || pos >= length) throw new Exception ("Erro!");
		else if (pos == 0) element = shift();
		else if (pos == length - 1) element = pop();
		else {
			Cell<T> i = first;
			for (int j = 0; j < pos; j++, i = i.next);
			Cell<T> tmp = i.next;
			element = tmp.value;
			i.next = tmp.next;
			tmp.next = null;
			i = tmp = null;
		}
		return element;
	}
	
	public int indexOf(T x) {
		int pos = 0;
		for (Cell<T> c = first.next; c != null; c = c.next, pos++)
			if (c.value.equals(x)) return pos;
		return -1;
	}
	
	public T elementAt(int pos) throws Exception {
		return cellAt(pos).value;
	}
	
	public void sort() throws Exception {
		if (first == last) throw new Exception("Lista vazia.");
		quickSort(0, getLength() - 1);
	}
	
	public void sortBy(Comparator<? super T> comp) throws Exception {
		if (first == last) throw new Exception("Lista vazia.");
		quickSort(0, getLength() - 1, comp);
	}
	
	public int getLength() {
		int length = 0;
		Cell<T> tmp = first;
		while (tmp.next != null) {
			length++;
			tmp = tmp.next;
		}
		tmp = null;
		return length;
	}
	
	private Cell<T> cellAt(int pos) throws Exception {
		Cell<T> i;
		int length = getLength();
		if (first == last || pos < 0 || pos >= length) throw new Exception ("Erro!");
		else if (pos == 0) i = first.next;
		else if (pos == length - 1) i = last;
		else {
			i = first.next;
			for (int j = 0; j < pos; j++, i = i.next);
		}
		return i;
	}
	
	private void quickSort(int startPos, int endPos) throws Exception {
        if(startPos < endPos) {
            int pivot = partition(startPos, endPos);
            quickSort(startPos, pivot - 1);
            quickSort(pivot + 1, endPos);
        }
    }
	
	private int partition(int startPos, int endPos) throws Exception {
        T pivo = elementAt(endPos);
        int i = startPos - 1;
        
        for (int j = startPos ; j < endPos ; j++){
            if(elementAt(j).compareTo(pivo) < 1) {
            	i++;
                swap(i, j);
            }
        }
        
        i++;
        swap(i, endPos);
        return i;
    }
    
    private void quickSort(int startPos, int endPos, Comparator<? super T> comp) throws Exception {
        if(startPos < endPos) {
            int pivot = partition(startPos, endPos, comp);
            quickSort(startPos, pivot - 1, comp);
            quickSort(pivot + 1, endPos, comp);
        }
    }
 
    private int partition(int startPos, int endPos, Comparator<? super T> comp) throws Exception {
        T pivo = elementAt(endPos);
        int i = startPos - 1;
        
        for (int j = startPos ; j < endPos ; j++){
            if(comp.compare(elementAt(j), pivo) < 1) {
            	i++;
                swap(i, j);
            }
        }
        
        i++;
        swap(i, endPos);
        return i;
    }
    
    private void swap (int pos1, int pos2) throws Exception {
    	if (pos1 == pos2) return;
    	
    	Cell<T> beforeEl1 = pos1 == 0 ? first : cellAt(pos1 - 1);
        Cell<T> el1 = beforeEl1.next;
        Cell<T> afterEl1 = el1.next;
        
        Cell<T> beforeEl2 = pos2 == 0 ? first : cellAt(pos2 - 1);
        Cell<T> el2 = beforeEl2.next;
        Cell<T> afterEl2 = el2.next;
        
    	if (pos2 - pos1 == 1) {
    		beforeEl1.next = el2;
    		el1.next = el2.next;
    		el2.next = el1;
    	}
    	else if (pos1 - pos2 == 1) {
    		beforeEl2.next = el1;
    		el2.next = el1.next;
    		el1.next = el2;
    	}
    	else {
    		beforeEl1.next = el2;
            el2.next = afterEl1;
            
            beforeEl2.next = el1;
            el1.next = afterEl2;
    	}     
    	
    	if (pos2 == getLength() - 1) last = el1; 
        
        beforeEl1 = el1 = afterEl1 = beforeEl2 = el2 = afterEl2 = null;
    }
    
	public List<T> filter(Predicate<? super T> tester) throws Exception {
		List<T> rl = new List<T>();
		for (Cell<T> c = first.next; c != null; c = c.next) {
			if (tester.test(c.value))
				rl.push(c.value);
		}
		return rl;
	}
	
	public T[] toArray(T[] array) throws Exception {
		if (array.length != getLength())
			throw new Exception ("O array passado deve ter tamanho " + getLength() + ".");
		else {
			for (int i = 0; i < array.length; i++) {
				array[i] = elementAt(i);
			}
			return array;
		}
	}
	
	public List<T> merge(List<T> l) throws Exception {
		for (int i = 0; i < l.getLength(); i++)
			push(l.elementAt(i));
		return this;
	}
}
