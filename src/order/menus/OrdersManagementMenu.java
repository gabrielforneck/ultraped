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
		ResultWithData<Integer> requestResult = new IntegerCrudField("", "Insira o ID do pedido:").requestData(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		Order order = localOrderRepository.getByID(requestResult.getData());
		if (order == null)
			return NextAction.Continue("Pedido não encontrado.");
		
		Result cancelResult = order.cancel();
		if (cancelResult.isFailure())
			return NextAction.Continue(cancelResult.getMessage());
		
		updateOrdersInProgress();
		return NextAction.Continue("Pedido cancelado.");
	}
	
	private NextAction deliverOrder(Scanner sc) {
		ResultWithData<Integer> requestResult = new IntegerCrudField("", "Insira o ID do pedido:").requestData(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		Order order = localOrderRepository.getByID(requestResult.getData());
		if (order == null)
			return NextAction.Continue("Pedido não encontrado.");
		
		Result deliverResult = order.deliver();
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
	
	private void addDefaultOptions() {
		options.add(new MethodMenuOption("Entregar pedido", this::deliverOrder));
		options.add(new MethodMenuOption("Cancelar pedido", this::cancelOrder));
		options.add(new MethodMenuOption("Visualizar todos os pedidos", this::viewAll));
		//TODO: Maneira para visualizar os produtos do pedido
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
