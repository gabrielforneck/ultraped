package supplier.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import address.crud.AddressCrud;
import application.Program;
import consoleinterface.nextaction.NextAction;
import consoleinterface.table.ConsoleTable;
import consoleinterface.table.ConsoleTableColumn;
import crud.FullCrud;
import crud.field.IntegerCrudField;
import crud.field.StringCrudField;
import menu.Menu;
import menu.options.MenuOption;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IExecutableOption;
import menu.options.interfaces.IMenuOption;
import products.crud.ProductCrud;
import result.ResultWithData;
import supplier.Supplier;
import supplier.validation.SupplierValidations;

public class SupplierCrud extends FullCrud<Supplier> implements IExecutableOption {

	public SupplierCrud() {
		super("Fornecedores");
		addDefaultCrudOptions();
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
		getDefaultConsoleTable().setData(Program.applicationData.supplierRepository.getAll()).build();
	}

	@Override
	protected NextAction create(Scanner sc) {
		Supplier dummySupplier = new Supplier();

		updateRecord("Novo fornecedor", dummySupplier, sc);
		
		//TODO: Fazer com que a alteração seja salva em disco
		Program.applicationData.supplierRepository.save(dummySupplier);

		return NextAction.Continue();
	}

	@Override
	protected NextAction update(Scanner sc) {
		ResultWithData<Supplier> requestResult = requestSupplier(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());

		//Não preciso atualizar no repositório pois já estou alterando na referência.
		//TODO: Fazer com que a alteração seja salva em disco
		return updateRecord("Atualizar o registro " + requestResult.getData().getId(), requestResult.getData(), sc);
	}
	
	@Override
	protected NextAction updateRecord(String title, Supplier record, Scanner sc) {
		List<IMenuOption> options = getDefaultFieldOptions(record);
		options.add(new MethodMenuOption("Concluir", (scanner) -> SupplierValidations.validateAll(record).toExitNextActionIfSucces()));

		return new Menu(title, options).setDetailsToShow(record).execute(sc);
	}

	@Override
	protected NextAction delete(Scanner sc) {
		ResultWithData<Supplier> requestResult = requestSupplier(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		//TODO: Avaliar como validar se o fornecedor pode ser excluído. Exemplo: o fornecedor tem produtos que estão em uso em algum pedido.
		//TODO: Fazer com que a alteração seja salva em disco
		Program.applicationData.supplierRepository.delete(requestResult.getData().getId());

		return NextAction.Continue("Fornecedor removido.");
	}
	
	private NextAction showSupplierDetails(Scanner sc) {
		ResultWithData<Supplier> requestResult = requestSupplier(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		System.out.println(requestResult.getData().toString());
		super.waitForEnter(sc);
		return NextAction.Continue();
	}

	private List<IMenuOption> getDefaultFieldOptions(Supplier supplier) {
		List<IMenuOption> options = new ArrayList<>();

		options.add(new StringCrudField("Nome", "Insira o nome:", supplier::setName));
		options.add(new StringCrudField("Descrição", "Insira a descrição:", supplier::setDescription));
		options.add(new StringCrudField("Telefone", "Insira o telefone:", supplier::setPhone));
		options.add(new StringCrudField("E-mail", "Insira o e-mail:", supplier::setEmail));
		options.add(new MethodMenuOption("Endereço", (sc) -> new AddressCrud().update(supplier.getAddress(), sc)));
		options.add(new MenuOption("Produtos", new ProductCrud(supplier).showBackOption()));

		return options;
	}
	
	private NextAction filterByName(Scanner sc) {
		ResultWithData<String> requestResult = new StringCrudField("", "Insira a pesquisa:").requestData(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		List<Supplier> searchResult = Program.applicationData.supplierRepository.getByName(requestResult.getData());
		if (searchResult.size() == 0)
			return NextAction.Continue("Não houveram resultados.");
		
		getDefaultConsoleTable().setData(searchResult).build();
		super.waitForEnter(sc);
		
		return NextAction.Continue();
	}
	
	private ResultWithData<Supplier> requestSupplier(Scanner sc) {
		if (Program.applicationData.supplierRepository.getCount() == 0)
			return ResultWithData.failure("Nenhum fornecedor cadastrado.");

		ResultWithData<Integer> requestResult = new IntegerCrudField("", "Insira o ID do registro: ").requestData(sc);
		if (requestResult.isFailure())
			return ResultWithData.failure(requestResult.getMessage());
		
		Supplier selectedSupplier = Program.applicationData.supplierRepository.getByID(requestResult.getData());
		if (selectedSupplier == null)
			return ResultWithData.failure("Fornecedor não encontrado.");
		
		return ResultWithData.success(selectedSupplier);
	}
	
	private void addDefaultCrudOptions() {
		options.add(new MethodMenuOption("Criar", this::create));
		options.add(new MethodMenuOption("Alterar", this::update));
		options.add(new MethodMenuOption("Excluir", this::delete));
		options.add(new MethodMenuOption("Buscar", this::filterByName));
		options.add(new MethodMenuOption("Visualizar", this::showSupplierDetails));
	}
}
