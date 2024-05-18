package crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import consoleinterface.ConsoleInterface;
import consoleinterface.nextaction.NextAction;
import menu.interfaces.IMenuOption;

public abstract class Crud extends ConsoleInterface {

	protected String title;
	protected List<CrudOption> options;
	
	protected Crud() {
		options = new ArrayList<>();
	}
	
	protected Crud(String title) {
		super(title);
		options = new ArrayList<>();
	}
	
	protected Crud(List<CrudOption> options) {
		super();
		options = new ArrayList<>();
		this.options = options;
	}
	
	protected Crud(String title, List<CrudOption> options) {
		super(title);
		options = new ArrayList<>();
		this.options = options;
	}
	
	protected abstract void showDataAsTable();
	
	protected abstract NextAction create(Scanner sc);
	protected abstract NextAction update(Scanner sc);
	protected abstract NextAction delete(Scanner sc);
	
	protected NextAction goBack(Scanner sc) {
		return NextAction.Exit();
	}
	
	protected void constructCrud() {
		showTitle();
		System.out.println();
		showDataAsTable();
		System.out.println();
		showOptions();
	}
	
	protected void showOptions() {
		if (this.options == null)
			return;
		
		for (int i = 0; i<options.size(); i++)
			System.out.println(i + " - " + options.get(i).getDescription());
	}

	protected void addDefaultCrudOptions() {
		
		options.add(new CrudOption("Criar", (sc) -> create(sc)));
		options.add(new CrudOption("Alterar", (sc) -> update(sc)));
		options.add(new CrudOption("Excluir", (sc) -> delete(sc)));
		options.add(new CrudOption("Voltar", (sc) -> goBack(sc)));
	}
	
	protected NextAction waitForOptionAndExecute(Scanner sc) {
		int optionIndex = 0;
		
		try {
			System.out.print("Selecione a opção: ");
			optionIndex = sc.nextInt();
			sc.nextLine();
		}
		catch (Exception ex) {
			sc.next();
			return NextAction.Continue("Entrada inválida.");
		}
		
		if (optionIndex < 0 || optionIndex >= options.size())
			return NextAction.Continue("Opção não encontrada.");
		
		System.out.println();
		return this.options.get(optionIndex).execute(sc);
	}

	public List<CrudOption> getOptions() {
		return options;
	}

	public void setOptions(List<CrudOption> options) {
		this.options = options;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
