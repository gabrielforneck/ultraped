package menu.defaultoptions;

import menu.AbstractMenuOption;
import menu.interfaces.IExecutableOption;

public class MenuOption extends AbstractMenuOption {

	public MenuOption() {
		super();
	}
	
	public MenuOption(String description, IExecutableOption option) {
		super(description, option);
	}
}
