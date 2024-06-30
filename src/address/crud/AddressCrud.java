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

		return new Menu("Alterar endereço").addOptions(options).setDetailsToShow(address).execute(sc);
	}

	private List<IMenuOption> getDefaultFieldOptions(Address address) {
		List<IMenuOption> options = new ArrayList<>();

		options.add(new StringCrudField("Rua", "Insira a rua:", address::setStreet));
		options.add(new StringCrudField("Número", "Insira o número:", address::setNumber));
		options.add(new StringCrudField("Complemento", "Insira o complemento:", address::setComplement));
		options.add(new StringCrudField("Bairro", "Insira o bairro:", address::setDistrict));
		options.add(new StringCrudField("CEP", "Insira o CEP:", address::setCep));
		options.add(new StringCrudField("Cidade", "Insira a cidade:", address::setCity));
		options.add(new StringCrudField("Estado", "Insira o estado:", address::setState));

		return options;
	}
}
