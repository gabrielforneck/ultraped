package supplier.crud;

import java.util.ArrayList;
import java.util.Scanner;

import crud.Crud;
import menu.interfaces.IExecutableOption;
import menu.nextaction.NextAction;
import supplier.Supplier;

public class SupplierCrud extends Crud implements IExecutableOption {

	public static final ArrayList<Supplier> SUPPLIERS = new ArrayList<Supplier>();
	
	public SupplierCrud() {
		super("Fornecedores");
		addDefaultCrudOptions();
	}

	public NextAction execute(Scanner sc) {
		
		
		return NextAction.Continue();
	}

	@Override
	protected void showDataAsTable() {
		//TODO: Avaliar como fazer algo genérico em ConsoleInterface para mostrar tabelas
	}

}
