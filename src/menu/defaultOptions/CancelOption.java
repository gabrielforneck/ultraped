package menu.defaultOptions;

import menu.ENextAction;
import menu.interfaces.IMenuOption;

public class CancelOption implements IMenuOption{
	private String description;
	
	public CancelOption() {
		this.description = "Cancelar";
	}
	
	@Override
	public ENextAction execute() {
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
