package Aula4;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) throws IOException {
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
	}

}
