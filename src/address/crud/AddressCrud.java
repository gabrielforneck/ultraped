package address.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import address.Address;
import address.validation.AddressValidation;
import consoleinterface.nextaction.NextAction;
import crud.field.StringCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;

public class AddressCrud {
	
	public NextAction update(Address address, Scanner sc) {
		List<IMenuOption> options = getDefaultFieldOptions(address);
		options.add(new MethodMenuOption("Aceitar", (scanner) -> AddressValidation.validateAll(address).toExitNextActionIfSucces()));

		return new Menu("Novo endereço").addOptions(options).setDetailsToShow(address).execute(sc);
	}

	private List<IMenuOption> getDefaultFieldOptions(Address address) {
		List<IMenuOption> options = new ArrayList<>();

		options.add(new StringCrudField("Rua", "Insira a rua:", (s) -> address.setStreet(s)));
		options.add(new StringCrudField("Número", "Insira o número:", (n) -> address.setNumber(n)));
		options.add(new StringCrudField("Complemento", "Insira o complemento:", (c) -> address.setComplement(c)));
		options.add(new StringCrudField("Bairro", "Insira o bairro:", (b) -> address.setDistrict(b)));
		options.add(new StringCrudField("CEP", "Insira o CEP:", (c) -> address.setCep(c)));
		options.add(new StringCrudField("Cidade", "Insira a cidade:", (c) -> address.setCity(c)));
		options.add(new StringCrudField("Estado", "Insira o estado:", (e) -> address.setState(e)));

		return options;
	}
}
