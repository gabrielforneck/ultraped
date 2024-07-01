package order.menus;

import java.util.List;
import java.util.Scanner;

import application.Program;
import consoleinterface.nextaction.NextAction;
import consoleinterface.table.ConsoleTableColumn;
import crud.field.IntegerCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import order.Order;
import order.repository.OrderRepository;
import products.Product;
import result.Result;
import result.ResultWithData;

public class OrdersManagementMenu extends Menu {

	private OrderRepository localOrderRepository;
	
	public OrdersManagementMenu() {
		super("Gerenciamento dos pedidos do programa");
		addDefaultOptions();
		localOrderRepository = new OrderRepository(Program.applicationData.customerRepository.getAllOrders());
		updateOrdersInProgress();
	}
	
	private NextAction cancelOrder(Scanner sc) {
		ResultWithData<Order> requestResult = requestOrder(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		Result cancelResult = requestResult.getData().cancel();
		if (cancelResult.isFailure())
			return NextAction.Continue(cancelResult.getMessage());
		
		updateOrdersInProgress();
		return NextAction.Continue("Pedido cancelado.");
	}
	
	private NextAction deliverOrder(Scanner sc) {
		ResultWithData<Order> requestResult = requestOrder(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		Result deliverResult = requestResult.getData().deliver();
		if (deliverResult.isFailure())
			return NextAction.Continue(deliverResult.getMessage());
		
		updateOrdersInProgress();
		return NextAction.Continue("Pedido entregue.");
	}
	
	private NextAction viewAll(Scanner sc) {
		Order.getDefaultConsoleTable()
			.setData(localOrderRepository.getAll())
			.addColumn(new ConsoleTableColumn<Order>(20, "Situação", (o) -> o.getSituation()))
			.build();
		super.waitForEnter(sc);

		return NextAction.Continue();
	}
	
	private ResultWithData<Order> requestOrder(Scanner sc) {
		if (localOrderRepository.getCount() ==  0)
			return ResultWithData.failure("Nenhum pedido cadastrado.");

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
		
		System.out.println("Produtos do pedido:");
		for (Product p : requestResult.getData().getAllProducts()) {
			System.out.println(p);
			System.out.println();
		}
		
		return NextAction.Continue();
	}
	
	private void addDefaultOptions() {
		options.add(new MethodMenuOption("Entregar pedido", this::deliverOrder));
		options.add(new MethodMenuOption("Cancelar pedido", this::cancelOrder));
		options.add(new MethodMenuOption("Visualizar todos os pedidos", this::viewAll));
		options.add(new MethodMenuOption("Visualizar pedido", this::viewOrder));
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
