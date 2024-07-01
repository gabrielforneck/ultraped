package costumer.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import address.crud.AddressCrud;
import application.Program;
import consoleinterface.nextaction.NextAction;
import costumer.Costumer;
import costumer.validation.CostumerValidation;
import crud.field.BooleanCrudField;
import crud.field.StringCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import result.ResultWithData;

public final class CostumerCrud {

	public static Costumer create(Scanner sc) {
		Costumer dummyCostumer = new Costumer();
		
		updateRecord("Novo cliente", dummyCostumer, sc);

		//TODO: Fazer com que a alteração seja salva em disco
		Program.applicationData.costumerRepository.save(dummyCostumer);

		return dummyCostumer;
	}

	public static NextAction update(Scanner sc, Costumer costumer) {
		
		//Não preciso atualizar no repositório pois já estou alterando na referência.
		//TODO: Fazer com que a alteração seja salva em disco
		return updateRecord("Atualizar o meu cadastro", costumer, sc);
	}

	private static NextAction updateRecord(String title, Costumer record, Scanner sc) {
		List<IMenuOption> options = getDefaultFieldOptions(record);
		options.add(new MethodMenuOption("Concluir", (scanner) -> CostumerValidation.validateAll(record).toExitNextActionIfSucces()));

		return new Menu(title, options).setDetailsToShow(record).execute(sc);
	}

	public static NextAction delete(Scanner sc, Costumer c) {
		ResultWithData<Boolean> requestResult = new BooleanCrudField("", "Você tem certeza que deseja excluir seu cadastro?").requestData(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		if (requestResult.getData() == false)
			return NextAction.Continue();
		
		//TODO: Avaliar como validar se o fornecedor pode ser excluído. Exemplo: o fornecedor tem produtos que estão em uso em algum pedido.
		//TODO: Fazer com que a alteração seja salva em disco
		Program.applicationData.costumerRepository.delete(c.getId());

		return NextAction.Exit();
	}

	private static List<IMenuOption> getDefaultFieldOptions(Costumer costumer) {
		List<IMenuOption> options = new ArrayList<>();
		
		options.add(new StringCrudField("Nome", "Insira o nome:", costumer::setName));
		options.add(new StringCrudField("Telefone", "Insira o telefone:", costumer::setPhone));
		options.add(new StringCrudField("E-mail", "Insira o e-mail:", costumer::setEmail));
		options.add(new StringCrudField("Cartão de crédito", "Insira o cartão de crédito:", costumer::setCreditCard));
		options.add(new MethodMenuOption("Endereço", (sc) -> new AddressCrud().update(costumer.getAddress(), sc)));
		
		return options;
	}

}