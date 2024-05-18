package crud;

import java.util.Scanner;
import java.util.function.Function;

import consoleinterface.nextaction.NextAction;

public class CrudOption {

	private String description;
	private Function<Scanner, NextAction> action;
	
	public CrudOption() {
	}

	public CrudOption(String description, Function<Scanner, NextAction> action) {
		this.description = description;
		this.action = action;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Function<Scanner, NextAction> getAction() {
		return action;
	}

	public void setAction(Function<Scanner, NextAction> action) {
		this.action = action;
	}
	
	public NextAction execute(Scanner sc) {
		return action.apply(sc);
	}
}
