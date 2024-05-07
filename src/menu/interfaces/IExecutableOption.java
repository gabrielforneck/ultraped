package menu.interfaces;

import java.util.Scanner;

import menu.nextaction.NextAction;

public interface IExecutableOption {
	public NextAction execute(Scanner sc);
}
