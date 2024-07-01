package crud;

import java.util.Scanner;

import consoleinterface.nextaction.NextAction;

public abstract class FullCrud<T> extends Crud {
	
	protected FullCrud(String title) {
		super(title);
	}

	protected abstract NextAction create(Scanner sc);
	protected abstract NextAction update(Scanner sc);
	protected abstract NextAction updateRecord(String title, T record, Scanner sc);
	protected abstract NextAction delete(Scanner sc);
}
