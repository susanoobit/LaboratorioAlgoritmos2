package Prova1;

public class Deque {
	int Itens[];
	int Primeiro, Ultimo;
	private static int TAM = 5;
	
	Deque() {
		this.Itens = new int[TAM];
		this.Primeiro = this.Ultimo = 0;
	}
	
	public int inserir_inicio(int item) {
		if (!this.cheia()) {
			if (this.Primeiro == this.Ultimo && this.Itens[this.Primeiro] == 0) {
				this.Ultimo--;
				if (this.Ultimo < 0) this.Ultimo = TAM - 1;
			}
			this.Primeiro--;
			if (this.Primeiro < 0) this.Primeiro = TAM - 1;
			this.Itens[this.Primeiro] = item;
			return 1;
		}
		return 0;
	}
	
	public int inserir_final(int item) {
		if (!this.cheia()) {
			if (this.Primeiro == this.Ultimo && this.Itens[this.Primeiro] == 0) {
				this.Primeiro++;
				if (this.Primeiro > 4) this.Primeiro = 0;
			}
			this.Ultimo++;
			if (this.Ultimo > 4)
				this.Ultimo = 0;
			this.Itens[this.Ultimo] = item;
			return 1;
		}
		return 0;
	}
	
	public boolean cheia( ) {
		int i, j;
		for (i = this.Primeiro, j = 0; j < TAM && this.Itens[i] != 0; i++, j++) {
			if (i == 4) i = -1;
		};
		if (j < 5) return false;
		return true;
	}
	
	public void mostrar() {
		System.out.print("[");
		for (int i = this.Primeiro, j = 0; j < TAM; i++, j++) {
			if (i > 4) i = 0;
			System.out.print(" " + this.Itens[i]);
		}
		System.out.print(" ]");
	}
	
}
