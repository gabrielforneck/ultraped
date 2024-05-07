package menu.defaultoptions;

import java.util.Scanner;

import menu.AbstractMenuOption;
import menu.interfaces.IMenuOption;
import nextaction.ENextAction;
import nextaction.NextAction;

public class BackOption extends AbstractMenuOption implements IMenuOption {
	
	public BackOption() {
		this.description = "Voltar";
	}
	
	@Override
	public NextAction execute(Scanner sc) {
		return new NextAction(ENextAction.EXIT);
	}
}
