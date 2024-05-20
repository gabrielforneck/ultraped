package application;

import java.util.Scanner;

import menu.Menu;
import menu.options.MenuOption;
import supplier.crud.SupplierCrud;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		new Menu("ULTRAPED")
			.addOptions(new MenuOption("Fornecedores", new SupplierCrud().showCancelOption()))
			.showCancelOption()
			.execute(sc);
		
		sc.close();
	}

}
