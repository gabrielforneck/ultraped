package crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import consoleinterface.nextaction.NextAction;
import menu.Menu;
import menu.interfaces.IMenuOption;
import menu.options.MethodMenuOption;

public abstract class Crud extends Menu {

	protected Crud() {
		super();
		options = new ArrayList<>();
	}
	
	protected Crud(String title) {
		super(title);
	}
	
	protected Crud(List<IMenuOption> options) {
		super(options);
	}
	
	protected Crud(String title, List<IMenuOption> options) {
		super(title, options);
	}
	
	protected abstract void showDataAsTable();
	
	protected abstract NextAction create(Scanner sc);
	protected abstract NextAction update(Scanner sc);
	protected abstract NextAction delete(Scanner sc);
	
	@Override
	protected void constructMenu() {
		showTitle();
		System.out.println();
		showDataAsTable();
		System.out.println();
		showOptions();
	}
	
	protected Crud addDefaultCrudOptions() {
		
		options.add(new MethodMenuOption("Criar", (sc) -> create(sc)));
		options.add(new MethodMenuOption("Alterar", (sc) -> update(sc)));
		options.add(new MethodMenuOption("Excluir", (sc) -> delete(sc)));
		
		return this;
	}
}
