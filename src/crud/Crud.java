package crud;

import java.util.ArrayList;
import java.util.List;

import menu.Menu;
import menu.options.interfaces.IMenuOption;

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

	@Override
	protected void constructMenu() {
		showTitle();
		System.out.println();
		showDataAsTable();
		System.out.println();
		showOptions();
	}

}
