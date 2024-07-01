package customer.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import address.crud.AddressCrud;
import application.Program;
import consoleinterface.nextaction.NextAction;
import crud.field.BooleanCrudField;
import crud.field.StringCrudField;
import customer.Customer;
import customer.validation.CustomerValidation;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import result.ResultWithData;

public final class CustomerCrud {

	public static Customer create(Scanner sc) {
		Customer dummyCustomer = new Customer();
		
		updateRecord("Novo cliente", dummyCustomer, sc);

		Program.applicationData.customerRepository.save(dummyCustomer);
		Program.applicationData.saveAllToDisk();

		return dummyCustomer;
	}

	public static NextAction update(Scanner sc, Customer customer) {
		
		//Não preciso atualizar no repositório pois já estou alterando na referência.
		updateRecord("Atualizar o meu cadastro", customer, sc);
		Program.applicationData.saveAllToDisk();
		return NextAction.Continue();
	}

	private static NextAction updateRecord(String title, Customer record, Scanner sc) {
		List<IMenuOption> options = getDefaultFieldOptions(record);
		options.add(new MethodMenuOption("Concluir", (scanner) -> CustomerValidation.validateAll(record).toExitNextActionIfSucces()));

		return new Menu(title, options).setDetailsToShow(record).execute(sc);
	}

	public static NextAction delete(Scanner sc, Customer c) {
		ResultWithData<Boolean> requestResult = new BooleanCrudField("", "Você tem certeza que deseja excluir seu cadastro?").requestData(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		if (requestResult.getData() == false)
			return NextAction.Continue();
		
		Program.applicationData.customerRepository.delete(c.getId());
		Program.applicationData.saveAllToDisk();

		return NextAction.Exit();
	}

	private static List<IMenuOption> getDefaultFieldOptions(Customer customer) {
		List<IMenuOption> options = new ArrayList<>();
		
		options.add(new StringCrudField("Nome", "Insira o nome:", customer::setName));
		options.add(new StringCrudField("Telefone", "Insira o telefone:", customer::setPhone));
		options.add(new StringCrudField("E-mail", "Insira o e-mail:", customer::setEmail));
		options.add(new StringCrudField("Cartão de crédito", "Insira o cartão de crédito:", customer::setCreditCard));
		options.add(new MethodMenuOption("Endereço", (sc) -> new AddressCrud().update(customer.getAddress(), sc)));
		
		return options;
	}

}
