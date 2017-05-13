package Prova1;

public class Fila {
	char itens[];
	int n;
	
	Fila(char[] elementos) {
		this.n = elementos.length;
		this.itens = new char[this.n];
		for (int i = 0; i < elementos.length; i++)
			this.itens[i] = elementos[i];
	}
	
	Fila(int c) {
		this.itens = new char[c];
		this.n = 0;
	}
	
	Fila() {
		this(5);
	}
	
	// N�o fiz m�todos para adicionar, eliminar, etc., porque n�o foi pedido na quest�o 2.
	
	public void mostrar() {
		for (int i = 0; i < this.n; i++)
			System.out.print(this.itens[i]);
	}
	
	public void inverter() {
		for (int i = 0, j = this.n - 1; i < this.n/2; i++, j--) {
			char tmp = this.itens[i];
			this.itens[i] = this.itens[j];
			this.itens[j] = tmp;
		}
	}
}
