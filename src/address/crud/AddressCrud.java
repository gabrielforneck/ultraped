package address.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import address.Address;
import address.validation.AddressValidation;
import consoleinterface.nextaction.NextAction;
import crud.CrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import result.Result;

public class AddressCrud {

	public Address create(Scanner sc) {
		Address dummyAddress = new Address();
		
		update(dummyAddress, sc);
		
		return dummyAddress;
	}
	
	public NextAction update(Address address, Scanner sc) {
		Address dummyAddress = address == null ? new Address() : address.clone();
		List<IMenuOption> options = getDefaultFieldOptions(dummyAddress);
		
		options.add(new MethodMenuOption("Aceitar", (scanner) -> validateAndSave(dummyAddress, (a) -> AddressValidation.validateAll(a))));
		options.add(new MethodMenuOption("Cancelar", (scanner) -> NextAction.ExecuteAndExit(scanner, (s) -> dummyAddress.clear())));

		new Menu("Novo endereço").addOptions(options).setDetailsToShow(dummyAddress).execute(sc);
		
		if (dummyAddress.getStreet() == null)
			return NextAction.Continue();
		
		dummyAddress.copyTo(address);
		return NextAction.Continue();
	}

	private List<IMenuOption> getDefaultFieldOptions(Address address) {
		List<IMenuOption> options = new ArrayList<>();

		options.add(new CrudField<String>("Rua", "Insira o nome:", (n) -> address.setStreet(n)));
		options.add(new CrudField<String>("Número", "Insira a descrição:", (d) -> address.setNumber(d)));
		options.add(new CrudField<String>("Complemento", "Insira o telefone:", (p) -> address.setComplement(p)));
		options.add(new CrudField<String>("Bairro", "Insira o e-mail:", (e) -> address.setDistrict(e)));
		options.add(new CrudField<String>("CEP", "Insira o e-mail:", (e) -> address.setCep(e)));
		options.add(new CrudField<String>("Cidade", "Insira o e-mail:", (e) -> address.setCity(e)));
		options.add(new CrudField<String>("Estado", "Insira o e-mail:", (e) -> address.setState(e)));

		return options;
	}
	
	protected NextAction validateAndSave(Address adress, Function<Address, Result> validator) {
		Result validationResult = validator.apply(adress);

		if (validationResult.isFailure())
			return NextAction.Continue(validationResult.getMessage());

		return NextAction.Exit();
	}

}
