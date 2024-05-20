package supplier.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import address.crud.AddressCrud;
import consoleinterface.nextaction.NextAction;
import consoleinterface.table.ConsoleTable;
import consoleinterface.table.ConsoleTableColumn;
import crud.Crud;
import crud.CrudField;
import ecommerce.EcommerceData;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IExecutableOption;
import menu.options.interfaces.IMenuOption;
import products.Product;
import products.crud.ProductCrud;
import result.ResultWithData;
import supplier.Supplier;
import supplier.validation.SupplierValidations;
import supplierproduct.SupplierProduct;
import supplierproduct.validation.SupplierProductValidation;

public class SupplierCrud extends Crud implements IExecutableOption {

	public SupplierCrud() {
		super("Fornecedores");
		addDefaultCrudOptions();
		options.add(new MethodMenuOption("Buscar", (sc) -> filterByName(sc)));
	}

	public static ConsoleTable<Supplier> getDefaultConsoleTable() {
		List<ConsoleTableColumn<Supplier>> columns = new ArrayList<>();

		columns.add(new ConsoleTableColumn<>(5, "ID", (s) -> s.getId()));
		columns.add(new ConsoleTableColumn<>(30, "Nome", (s) -> s.getName()));
		columns.add(new ConsoleTableColumn<>(20, "Telefone", (s) -> s.getPhone()));
		columns.add(new ConsoleTableColumn<>(50, "E-mail", (s) -> s.getEmail()));

		return new ConsoleTable<>(columns);
	}

	@Override
	protected void showDataAsTable() {
		getDefaultConsoleTable().setData(EcommerceData.supplierRepository.getData()).build();
	}

	@Override
	protected NextAction create(Scanner sc) {
		Supplier dummySupplier = new Supplier();
		List<IMenuOption> options = getDefaultFieldOptions(dummySupplier);

		options.add(new MethodMenuOption("Endereço", (scanner) -> new AddressCrud().update(dummySupplier.getAddress(), sc)));
		options.add(new MethodMenuOption("Aceitar", (scanner) -> validateAndSaveNew(dummySupplier,
				(s) -> SupplierValidations.validateAll(s), (s) -> EcommerceData.supplierRepository.save(s))));

		new Menu("Novo fornecedor", options).setDetailsToShow(dummySupplier).showCancelOption().execute(sc);

		return NextAction.Continue();
	}

	@Override
	protected NextAction update(Scanner sc) {
		ResultWithData<Supplier> selectResult = waitForId(sc, EcommerceData.supplierRepository);
		if (selectResult.isFailure())
			return NextAction.Continue(selectResult.getMessage());

		Supplier selectedSupplier = selectResult.getData();

		List<IMenuOption> options = getDefaultFieldOptions(selectedSupplier);

		options.add(new MethodMenuOption("Vincular um produto",
				(scanner) -> addProduct(scanner, selectedSupplier.getId())));
		options.add(new MethodMenuOption("Ver produtos vinculados",
				(scanner) -> showAllProductsBySupplierID(scanner, selectedSupplier.getId())));
		options.add(new MethodMenuOption("Desvincular produto",
				(scanner) -> remProduct(scanner, selectedSupplier.getId())));
		options.add(new MethodMenuOption("Endereço", (scanner) -> new AddressCrud().update(selectedSupplier.getAddress(), sc)));
		options.add(new MethodMenuOption("Aceitar", (scanner) -> validateAndSaveNew(selectedSupplier,
				(s) -> SupplierValidations.validateAll(s), (s) -> EcommerceData.supplierRepository.update(s))));

		new Menu("Alterar o fornecedor " + selectedSupplier.getId(), options).setDetailsToShow(selectedSupplier)
				.showCancelOption().execute(sc);

		return NextAction.Continue();
	}

	@Override
	protected NextAction delete(Scanner sc) {
		ResultWithData<Supplier> selectResult = waitForId(sc, EcommerceData.supplierRepository);
		if (selectResult.isFailure())
			return NextAction.Continue(selectResult.getMessage());

		Supplier selectedSupplier = selectResult.getData();

		List<IMenuOption> options = new ArrayList<>();

		options.add(new MethodMenuOption("Aceitar", (scanner) -> NextAction.ExecuteAndExit(scanner,
				(s) -> EcommerceData.supplierRepository.delete(selectedSupplier))));

		new Menu("Você deseja mesmo remover o fornecedor " + selectedSupplier.getId() + "?", options)
				.setDetailsToShow(selectedSupplier).showCancelOption().execute(sc);

		return NextAction.Continue();
	}

	private List<IMenuOption> getDefaultFieldOptions(Supplier supplier) {
		List<IMenuOption> options = new ArrayList<>();

		options.add(new CrudField<String>("Nome", "Insira o nome:", (n) -> supplier.setName(n)));
		options.add(new CrudField<String>("Descrição", "Insira a descrição:", (d) -> supplier.setDescription(d)));
		options.add(new CrudField<String>("Telefone", "Insira o telefone:", (p) -> supplier.setPhone(p)));
		options.add(new CrudField<String>("E-mail", "Insira o e-mail:", (e) -> supplier.setEmail(e)));

		return options;
	}

	private NextAction addProduct(Scanner sc, int supplierID) {
		SupplierProduct dummySupplierProduct = new SupplierProduct();

		ResultWithData<Product> selectProductResult = waitForId(sc, EcommerceData.productRepository,
				"Insira o ID do produto: ");
		if (selectProductResult.isFailure())
			return NextAction.Continue(selectProductResult.getMessage());

		dummySupplierProduct.setSupplierID(supplierID);
		dummySupplierProduct.setProductID(selectProductResult.getData().getId());

		if (SupplierProductValidation.isASupplierProduct(dummySupplierProduct))
			return NextAction.Continue("Essa vinculação já existe.");

		List<IMenuOption> options = new ArrayList<>();

		options.add(new MethodMenuOption("Aceitar", (scanner) -> NextAction.ExecuteAndExit(scanner,
				(s) -> EcommerceData.supplierProductRepository.save(dummySupplierProduct))));

		String details = "FORNECEDOR:\n";
		details += EcommerceData.supplierRepository.getByID(dummySupplierProduct.getSupplierID()).toString() + "\n\n";
		details += "PRODUTO:\n";
		details += EcommerceData.productRepository.getByID(dummySupplierProduct.getProductID()).toString();

		new Menu("Você deseja mesmo confirmar essa vinculação?", options).setDetailsToShow(details).showCancelOption()
				.execute(sc);

		return NextAction.Exit();
	}

	private NextAction showAllProductsBySupplierID(Scanner sc, int supplierID) {
		ProductCrud.getDefaultConsoleTable().setData(SupplierProductValidation.getAllProductsBySupplierID(supplierID))
				.build();

		System.out.println("\nPressione enter para continuar");
		sc.nextLine();

		return NextAction.Continue();
	}

	private NextAction remProduct(Scanner sc, int supplierID) {
		SupplierProduct dummySupplierProduct = new SupplierProduct();

		ResultWithData<Product> selectProductResult = waitForId(sc, EcommerceData.productRepository,
				"Insira o ID do produto: ");
		if (selectProductResult.isFailure())
			return NextAction.Continue(selectProductResult.getMessage());

		dummySupplierProduct.setSupplierID(supplierID);
		dummySupplierProduct.setProductID(selectProductResult.getData().getId());

		dummySupplierProduct.setId(SupplierProductValidation.getID(dummySupplierProduct));
		if (dummySupplierProduct.getId() == 0)
			return NextAction.Continue("Essa vinculação não existe.");

		String details = "FORNECEDOR:\n";
		details += EcommerceData.supplierRepository.getByID(dummySupplierProduct.getSupplierID()).toString() + "\n\n";
		details += "PRODUTO:\n";
		details += EcommerceData.productRepository.getByID(dummySupplierProduct.getProductID()).toString();

		new Menu("Você deseja mesmo desfazer essa vinculação?")
				.addOptions(new MethodMenuOption("Aceitar",
						(scanner) -> NextAction.ExecuteAndExit(scanner,
								(s) -> EcommerceData.supplierProductRepository.delete(dummySupplierProduct))))
				.setDetailsToShow(details).showCancelOption().execute(sc);

		return NextAction.Exit();
	}
	
	private NextAction filterByName(Scanner sc) {
		
		System.out.println("Insira a pesquisa:");
		String filter = sc.nextLine();
		List<Supplier> filteredResults = new ArrayList<Supplier>();
		
		for (Supplier sp : EcommerceData.supplierRepository.getData())
			if (sp.getName().contains(filter))
				filteredResults.add(sp);
		
		if (filteredResults.size() == 0)
			return NextAction.Continue("Não houveram resultados.");
		
		getDefaultConsoleTable().setData(filteredResults).build();
		System.out.println("Prescione enter para continuar.");
		sc.nextLine();
		
		return NextAction.Continue();
	}

}
