package menu.options.interfaces;

import java.util.Scanner;

import consoleinterface.nextaction.NextAction;

public interface IExecutableOption {
	public NextAction execute(Scanner sc);
}
