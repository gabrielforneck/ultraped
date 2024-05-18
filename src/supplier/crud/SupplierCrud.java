package supplier.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import consoleinterface.nextaction.NextAction;
import consoleinterface.table.ConsoleTable;
import consoleinterface.table.ConsoleTableColumn;
import crud.Crud;
import menu.interfaces.IExecutableOption;
import supplier.Supplier;

public class SupplierCrud extends Crud implements IExecutableOption {

	public static final ArrayList<Supplier> SUPPLIERS = new ArrayList<Supplier>();
	
	public SupplierCrud() {
		super("Fornecedores");
		addDefaultCrudOptions();
	}

	public NextAction execute(Scanner sc) {
		
		NextAction nextAction;
		do {
			constructCrud();
			System.out.println();
			nextAction = waitForOptionAndExecute(sc);
		} while (!nextAction.nextActionIsExit());
		
		return NextAction.Continue();
	}

	@Override
	protected void showDataAsTable() {
		List<ConsoleTableColumn<Supplier>> columns = new ArrayList<>();
		
		columns.add(new ConsoleTableColumn<Supplier>(5, "ID", (s) -> s.getId()));
		columns.add(new ConsoleTableColumn<Supplier>(30, "Nome", (s) -> s.getName()));
		columns.add(new ConsoleTableColumn<Supplier>(20, "Telefone", (s) -> s.getPhone()));
		columns.add(new ConsoleTableColumn<Supplier>(50, "E-mail", (s) -> s.getId()));
		
		new ConsoleTable<Supplier>(SUPPLIERS, columns).build();
	}

	@Override
	protected NextAction create(Scanner sc) {
		sc.nextLine();
		return NextAction.Continue();
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
