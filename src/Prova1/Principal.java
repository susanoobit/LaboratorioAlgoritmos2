package Prova1;

public class Principal {

	public static void main(String[] args) {
		teste1();
		System.out.print("\n");
		teste2();
		
		/*
    	FileReader fr = new FileReader("teste.txt");
        BufferedReader in = new BufferedReader(fr);
    	FileOutputStream outfile = new FileOutputStream("saida.txt");
		PrintStream out = new PrintStream(outfile);
		
        ArrayList<Client> clients = new ArrayList<Client>();
    	String line = in.readLine();
        while (line != null) {
            String data[] = line.split(";");
            clients.add(new Client(Integer.parseInt(data[0]), data[1]));
    		line = in.readLine();
        }
        
        Client[] clientsArray = new Client[clients.size()];
        clients.toArray(clientsArray);
        for (Client client : clientsArray)
            out.print(client.toString());
		
		in.close();
		out.close();
		*/
	}
	
	public static void teste1() {
		System.out.println("\\------- INICIO TESTE 1 -------\\");
		Deque d = new Deque();
		System.out.println("Inserindo o 9 no inicio");
		d.inserir_inicio(9);
		d.mostrar();
		System.out.println("\nInserindo o 15 no inicio");
		d.inserir_inicio(15);
		d.mostrar();
		System.out.println("\nInserindo o 10 no inicio");
		d.inserir_inicio(10);
		d.mostrar();
		System.out.println("\nInserindo o 10 no final");
		d.inserir_final(10);
		d.mostrar();
		System.out.println("\nInserindo o 25 no final");
		d.inserir_final(25);
		d.mostrar();
		System.out.println("\n\\-------- FIM TESTE 1 ---------\\");
	}
	
	public static void teste2() {
		System.out.println("\\------- INICIO TESTE 2 -------\\");
		String c = "unicornio-lindao";
		System.out.println("Entrada: " + c);
		Fila f = new Fila(c.toCharArray());
		System.out.print("Fila: ");
		f.mostrar();
		f.inverter();
		System.out.print("\nFina invertida: ");
		f.mostrar();
		System.out.println("\n\\-------- FIM TESTE 2 ---------\\");
	}
}
