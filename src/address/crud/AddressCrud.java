package address.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import address.Address;
import address.validation.AddressValidation;
import consoleinterface.nextaction.NextAction;
import crud.field.StringCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import result.Result;

public class AddressCrud {
	
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

		options.add(new StringCrudField("Rua", "Insira a rua:", (n) -> address.setStreet(n)));
		options.add(new StringCrudField("Número", "Insira o número:", (d) -> address.setNumber(d)));
		options.add(new StringCrudField("Complemento", "Insira o complemento:", (p) -> address.setComplement(p)));
		options.add(new StringCrudField("Bairro", "Insira o bairro:", (e) -> address.setDistrict(e)));
		options.add(new StringCrudField("CEP", "Insira o CEP:", (e) -> address.setCep(e)));
		options.add(new StringCrudField("Cidade", "Insira a cidade:", (e) -> address.setCity(e)));
		options.add(new StringCrudField("Estado", "Insira o estado:", (e) -> address.setState(e)));

		return options;
	}
	
	protected NextAction validateAndSave(Address adress, Function<Address, Result> validator) {
		Result validationResult = validator.apply(adress);

		if (validationResult.isFailure())
			return NextAction.Continue(validationResult.getMessage());

		return NextAction.Exit();
	}

}
