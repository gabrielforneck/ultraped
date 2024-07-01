package customer.customerarea;

import customer.Customer;
import customer.crud.CustomerCrud;
import menu.Menu;
import menu.options.MethodMenuOption;

public class CustomerArea extends Menu {
	
	private Customer costumer;

	public CustomerArea(Customer costumer) {
		super("Área do cliente " + costumer.getName() + " (" + costumer.getId() + ")");
		this.costumer = costumer;
		addDefaultOptions();
	}
	
	private void addDefaultOptions() {

		super.options.add(new MethodMenuOption("Atualizar meu cadastro", (sc) -> CustomerCrud.update(sc, costumer)));
		super.options.add(new MethodMenuOption("Excluir meu cadastro", (sc) -> CustomerCrud.delete(sc, costumer)));
	}
}
