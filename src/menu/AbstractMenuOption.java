package menu;

import java.util.Scanner;

import menu.interfaces.IExecutableOption;
import menu.interfaces.IMenuOption;

public abstract class AbstractMenuOption implements IMenuOption {
	protected String description;
	protected IExecutableOption option;
	
	public AbstractMenuOption() {
	}
	
	public AbstractMenuOption(String description, IExecutableOption option) {
		this.description = description;
		this.option = option;
	}
	
	public ENextAction execute(Scanner sc) {
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
