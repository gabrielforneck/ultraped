package menu.defaultOptions;

import menu.ENextAction;
import menu.interfaces.IMenuOption;

public class BackOption implements IMenuOption {
	private String description;
	
	public BackOption() {
		this.description = "Voltar";
	}
	
	@Override
	public ENextAction execute() {
		return ENextAction.EXIT;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
}
