package application;

import java.util.Scanner;

import menu.Menu;
import menu.defaultoptions.MenuOption;
import supplier.crud.SupplierCrud;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		new Menu("ULTRAPED")
			.addOptions(new MenuOption("Fornecedores", new SupplierCrud()))
			.ShowCancelOption()
			.execute(sc);
		
		sc.close();
	}

}
