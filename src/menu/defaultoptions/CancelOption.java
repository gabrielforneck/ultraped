package menu.defaultoptions;

import java.util.Scanner;

import consoleinterface.nextaction.NextAction;
import menu.AbstractMenuOption;
import menu.interfaces.IMenuOption;

public class CancelOption extends AbstractMenuOption implements IMenuOption{

	public CancelOption() {
		this.description = "Cancelar";
	}
	
	@Override
	public NextAction execute(Scanner sc) {
		return NextAction.Exit();
	}
}
