package costumer.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import address.crud.AddressCrud;
import application.Program;
import consoleinterface.nextaction.NextAction;
import consoleinterface.table.ConsoleTable;
import consoleinterface.table.ConsoleTableColumn;
import costumer.Costumer;
import costumer.validation.CostumerValidation;
import crud.Crud;
import crud.field.IntegerCrudField;
import crud.field.StringCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import result.ResultWithData;

public class CostumerCrud extends Crud {

	public CostumerCrud() {
		super("Clientes");
	}
	
	public static ConsoleTable<Costumer> getDefaultConsoleTable() {
		List<ConsoleTableColumn<Costumer>> columns = new ArrayList<>();

		columns.add(new ConsoleTableColumn<>(5, "ID", (s) -> s.getId()));
		columns.add(new ConsoleTableColumn<>(30, "Nome", (s) -> s.getName()));
		columns.add(new ConsoleTableColumn<>(20, "Telefone", (s) -> s.getPhone()));
		columns.add(new ConsoleTableColumn<>(50, "E-mail", (s) -> s.getEmail()));

		return new ConsoleTable<>(columns);
	}

	public Costumer create(Scanner sc) {
		Costumer dummyCostumer = new Costumer();
		
		updateRecord("Novo cliente", dummyCostumer, sc);

		//TODO: Fazer com que a alteração seja salva em disco
		Program.applicationData.costumerRepository.save(dummyCostumer);

		return dummyCostumer;
	}

	protected NextAction update(Scanner sc) {
		ResultWithData<Costumer> requestResult = requestCostumer(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		//Não preciso atualizar no repositório pois já estou alterando na referência.
		//TODO: Fazer com que a alteração seja salva em disco
		return updateRecord("Atualizar o registro " + requestResult.getData().getId(), requestResult.getData(), sc);
	}

	protected NextAction updateRecord(String title, Costumer record, Scanner sc) {
		List<IMenuOption> options = getDefaultFieldOptions(record);
		options.add(new MethodMenuOption("Concluir", (scanner) -> CostumerValidation.validateAll(record).toExitNextActionIfSucces()));

		return new Menu(title, options).setDetailsToShow(record).execute(sc);
	}

	protected NextAction delete(Scanner sc) {
		ResultWithData<Costumer> requestResult = requestCostumer(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		//TODO: Avaliar como validar se o fornecedor pode ser excluído. Exemplo: o fornecedor tem produtos que estão em uso em algum pedido.
		//TODO: Fazer com que a alteração seja salva em disco
		Program.applicationData.costumerRepository.delete(requestResult.getData().getId());

		return NextAction.Continue("Fornecedor removido.");
	}

	protected void showDataAsTable() {
		getDefaultConsoleTable().setData(Program.applicationData.costumerRepository.getAll()).build();
	}
	
	private List<IMenuOption> getDefaultFieldOptions(Costumer costumer) {
		List<IMenuOption> options = new ArrayList<>();
		
		options.add(new StringCrudField("Nome", "Insira o nome:", costumer::setName));
		options.add(new StringCrudField("Telefone", "Insira o telefone:", costumer::setPhone));
		options.add(new StringCrudField("E-mail", "Insira o e-mail:", costumer::setEmail));
		options.add(new StringCrudField("Cartão de crédito", "Insira o cartão de crédito:", costumer::setCreditCard));
		options.add(new MethodMenuOption("Endereço", (sc) -> new AddressCrud().update(costumer.getAddress(), sc)));
		
		return options;
	}
	
	private ResultWithData<Costumer> requestCostumer(Scanner sc) {
		if (Program.applicationData.supplierRepository.getCount() == 0)
			return ResultWithData.failure("Nenhum cliente cadastrado.");

		ResultWithData<Integer> requestResult = new IntegerCrudField("", "Insira o ID do registro: ").requestData(sc);
		if (requestResult.isFailure())
			return ResultWithData.failure(requestResult.getMessage());
		
		Costumer selectedCostumer = Program.applicationData.costumerRepository.getByID(requestResult.getData());
		if (selectedCostumer == null)
			return ResultWithData.failure("Fornecedor não encontrado.");
		
		return ResultWithData.success(selectedCostumer);
	}

}
