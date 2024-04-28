package menu.interfaces;

import java.util.Scanner;

import menu.ENextAction;

public interface IMenuOption {
	public ENextAction execute(Scanner sc);
	public String getDescription();
	public void setDescription(String description);
}
