package application;

import java.util.Scanner;

import administration.administrationarea.AdministrationArea;
import customer.customerarea.CustomerAreaLoginScreen;
import ecommerce.EcommerceData;
import menu.Menu;
import menu.options.MenuOption;
import result.Result;
import result.ResultWithData;

public class Program {
	public static EcommerceData applicationData;

	public static void main(String[] args) {
		
		loadApplicationData();
		
		Scanner sc = new Scanner(System.in);

		new Menu("ULTRAPED")
			.addOptions(new MenuOption("Área do cliente", new CustomerAreaLoginScreen().showBackOption()))
			.addOptions(new MenuOption("Área interna", new AdministrationArea().showBackOption()))
			.showExitOption()
			.execute(sc);
		
		Result saveResult = applicationData.saveAll();
		if (saveResult.isFailure())
			System.out.println("Ocorreu um erro ao salvar os dados da aplicação: " + saveResult.getMessage());
		
		sc.close();
	}
	
	private static void loadApplicationData() {
		ResultWithData<EcommerceData> appDataResult = EcommerceData.loadAll();
		if (appDataResult.isFailure())
		{
			System.out.println("Ocorreu um erro ao carregar os dados da aplicação: " + appDataResult.getMessage());
			System.exit(0);
		}
		
		applicationData = appDataResult.getData();
	}

}
