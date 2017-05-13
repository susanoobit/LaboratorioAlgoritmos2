package Aula5;


public class Principal {

	public static void main(String[] args) {
		ListaEstatica l1 = new ListaEstatica();
		
		for (int i = 0; i < 8; i++)
			try {
				l1.inserirFim(i);
			}
			catch (ListaException e) {
				System.out.println(e.getMessage());
			}
	}

}
