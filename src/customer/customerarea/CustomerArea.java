package customer.customerarea;

import java.util.List;
import java.util.Scanner;

import application.Program;
import consoleinterface.nextaction.NextAction;
import customer.Customer;
import customer.crud.CustomerCrud;
import menu.Menu;
import menu.options.MethodMenuOption;
import order.Order;
import order.menus.OrderCreationMenu;
import order.repository.OrderRepository;
import products.repository.ProductRepository;

public class CustomerArea extends Menu {
	
	private Customer customer;
	private OrderRepository localOrderRepository;

	public CustomerArea(Customer customer) {
		super("Área do cliente " + customer.getName() + " (" + customer.getId() + ")");
		this.customer = customer;
		this.localOrderRepository = new OrderRepository(customer.getOrders());
		addDefaultOptions();
		updateOrdersInProgress();
	}
	
	private NextAction createOrder(Scanner sc) {
		if (new ProductRepository(Program.applicationData.supplierRepository.getAllProducts()).getAllWithStock().isEmpty())
			return NextAction.Continue("Não existem produtos cadastrados para realizar um pedido.");

		OrderCreationMenu menu = new OrderCreationMenu();
		menu.execute(sc);
		
		localOrderRepository.save(menu.getDummyOrder());
		
		updateOrdersInProgress();
		return NextAction.Continue();
	}
	
	private void addDefaultOptions() {

		super.options.add(new MethodMenuOption("Novo pedido", this::createOrder));
		super.options.add(new MethodMenuOption("Atualizar meu cadastro", (sc) -> CustomerCrud.update(sc, customer)));
		super.options.add(new MethodMenuOption("Excluir meu cadastro", (sc) -> CustomerCrud.delete(sc, customer)));
	}
	
	private void updateOrdersInProgress() {
		List<Order> orders = localOrderRepository.getOrdersInProgress();
		
		if (orders.isEmpty()) {
			super.setDetailsToShow(null);
			return;
		}
		
		String str = "Pedidos em andamento: " + "\n"
				+ Order.getDefaultConsoleTable().setData(orders).toString();
		
		super.setDetailsToShow(str);
	}
}
