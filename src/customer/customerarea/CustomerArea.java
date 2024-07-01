package customer.customerarea;

import customer.Customer;
import customer.crud.CustomerCrud;
import menu.Menu;
import menu.options.MethodMenuOption;

public class CustomerArea extends Menu {
	
	private Customer customer;

	public CustomerArea(Customer customer) {
		super("Área do cliente " + customer.getName() + " (" + customer.getId() + ")");
		this.customer = customer;
		addDefaultOptions();
	}
	
	private void addDefaultOptions() {

		super.options.add(new MethodMenuOption("Atualizar meu cadastro", (sc) -> CustomerCrud.update(sc, customer)));
		super.options.add(new MethodMenuOption("Excluir meu cadastro", (sc) -> CustomerCrud.delete(sc, customer)));
	}
}
