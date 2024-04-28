package menu.defaultOptions;

import java.util.Scanner;

import menu.ENextAction;
import menu.interfaces.IMenuOption;

public class CancelOption implements IMenuOption{
	private String description;
	
	public CancelOption() {
		this.description = "Cancelar";
	}
	
	@Override
	public ENextAction execute(Scanner sc) {
		return ENextAction.EXIT;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
}
