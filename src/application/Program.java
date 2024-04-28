package application;

import java.util.Scanner;

import menu.Menu;
import menu.defaultoptions.CancelOption;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		new Menu("ULTRAPED", new CancelOption()).execute(sc);
		
		sc.close();
	}

}
