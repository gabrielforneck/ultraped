package menu.defaultoptions;

import java.util.Scanner;

import menu.AbstractMenuOption;
import menu.ENextAction;
import menu.interfaces.IMenuOption;

public class BackOption extends AbstractMenuOption implements IMenuOption {
	
	public BackOption() {
		this.description = "Voltar";
	}
	
	@Override
	public ENextAction execute(Scanner sc) {
		return ENextAction.EXIT;
	}
}
