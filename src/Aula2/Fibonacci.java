package Aula2;
import java.util.Scanner;

public class Fibonacci {
	
	public static int fib(int n) {
		if (n > 1)
			return fib(n - 1) + fib (n - 2);
		else
			return 1;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Digite um numero inteiro positivo para calculo do fibonacci: ");
		System.out.println("O valor do fibonacci eh: " + fib(in.nextInt()));
		in.close();
	}

}
