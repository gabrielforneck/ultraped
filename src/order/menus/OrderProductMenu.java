package order.menus;

import java.util.Scanner;

import consoleinterface.nextaction.NextAction;
import crud.field.IntegerCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import order.OrderProduct;
import order.validation.OrderProductValidation;
import products.Product;
import products.selection.SelectProductMenu;

public class OrderProductMenu extends Menu {
	
	private OrderProduct dummyOrderProduct;

	public OrderProductMenu(OrderProduct dummyOrderProduct) {
		super("Novo item");
		this.dummyOrderProduct = dummyOrderProduct;
		addDefaultOptions(this.dummyOrderProduct);
		super.setDetailsToShow(dummyOrderProduct);
	}
	
	private NextAction selectProduct(Scanner sc) {
		
		SelectProductMenu menu = new SelectProductMenu();
		menu.showBackOption().execute(sc);
		Product selectedProduct;

		if ((selectedProduct = menu.getSelectedProduct()) == null)
			return NextAction.Continue();
		
		dummyOrderProduct.setProduct(selectedProduct);
		return NextAction.Continue();
	}
	
	private void addDefaultOptions(OrderProduct o) {
		super.options.add(new MethodMenuOption("Selecionar produto", this::selectProduct));
		super.options.add(new IntegerCrudField("Definir a quantidade", "Insira a quantidade a ser comprada:", o::setQuantity));
		super.options.add(new MethodMenuOption("Aceitar", (sc) -> OrderProductValidation.validateAll(o).toExitNextActionIfSucces()));
	}
}
