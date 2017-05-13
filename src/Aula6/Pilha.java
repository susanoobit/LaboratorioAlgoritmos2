package Aula6;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Pilha {
	public Celula topo;
	Pilha () {
		topo = null;
	}
	
	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.proximo = topo;
		topo = tmp;
		tmp = null;
	}
	
	public int remover() throws Exception {
		if(topo==null) throw new Exception("Erro!");
		int valor = topo.valor;
		Celula tmp = topo;
		topo = topo.proximo;
		tmp.proximo = null;
		tmp = null;
		return valor;
	}
	
	public void mostrar() {
//		Celula tmp;
//		tmp = topo;
//		System.out.print("[");
//		while (tmp != null) {
//			System.out.print(tmp.valor + " ");
//			tmp = tmp.proximo;
//		}
//		System.out.print("]");
		
		System.out.print("[ ");
		for (Celula i = topo; i != null; i = i.proximo) {
			System.out.print(i.valor + " ");
		}
		System.out.print("]");
	}
	
	public void show(Celula i) {
		if(i != null) {
			System.out.println(i.valor);
			show(i.proximo);
		}
	}
}
