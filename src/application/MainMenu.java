package application;

import java.util.HashMap;

import menu.AbstractMenu;
import menu.defaultOptions.CancelOption;
import menu.interfaces.IMenuOption;

public class MainMenu extends AbstractMenu {

	public MainMenu() {
		this.options = new HashMap<Integer, IMenuOption>();
		this.addOption(new CancelOption());
		this.setTitle("ULTRAPAD");
	}
}
