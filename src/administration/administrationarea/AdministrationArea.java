package administration.administrationarea;

import menu.Menu;
import menu.options.MenuOption;
import order.menus.OrdersManagementMenu;
import supplier.crud.SupplierCrud;

public class AdministrationArea extends Menu {

	public AdministrationArea() {
		super("Área administrativa");
		addDefaultOptions();
	}
	
	private void addDefaultOptions() {
		super.options.add(new MenuOption("Fornecedores", new SupplierCrud().showBackOption()));
		super.options.add(new MenuOption("Gerenciar pedidos", new OrdersManagementMenu().showBackOption()));
	}

}
