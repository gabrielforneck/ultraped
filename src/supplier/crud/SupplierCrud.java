package supplier.crud;

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
import menu.options.interfaces.IExecutableOption;
import menu.options.interfaces.IMenuOption;
import result.ResultWithData;
import supplier.Supplier;
import supplier.validation.SupplierValidations;

public class SupplierCrud extends Crud implements IExecutableOption {

	public SupplierCrud() {
		super("Fornecedores");
		addDefaultCrudOptions();
	}

	public static ConsoleTable<Supplier> getDefaultConsoleTable() {
		List<ConsoleTableColumn<Supplier>> columns = new ArrayList<>();

		columns.add(new ConsoleTableColumn<Supplier>(5, "ID", (s) -> s.getId()));
		columns.add(new ConsoleTableColumn<Supplier>(30, "Nome", (s) -> s.getName()));
		columns.add(new ConsoleTableColumn<Supplier>(20, "Telefone", (s) -> s.getPhone()));
		columns.add(new ConsoleTableColumn<Supplier>(50, "E-mail", (s) -> s.getEmail()));

		return new ConsoleTable<Supplier>(EcommerceData.supplierRepository.getData(), columns);
	}

	@Override
	protected void showDataAsTable() {
		getDefaultConsoleTable().build();
	}

	@Override
	protected NextAction create(Scanner sc) {
		Supplier dummySupplier = new Supplier();
		List<IMenuOption> options = getDefaultFieldOptions(dummySupplier);

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
				(s) -> EcommerceData.supplierRepository.delete(selectedSupplier.getId()))));

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

}
