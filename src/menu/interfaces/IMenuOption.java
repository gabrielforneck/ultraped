package menu.interfaces;

import menu.ENextAction;

public interface IMenuOption {
	public ENextAction execute();
	public String getDescription();
	public void setDescription(String description);
}
