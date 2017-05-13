package Aula6;

public class Principal {
	public static void main(String[] args) {
		Pilha pilha = new Pilha();
		Fila fila = new Fila();
		
    	for (int i = 0; i < 11; i++) {
    		int valor = (int) Math.round(Math.random() * 10);
    		pilha.inserir(valor);
    		fila.inserir(valor);
    	}
    	
    	pilha.mostrar();
    	System.out.println("");
    	fila.mostrar();
	}
}
