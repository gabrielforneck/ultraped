package costumerarea;

import java.util.Scanner;

import application.Program;
import consoleinterface.nextaction.NextAction;
import costumer.Costumer;
import costumer.crud.CostumerCrud;
import crud.field.StringCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import result.ResultWithData;

public class CostumerAreaLoginScreen extends Menu {
	
	public CostumerAreaLoginScreen() {
		super("Acessar área do cliente");
		addDefaultOptions();
	}
	
	private void addDefaultOptions() {
		super.options.add(new MethodMenuOption("Criar conta", this::createAccount));
		super.options.add(new MethodMenuOption("Realizar login", this::login));
	}
	
	private NextAction createAccount(Scanner sc) {
		Costumer newCostummer = CostumerCrud.create(sc);
		
		System.out.println();
		return new CostumerArea(newCostummer).showBackOption().execute(sc);
	}
	
	private NextAction login(Scanner sc) {
		ResultWithData<Costumer> requestResult = requestCostumerByIDOrEmail(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		System.out.println();
		return new CostumerArea(requestResult.getData()).showBackOption().execute(sc);
	}
	
	private ResultWithData<Costumer> requestCostumerByIDOrEmail(Scanner sc) {
		ResultWithData<String> requestResult = new StringCrudField("", "Insira o ID ou o e-mail do cliente:").requestData(sc);
		if (requestResult.isFailure())
			return ResultWithData.failure(requestResult.getMessage());
		
		Costumer c;
		Integer id;
		
		try {
			id = Integer.parseInt(requestResult.getData());
		} catch (NumberFormatException ex) {
			//O usuário não informou o ID.
			if ((c = Program.applicationData.costumerRepository.getByEmail(requestResult.getData())) == null)
				return ResultWithData.failure("Cliente não encontrado.");
			
			return ResultWithData.success(c);
		}
		
		if ((c = Program.applicationData.costumerRepository.getByID(id)) == null)
			return ResultWithData.failure("Cliente não encontrado.");
		
		return ResultWithData.success(c);
	}
}