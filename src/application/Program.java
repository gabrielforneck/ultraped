package application;

import java.util.Scanner;

import menu.Menu;
import menu.defaultoptions.CancelOption;
import menu.defaultoptions.MenuOption;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		new Menu("ULTRAPED", new MenuOption("Submenu", new Menu("a", new CancelOption())))
			.ShowCancelOption()
			.execute(sc);
		
		sc.close();
	}

}
