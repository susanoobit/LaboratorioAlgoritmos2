package Aula2;

import java.util.Scanner;

public class QuantasLetras {

	public static int quantasLetras(int idx, char[] palavra) {
		if (idx < palavra.length)
			return quantasLetras(++idx, palavra);
		
		if (idx > palavra.length)
			return 0;
		
		if (palavra[idx] > 'A' && palavra[idx] < 'Z') {
			return 1;
		}
		return 0;
		/*
		int r = 0;
		for (char c : palavra.toCharArray()) {
			if (c > 'A' && c < 'Z')
				++r;
		}
		return r;
		*/
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Digite uma palavra para contar o numero de maiusculas: ");
		System.out.println("O numero de maiusculas eh: " + quantasLetras(0, in.nextLine().toCharArray()));
		in.close();
	}

}
