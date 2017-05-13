package Aula5;


public class ListaEstatica {
	int[] lista;
	int n;
	
	ListaEstatica () {
		this(6);
	}
	
	ListaEstatica (int n) {
		this.n = n;
		this.lista = new int[n];
	}
	
	public void inserirFim (int elemento) throws ListaException {
		if (n == lista.length)
			throw new ListaException("Lista cheia. Erro na insercao: " + elemento);
		
		lista[n] = elemento;
		n++;
	}
	
	public void inserirInicio (int elemento) throws ListaException {
		if (n == lista.length)
			throw new ListaException("Lista cheia.");
		
		for (int i = n; i > 0; i--)
			lista[i] = lista[i - 1];
		
		lista[0] = elemento;
		n++;
	}
	
	public void inserirPos (int elemento, int pos) throws ListaException {
		if (pos < 0 || pos >= lista.length || n == lista.length)
			throw new ListaException("Posi��o inv�lida ou Lista cheia.");
		
		for (int i = n; i > pos; i--)
			lista[i] = lista[i - 1];
		
		lista[pos] = elemento;
		n++;
	}
	
	public int removerFim () throws ListaException {
		if (n == 0)
			throw new ListaException("Lista vazia.");
		
		n--;
		return lista[n];
	}
	
	public int removerInicio () throws ListaException {
		if (n == 0)
			throw new ListaException("Lista vazia.");
		
		int r = lista[0];
		
		n--;
		for (int i = 0; i < n; i++)
			lista[i] = lista[i + 1];
		
		return r;
	}
	
	public int removerPos (int pos) throws ListaException {
		if (n == 0 || pos < 0 || pos >= lista.length)
			throw new ListaException("Posi��o inv�lida ou lista vazia.");
		
		int r = lista[pos];
		
		n--;
		for (int i = pos; i < n; i++)
			lista[i] = lista[i + 1];
		
		return r;
	}
}
