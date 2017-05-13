package Aula6;

public class Fila {
	public Celula primeiro, ultimo;
	Fila () {
		primeiro = new Celula();
		ultimo = primeiro;
	}
	
	public void inserir(int x) {
		ultimo.proximo = new Celula(x);
		ultimo = ultimo.proximo;
	}
	
	public int remover() throws Exception {
		if (primeiro == ultimo)
			throw new Exception ("Erro!");
		Celula tmp = primeiro;
		primeiro = primeiro.proximo;
		int valor = primeiro.valor;
		tmp.proximo = null;
		tmp = null;
		return valor;
	}
	
	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro.proximo; i != null; i = i.proximo) {
			System.out.print(i.valor + " ");
		}
		System.out.print("]");
	}
}
