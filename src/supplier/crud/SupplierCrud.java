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
import menu.interfaces.IExecutableOption;
import menu.interfaces.IMenuOption;
import menu.options.MethodMenuOption;
import result.Result;
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
		List<IMenuOption> options = new ArrayList<>();
		
		options.add(new CrudField<String>("Nome", "Insira o nome:", (n) -> dummySupplier.setName(n)));
		options.add(new CrudField<String>("Descrição", "Insira a descrição:", (d) -> dummySupplier.setDescription(d)));
		options.add(new CrudField<String>("Telefone", "Insira o telefone:", (p) -> dummySupplier.setPhone(p)));
		options.add(new CrudField<String>("E-mail", "Insira o e-mail:", (e) -> dummySupplier.setEmail(e)));
		options.add(new MethodMenuOption("Aceitar", (scanner) -> validateAndSaveNewSupplier(dummySupplier)));
		
		new Menu("Novo fornecedor", options).showCancelOption().execute(sc);

		return NextAction.Continue();
	}
	
	private NextAction validateAndSaveNewSupplier(Supplier newSupplier) {
		Result validationResult = SupplierValidations.validateAll(newSupplier);
		
		if (validationResult.isFailure())
			return NextAction.Continue(validationResult.getMessage());
		
		EcommerceData.supplierRepository.saveNew(newSupplier);
		return NextAction.Exit();
	}

	@Override
	protected NextAction update(Scanner sc) {
		sc.nextLine();
		return NextAction.Continue();
	}

	@Override
	protected NextAction delete(Scanner sc) {
		sc.nextLine();
		return NextAction.Continue();
	}

}
