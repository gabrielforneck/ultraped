package menu.options;

import java.util.Scanner;

import consoleinterface.nextaction.NextAction;
import menu.options.interfaces.IExecutableOption;
import menu.options.interfaces.IMenuOption;

public abstract class ObjectMenuOption implements IMenuOption {
	protected String description;
	protected IExecutableOption option;

	protected ObjectMenuOption() {
	}

	protected ObjectMenuOption(String description, IExecutableOption option) {
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
