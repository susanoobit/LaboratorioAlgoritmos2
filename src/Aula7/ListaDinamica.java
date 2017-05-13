package Aula7;

import Aula6.Celula;

public class ListaDinamica {
	public Celula primeiro, ultimo;
	
	public ListaDinamica() {
		primeiro = new Celula();
		ultimo = primeiro;
	}
	
	public void inserirInicio(int x) {
		Celula tmp = new Celula(x);
		tmp.proximo = primeiro.proximo;
		primeiro.proximo = tmp;
		if (primeiro == ultimo)
			ultimo = tmp;
		tmp = null;
	}
	
	public void inserirFim(int x) {
		Celula tmp = new Celula(x);
		//TODO
	}
	
	public int removerFim() throws Exception {
		if (primeiro == ultimo)
			throw new Exception ("Erro!");
		Celula tmp = primeiro;
		while (tmp.proximo != ultimo)
			tmp = tmp.proximo;
		int elemento = ultimo.valor;
		ultimo = tmp;
		tmp = ultimo.proximo = null;
		return elemento;
	}
	
	public int removerInicio() throws Exception {
		if (primeiro == ultimo)
			throw new Exception ("Erro!");
		Celula tmp = primeiro.proximo;
		int elemento = tmp.valor;
		primeiro = tmp.proximo;
		tmp = null;
		return elemento;
	}
	
	public void inserir(int x, int pos) throws Exception {
		int tamanho = tamanho();
		if (pos < 0 || pos > tamanho) throw new Exception ("Erro!");
		else if (pos == 0) inserirInicio(x);
		else if (pos == tamanho) inserirFim(x);
		else {
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.proximo);
			Celula tmp = new Celula(x);
			tmp.proximo = i.proximo;
			i.proximo = tmp;
			tmp = i = null;
		}
	}
	
	public int remover(int pos) throws Exception {
		int elemento, tamanho = tamanho();
		if (primeiro == ultimo || pos < 0 || pos >= tamanho) throw new Exception ("Erro!");
		else if (pos == 0) elemento = removerInicio();
		else if (pos == tamanho - 1) elemento = removerFim();
		else {
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.proximo);
			Celula tmp = i.proximo;
			elemento = tmp.valor;
			i.proximo = tmp.proximo;
			tmp.proximo = null;
			i = tmp = null;
		}
		return elemento;
	}
	
	private int tamanho() {
		int tamanho = 0;
		Celula tmp = primeiro;
		while (tmp.proximo != null) {
			tamanho++;
			tmp = tmp.proximo;
		}
		return tamanho;
	}
}
