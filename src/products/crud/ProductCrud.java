package products.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import consoleinterface.nextaction.NextAction;
import consoleinterface.table.ConsoleTable;
import consoleinterface.table.ConsoleTableColumn;
import crud.Crud;
import crud.CrudField;
import ecommerce.EcommerceData;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import products.Product;
import products.validation.ProductValidation;
import result.ResultWithData;

public class ProductCrud extends Crud {
	
	public ProductCrud() {
		super("Produtos");
		addDefaultCrudOptions();
	}
	
	public static ConsoleTable<Product> getDefaultConsoleTable() {
		List<ConsoleTableColumn<Product>> columns = new ArrayList<>();

		columns.add(new ConsoleTableColumn<>(5, "ID", (s) -> s.getId()));
		columns.add(new ConsoleTableColumn<>(30, "Nome", (s) -> s.getName()));
		columns.add(new ConsoleTableColumn<>(70, "Descrição", (s) -> s.getDescription()));

		return new ConsoleTable<Product>(EcommerceData.productRepository.getData(), columns);
	}

	@Override
	protected void showDataAsTable() {
		getDefaultConsoleTable().build();
	}

	@Override
	protected NextAction create(Scanner sc) {
		Product dummyProduct = new Product();
		List<IMenuOption> options = getDefaultFieldOptions(dummyProduct);

		options.add(new MethodMenuOption("Aceitar", (scanner) -> validateAndSaveNew(dummyProduct,
				(s) -> ProductValidation.validateAll(s), (s) -> EcommerceData.productRepository.save(s))));

		new Menu("Novo produto", options).setDetailsToShow(dummyProduct).showCancelOption().execute(sc);

		return NextAction.Continue();
	}

	@Override
	protected NextAction update(Scanner sc) {
		ResultWithData<Product> selectResult = waitForId(sc, EcommerceData.productRepository);
		if (selectResult.isFailure())
			return NextAction.Continue(selectResult.getMessage());

		Product selectedProduct = selectResult.getData();

		List<IMenuOption> options = getDefaultFieldOptions(selectedProduct);

		options.add(new MethodMenuOption("Aceitar", (scanner) -> validateAndSaveNew(selectedProduct,
				(s) -> ProductValidation.validateAll(s), (s) -> EcommerceData.productRepository.update(s))));

		new Menu("Alterar o produto " + selectedProduct.getId(), options).setDetailsToShow(selectedProduct)
				.showCancelOption().execute(sc);

		return NextAction.Continue();
	}

	@Override
	protected NextAction delete(Scanner sc) {
		ResultWithData<Product> selectResult = waitForId(sc, EcommerceData.productRepository);
		if (selectResult.isFailure())
			return NextAction.Continue(selectResult.getMessage());

		Product selectedProduct = selectResult.getData();

		List<IMenuOption> options = new ArrayList<>();

		options.add(new MethodMenuOption("Aceitar", (scanner) -> NextAction.ExecuteAndExit(scanner,
				(s) -> EcommerceData.productRepository.delete(selectedProduct))));

		new Menu("Você deseja mesmo remover o produto " + selectedProduct.getId() + "?", options)
				.setDetailsToShow(selectedProduct).showCancelOption().execute(sc);

		return NextAction.Continue();
	}
	
	private List<IMenuOption> getDefaultFieldOptions(Product product) {
		List<IMenuOption> options = new ArrayList<>();

		options.add(new CrudField<String>("Nome", "Insira o nome:", (n) -> product.setName(n)));
		options.add(new CrudField<String>("Descrição", "Insira a descrição:", (d) -> product.setDescription(d)));

		return options;
	}

}
