package costumerarea;

import java.util.Scanner;

import consoleinterface.nextaction.NextAction;
import costumer.Costumer;
import costumer.crud.CostumerCrud;
import menu.Menu;
import menu.options.MethodMenuOption;

public class CostumerAreaLoginScreen extends Menu {
	
	public CostumerAreaLoginScreen() {
		super("Acessar área do cliente");
		addDefaultOptions();
	}
	
	private void addDefaultOptions() {
		super.options.add(new MethodMenuOption("Criar conta", this::createAccount));
		super.options.add(new MethodMenuOption("Realizar login", (sc) -> NextAction.Continue(sc.nextLine()))); //TODO: Implementar o login
	}
	
	private NextAction createAccount(Scanner sc) {
		CostumerCrud crud = new CostumerCrud();
		Costumer newCostummer = crud.create(sc);
		//TODO: Fazer com que depois que o usuário seja criado, faça login automaticamente
		return NextAction.Continue();
	}
}
