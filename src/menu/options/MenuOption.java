package menu.options;

import menu.interfaces.IExecutableOption;

public class MenuOption extends ObjectMenuOption {

	public MenuOption() {
		super();
	}
	
	public MenuOption(String description, IExecutableOption option) {
		super(description, option);
	}
}
