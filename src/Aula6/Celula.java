package Aula6;

public class Celula {
	public Celula proximo;
	public int valor;
	
	public Celula () {
		this.proximo = null;
	}
	
	public Celula (int valor) {
		this.proximo = null;
		this.valor = valor;
	}
}
