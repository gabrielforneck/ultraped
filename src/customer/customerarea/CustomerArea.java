package customer.customerarea;

import java.util.List;
import java.util.Scanner;

import application.Program;
import consoleinterface.nextaction.NextAction;
import consoleinterface.table.ConsoleTableColumn;
import crud.field.IntegerCrudField;
import customer.Customer;
import customer.crud.CustomerCrud;
import menu.Menu;
import menu.options.MethodMenuOption;
import order.Order;
import order.menus.OrderCreationMenu;
import order.repository.OrderRepository;
import products.repository.ProductRepository;
import result.ResultWithData;

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
	
	private ResultWithData<Order> requestOrder(Scanner sc) {
		if (localOrderRepository.getCount() ==  0)
			return ResultWithData.failure("Nenhum pedido para o seu usuário.");

		ResultWithData<Integer> requestResult = new IntegerCrudField("", "Insira o ID do registro: ").requestData(sc);
		if (requestResult.isFailure())
			return ResultWithData.failure(requestResult.getMessage());
		
		Order selectedOrder = localOrderRepository.getByID(requestResult.getData());
		if (selectedOrder == null)
			 return ResultWithData.failure("Pedido não encontrado.");
		
		return ResultWithData.success(selectedOrder);
	}
	
	private NextAction viewOrder(Scanner sc) {
		ResultWithData<Order> requestResult = requestOrder(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		System.out.println(requestResult.getData().toString());
		
		System.out.println();
		System.out.println("Produtos do pedido:");
		
		for (int i = 0; i < requestResult.getData().getOrderProductsQuantity(); i++) {
			System.out.println("-> Item " + (i+1) + ":");
			System.out.println(requestResult.getData().getOrderProduct(i).toString());
			System.out.println();
			
		}
		
		super.waitForEnter(sc);
		
		return NextAction.Continue();
	}
	private void addDefaultOptions() {

		super.options.add(new MethodMenuOption("Novo pedido", this::createOrder));
		super.options.add(new MethodMenuOption("Ver todos meus pedidos", this::viewAllOrders));
		super.options.add(new MethodMenuOption("Visualizar pedido", this::viewOrder));
		super.options.add(new MethodMenuOption("Atualizar meu cadastro", (sc) -> CustomerCrud.update(sc, customer)));
		super.options.add(new MethodMenuOption("Excluir meu cadastro", (sc) -> CustomerCrud.delete(sc, customer)));
	}
	
	private NextAction viewAllOrders(Scanner sc) {
		
		Order.getDefaultConsoleTable()
			.setData(localOrderRepository.getAll())
			.addColumn(new ConsoleTableColumn<Order>(20, "Situação", (o) -> o.getSituation()))
			.build();
		super.waitForEnter(sc);
		
		return NextAction.Continue();
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
