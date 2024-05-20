package menu.options;

import java.util.Scanner;
import java.util.function.Function;

import consoleinterface.nextaction.NextAction;
import menu.interfaces.IMenuOption;

public class MethodMenuOption implements IMenuOption {
	protected String description;
	protected Function<Scanner, NextAction> option;
	
	public MethodMenuOption() {
	}
	
	public MethodMenuOption(String description, Function<Scanner, NextAction> option) {
		this.description = description;
		this.option = option;
	}
	
	public NextAction execute(Scanner sc) {
		return this.option.apply(sc);
	}

	public Function<Scanner, NextAction> getOption() {
		return option;
	}

	public void setOption(Function<Scanner, NextAction> option) {
		this.option = option;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
