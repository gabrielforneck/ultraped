package application;

import java.util.ArrayList;
import java.util.Scanner;

import menu.Menu;
import menu.defaultoptions.CancelOption;
import menu.defaultoptions.MenuOption;
import menu.interfaces.IMenuOption;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<IMenuOption> options = new ArrayList<IMenuOption>();
		options.add(new CancelOption());
		options.add(new MenuOption("Submenu", new Menu("a", new CancelOption())));
		new Menu("ULTRAPED", options).execute(sc);
		
		sc.close();
	}

}
