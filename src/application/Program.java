package application;

import java.util.Scanner;

import ecommerce.EcommerceData;
import menu.Menu;
import menu.options.MenuOption;
import products.crud.ProductCrud;
import result.ResultWithData;
import supplier.crud.SupplierCrud;

public class Program {
	public static EcommerceData applicationData;

	public static void main(String[] args) {
		
		loadApplicationData();
		
		Scanner sc = new Scanner(System.in);

		new Menu("ULTRAPED")
			.addOptions(new MenuOption("Fornecedores", new SupplierCrud().showBackOption()))
			.addOptions(new MenuOption("Produtos", new ProductCrud().showBackOption()))
			.showExitOption()
			.execute(sc);
		
		sc.close();
	}
	
	private static void loadApplicationData() {
		ResultWithData<EcommerceData> appDataResult = EcommerceData.loadAll();
		if (appDataResult.isFailure())
		{
			System.out.println(appDataResult.getMessage());
			System.exit(0);
		}
		
		applicationData = appDataResult.getData();
	}

}
