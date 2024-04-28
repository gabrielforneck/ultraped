package application;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		MainMenu menu = new MainMenu();
		menu.execute(sc);
		
		sc.close();
	}

}
