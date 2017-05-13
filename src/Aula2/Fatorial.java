package Aula2;
import java.util.Scanner;

public class Fatorial {
	
	public static int fat(int n) {
		if (n > 1)
			return n * fat(n - 1);
		else
			return 1;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Digite um numero inteiro positivo para calculo do fatorial: ");
		System.out.println("O valor do fatorial eh: " + fat(in.nextInt()));
		in.close();
	}

}
