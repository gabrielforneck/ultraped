package order.menus;

import java.util.Scanner;

import application.Program;
import consoleinterface.nextaction.NextAction;
import crud.field.IntegerCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import order.Order;
import order.OrderProduct;
import order.exceptions.NoProductsRegisteredException;
import order.validation.OrderValidation;
import result.Result;
import result.ResultWithData;

public class OrderCreationMenu extends Menu {

	private Order dummyOrder;

	public OrderCreationMenu() {
		super("Novo pedido");
		addDefaultOptions();
		dummyOrder = new Order();
		super.setDetailsToShow(dummyOrder);
	}
	
	
	private NextAction addOrderItem(Scanner sc) {
		OrderProduct dummyOrderProduct = new OrderProduct();
		new OrderProductMenu(dummyOrderProduct).execute(sc);
		
		Result addResult = dummyOrder.addOrderProduct(dummyOrderProduct);
		if (addResult.isFailure())
			return NextAction.Continue(addResult.getMessage());

		return NextAction.Continue();
	}
	
	private NextAction remOrderItem(Scanner sc) {
		ResultWithData<Integer> requestResult = new IntegerCrudField("", "Insira o ID do produto:").requestData(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		int pIndex = dummyOrder.getOrderProductIndexByProductID(requestResult.getData());
		if (pIndex == -1)
			return NextAction.Continue("Produto do pedido não encontrado.");
		
		return NextAction.Continue();
	}
	
	private void addDefaultOptions() {
		
		super.options.add(new MethodMenuOption("Adicionar um item", this::addOrderItem));
		super.options.add(new MethodMenuOption("Remover item", this::remOrderItem));
		super.options.add(new MethodMenuOption("Concluir", (sc) -> OrderValidation.validateAll(dummyOrder).toExitNextActionIfSucces()));
	}
	
	
	@Override
	public NextAction execute(Scanner sc) {
		if (Program.applicationData.supplierRepository.getAllProducts().size() == 0)
			throw new NoProductsRegisteredException();
		
		return super.execute(sc);
	}
	
	public Order getDummyOrder() {
		return dummyOrder;
	}

}
