package menu;

import java.util.Scanner;

import menu.interfaces.IExecutableOption;
import menu.interfaces.IMenuOption;
import menu.nextaction.NextAction;

public abstract class AbstractMenuOption implements IMenuOption {
	protected String description;
	protected IExecutableOption option;
	
	protected AbstractMenuOption() {
	}
	
	protected AbstractMenuOption(String description, IExecutableOption option) {
		this.description = description;
		this.option = option;
	}
	
	public NextAction execute(Scanner sc) {
		return this.option.execute(sc);
	}

	public IExecutableOption getOption() {
		return option;
	}

	public void setOption(IExecutableOption option) {
		this.option = option;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
