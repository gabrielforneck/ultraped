package menu.defaultoptions;

import java.util.Scanner;

import menu.AbstractMenuOption;
import menu.ENextAction;
import menu.interfaces.IMenuOption;

public class CancelOption extends AbstractMenuOption implements IMenuOption{

	public CancelOption() {
		this.description = "Cancelar";
	}
	
	@Override
	public ENextAction execute(Scanner sc) {
		return ENextAction.EXIT;
	}
}
