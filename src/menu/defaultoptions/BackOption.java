package menu.defaultoptions;

import java.util.Scanner;

import consoleinterface.nextaction.NextAction;
import menu.AbstractMenuOption;
import menu.interfaces.IMenuOption;

public class BackOption extends AbstractMenuOption implements IMenuOption {
	
	public BackOption() {
		this.description = "Voltar";
	}
	
	@Override
	public NextAction execute(Scanner sc) {
		return NextAction.Exit();
	}
}
