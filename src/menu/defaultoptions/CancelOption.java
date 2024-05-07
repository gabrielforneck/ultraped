package menu.defaultoptions;

import java.util.Scanner;

import menu.AbstractMenuOption;
import menu.interfaces.IMenuOption;
import menu.nextaction.ENextAction;
import menu.nextaction.NextAction;

public class CancelOption extends AbstractMenuOption implements IMenuOption{

	public CancelOption() {
		this.description = "Cancelar";
	}
	
	@Override
	public NextAction execute(Scanner sc) {
		return new NextAction(ENextAction.EXIT);
	}
}
