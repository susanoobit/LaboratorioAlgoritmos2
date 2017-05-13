package Aula7;

public class CelulaGenerica<T> {
	public CelulaGenerica<T> proximo;
	public T valor;
	
	CelulaGenerica () {
		this.proximo = null;
	}
	
	CelulaGenerica (T valor) {
		this.proximo = null;
		this.valor = valor;
	}

}
